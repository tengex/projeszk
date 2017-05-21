package hu.elteik.projecttools.libmgmt.controller;

import hu.elteik.projecttools.libmgmt.data.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.*;

import hu.elteik.projecttools.libmgmt.data.entity.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    @RequestMapping(value = "/user_list", method = RequestMethod.GET)
    public String user_list(Map<String, Object> model, Principal principal) {
        List<User> userList = new ArrayList<>();
        Iterator<User> userIterator = userDao.findAll().iterator();
        userIterator.forEachRemaining(userList::add);
        model.put("userList", userList);
        if (principal != null)
            model.put("currentUser", principal.getName());
        return "user_list";
    }

    @RequestMapping(value = "/user_list", method = RequestMethod.POST)
    public String user_list(@RequestBody String postPayload){
        String payAmountString = postPayload.split("&_csrf")[0];
        String username = payAmountString.split("=")[0].split("_")[1];
        int payAmountValue=Integer.parseInt(payAmountString.split("=")[1]);
        User user = userDao.findByUsername(username);
        user.setPaidAmount(user.getPaidAmount() + payAmountValue);
        userDao.save(user);
        return "redirect:user_list";
    }
}
