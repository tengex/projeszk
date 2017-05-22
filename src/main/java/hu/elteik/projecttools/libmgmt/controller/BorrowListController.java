package hu.elteik.projecttools.libmgmt.controller;

import hu.elteik.projecttools.libmgmt.data.dao.BookDao;
import hu.elteik.projecttools.libmgmt.data.dao.BorrowDao;
import hu.elteik.projecttools.libmgmt.data.dao.UserDao;
import hu.elteik.projecttools.libmgmt.data.entity.Book;
import hu.elteik.projecttools.libmgmt.data.entity.Borrow;
import hu.elteik.projecttools.libmgmt.data.entity.Copy;
import hu.elteik.projecttools.libmgmt.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A Spring Controller which manages the /borrow_list page (HTTP requests, forms).
 * Fills the model with values from the database and sends them to the view.
 */
@Controller
public class BorrowListController {
    @Autowired
    private BorrowDao borrowDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    /**
     * Handles GET requests for the /borrow_list endpoint.
     * Initializes borrow list and user list; puts them in the model available to the view.
     * Manages user roles and view permissions.
     *
     * @param model     model Map available to the current view
     * @param principal User authority
     * @return the current view
     */
    @RequestMapping("/borrow_list")
    public String borrow_list(Map<String, Object> model, Principal principal) {
        if (principal != null) {
            User user = userDao.findByUsername(principal.getName());
            String currentAuthority = user.getRole();
            List<Borrow> borrowList = new ArrayList<>();
            Iterable<Book> books = bookDao.findAll();
            switch (currentAuthority) {
                case "ROLE_ADMIN":
                case "ROLE_LIBRARIAN":
                    Iterator<Borrow> borrowIterator = borrowDao.findAll().iterator();
                    borrowIterator.forEachRemaining(borrowList::add);
                    break;
                case "ROLE_USER":
                    Iterator<Borrow> borrowIter = user.getBorrows().iterator();
                    borrowIter.forEachRemaining(borrowList::add);
                    break;
            }
            for (Borrow borrow : borrowList) {
                Copy copy = borrow.getCopy();
                for (Book book : books) {
                    if (book.getCopies().contains(copy)) {
                        borrow.setBook(book);
                        break;
                    }
                }
            }
            model.put("borrowList", borrowList);
            model.put("currentUser", principal.getName());
        }
        return "borrow_list";
    }

}
