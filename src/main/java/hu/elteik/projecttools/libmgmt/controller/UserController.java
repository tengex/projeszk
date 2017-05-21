package hu.elteik.projecttools.libmgmt.controller;

import hu.elteik.projecttools.libmgmt.data.dao.UserDao;
import hu.elteik.projecttools.libmgmt.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Bázis on 2017. 04. 15..
 */
@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    UserDao userDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        User newUser = new User();
        model.addAttribute("userForm", newUser);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User user) {
        logger.warning("DBG USR: " + user.getUsername() + ": " + user.getRole());
        User u = userDao.findByUsername(user.getUsername());
        if (u == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDao.save(user);
            return "redirect:index";
        }
        logger.severe("User already exists!");
        return "registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index?logout";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Map<String, Object> model, Principal principal) {
        if (principal != null) {
            model.put("user", userDao.findByUsername(principal.getName()));
            model.put("currentUser", principal.getName());
        }
        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.PUT)
    public String profile(Principal principal,
            @RequestParam(name = "profile_fullname") String fullName,
            @RequestParam(name = "profile_email") String email,
            @RequestParam(name = "profile_tel") String phoneNum,
            @RequestParam(name = "profile_address") String address){
        User user = userDao.findByUsername(principal.getName());
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhoneNum(phoneNum);
        user.setAddress(address);
        userDao.save(user);
        return "redirect:/profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.DELETE)
    public String deleteProfile(Principal principal){
        userDao.delete(userDao.findByUsername(principal.getName()));
        return "redirect:/logout";
    }

}
