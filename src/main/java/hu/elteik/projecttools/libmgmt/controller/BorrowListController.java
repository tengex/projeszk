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
import java.util.*;

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
public class BorrowListController {
    @Autowired
    BorrowDao borrowDao;

    @Autowired
    UserDao userDao;

    @Autowired
    BookDao bookDao;

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
