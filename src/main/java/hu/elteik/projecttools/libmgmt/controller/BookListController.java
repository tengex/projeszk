package hu.elteik.projecttools.libmgmt.controller;

import hu.elteik.projecttools.libmgmt.data.dao.BookDao;
import hu.elteik.projecttools.libmgmt.data.dao.BorrowDao;
import hu.elteik.projecttools.libmgmt.data.dao.CopyDao;
import hu.elteik.projecttools.libmgmt.data.dao.UserDao;
import hu.elteik.projecttools.libmgmt.data.entity.Book;
import hu.elteik.projecttools.libmgmt.data.entity.Borrow;
import hu.elteik.projecttools.libmgmt.data.entity.Copy;
import hu.elteik.projecttools.libmgmt.data.entity.User;
import hu.elteik.projecttools.libmgmt.util.BorrowStatus;
import hu.elteik.projecttools.libmgmt.util.CopyStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

/**
 * A Spring Controller which manages the /book_list page (HTTP requests, forms).
 * Fills the model with values from the database and sends them to the view.
 */
@Controller
public class BookListController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private CopyDao copyDao;

    @Autowired
    private BorrowDao borrowDao;

    /**
     * Handles GET requests for the /book_list endpoint.
     * Initializes book list and user list; puts them in the model available to the view.
     * Manages user roles.
     *
     * @param model     model Map available to the current view
     * @param principal User authority
     * @return the current view after initialization.
     */
    @RequestMapping(value = "/book_list", method = RequestMethod.GET)
    public String book_list(Map<String, Object> model, Principal principal) {
        List<Book> bookList = new ArrayList<>();
        Iterator<Book> bookIterator = bookDao.findAll().iterator();
        bookIterator.forEachRemaining(bookList::add);
        model.put("bookList", bookList);
        List<User> userList = new ArrayList<>();
        Iterator<User> userIterator = userDao.findAll().iterator();
        userIterator.forEachRemaining(userList::add);
        model.put("userList", userList);
        model.put("addBookForm", new Book());
        if (principal != null) {
            model.put("user", userDao.findByUsername(principal.getName()));
            model.put("currentUser", principal.getName());
        }
        return "book_list";
    }

    /**
     * Handles borrow requests (HTTP POST).
     *
     * @param postPayload POST request data
     * @return the current view (see {@link #book_list(Map, Principal)})
     */
    @RequestMapping(value = "/borrow", method = RequestMethod.POST)
    public String borrowBook(@RequestBody String postPayload) {
        String[] paramArr = postPayload.split("&");
        User user = userDao.findByUsername(paramArr[1].split("=")[1]);
        Copy copy = copyDao.findOne(Long.parseLong(paramArr[0].split("=")[1]));
        copy.setCopyStatus(CopyStatus.BORROWED);
        copyDao.save(copy);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);

        Borrow borrow = new Borrow(user, copy, cal.getTime());
        borrowDao.save(borrow);
        Set<Borrow> borrows = user.getBorrows();
        borrows.add(borrow);
        user.setBorrows(borrows);
        userDao.save(user);
        return "redirect:/book_list";
    }

    /**
     * Handles borrow status change to CLOSED (HTTP POST)
     *
     * @param postPayload POST request data
     * @param request     current HTTP request, used to redirect to previously visited page (by using Referer header)
     * @return the previously visited view, or if there is no Referer available, the current view (see {@link #book_list(Map, Principal)})
     */
    @RequestMapping(value = "/borrow_del", method = RequestMethod.POST)
    public String closeExistingBorrow(@RequestBody String postPayload, HttpServletRequest request) {
        Copy copy = copyDao.findOne(Long.parseLong(postPayload.split("&")[0].split("=")[1]));
        Borrow borrow = borrowDao.findByCopy(copy);
        copy.setCopyStatus(CopyStatus.AVAILABLE);
        copyDao.save(copy);
        borrow.setCloseDate(new Date());
        borrow.setCopy(copy);
        borrow.setStatus(BorrowStatus.CLOSED);
        borrowDao.save(borrow);
        String referer = request.getHeader("Referer");
        if (borrow.getExpiryDate().before(borrow.getCloseDate())) {
            User user = borrow.getUser();
            int sum = user.getFeeAmount() + 300;
            user.setFeeAmount(sum);
            userDao.save(user);
        }
        if (referer != null && !"".equals(referer))
            return "redirect:" + referer;
        else
            return "redirect:/book_list";

    }

    /**
     * Handles new book addition requests (HTTP POST).
     *
     * @param model     model Map available to the current view
     * @param copyCount available copies of the added book
     * @param book      new Book instance
     * @return the current view (see {@link #book_list(Map, Principal)})
     */
    @RequestMapping(value = "/add_book", method = RequestMethod.POST)
    public String addBook(Map<String, Object> model, @RequestParam(name = "addBookCopyCount") Integer copyCount, @ModelAttribute(name = "addBookForm") Book book) {
        Set<Copy> copies = new HashSet<>();
        for (int i = 0; i < copyCount; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);
        return "redirect:/book_list";
    }

    /**
     * Handles book copy addition requests (HTTP POST)
     *
     * @param bookId    id of book we want to add copies to
     * @param copyCount amount of new copies
     * @return the current view (see {@link #book_list(Map, Principal)})
     */
    @RequestMapping(value = "/add_copies", method = RequestMethod.POST)
    public String addCopies(@RequestParam(name = "addCopyBookId") Long bookId, @RequestParam(name = "addCopyCount") Integer copyCount) {
        Book book = bookDao.findOne(bookId);
        Set<Copy> copies = new HashSet<>();
        for (int i = 0; i < copyCount; i++) {
            copies.add(new Copy());
        }
        copyDao.save(copies);
        Set<Copy> persistedCopies = book.getCopies();
        persistedCopies.addAll(copies);
        book.setCopies(persistedCopies);
        bookDao.save(book);
        return "redirect:/book_list";
    }
}
