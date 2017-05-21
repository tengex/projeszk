package hu.elteik.projecttools.libmgmt.controller;

import hu.elteik.projecttools.libmgmt.data.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.*;

import hu.elteik.projecttools.libmgmt.data.entity.*;

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
public class UserListController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/user_list")
    public String user_list(Map<String, Object> model, Principal principal) {
        List<User> userList = new ArrayList<>();
        Iterator<User> usrIt = userDao.findAll().iterator();
        usrIt.forEachRemaining(userList::add);
        model.put("userList", userList);
        if (principal != null)
            model.put("currentUser", principal.getName());
        return "user_list";
    }
}
