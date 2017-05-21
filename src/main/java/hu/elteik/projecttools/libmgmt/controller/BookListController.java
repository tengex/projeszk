package hu.elteik.projecttools.libmgmt.controller;

import hu.elteik.projecttools.libmgmt.data.dao.BookDao;
import hu.elteik.projecttools.libmgmt.data.dao.BorrowDao;
import hu.elteik.projecttools.libmgmt.data.dao.CopyDao;
import hu.elteik.projecttools.libmgmt.data.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

import hu.elteik.projecttools.libmgmt.data.entity.*;
import hu.elteik.projecttools.libmgmt.util.*;

/**
 * Admin / user kezelése auth nélkül:
 * <p>
 * User user = new User("admin", "Admin", "admin@bestlib.hu", "06702222222", "Budapest", "admin");
 * vagy
 * User user = new User("valaki", "Valaki", "Valaki@bestlib.hu", "06702222222", "Budapest", "valami");
 * <p>
 * model.put("user", user);
 * <p>
 * Thymeleaf template jelenleg ez alapján dönti el a felhasználó jogkörét
 */

@Controller
public class BookListController {

    @Autowired
    UserDao userDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    CopyDao copyDao;

    @Autowired
    BorrowDao borrowDao;

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

    @RequestMapping(value = "/borrow_del", method = RequestMethod.POST)
    public String closeExistingBorrow(@RequestBody String postPayload) {
        Copy copy = copyDao.findOne(Long.parseLong(postPayload.split("&")[0].split("=")[1]));
        Borrow borrow = borrowDao.findByCopy(copy);
        copy.setCopyStatus(CopyStatus.AVAILABLE);
        copyDao.save(copy);
        borrow.setCloseDate(new Date());
        borrow.setCopy(copy);
        borrow.setStatus(BorrowStatus.CLOSED);
        borrowDao.save(borrow);
        return "redirect:/book_list";
    }

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
