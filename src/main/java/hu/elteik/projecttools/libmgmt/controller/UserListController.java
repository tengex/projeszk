package hu.elteik.projecttools.libmgmt.controller;

import hu.elteik.projecttools.libmgmt.data.dao.UserDao;
import hu.elteik.projecttools.libmgmt.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A Spring Controller which manages the /user_list page (HTTP requests, forms).
 * Fills the model with values from the database, and sends them to the view.
 */
@Controller
public class UserListController {

    @Autowired
    private UserDao userDao;

    /**
     * Handles the /user_list GET request.
     *
     * @param model     model Map available to the current view
     * @param principal user authority
     * @return the current view
     */
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

    /**
     * Handles the /user_list POST request. Adds a specified amount of money to the balance of the selected user.
     *
     * @param postPayload body of the POST request
     * @return the user_list view
     */
    @RequestMapping(value = "/user_list", method = RequestMethod.POST)
    public String user_list(@RequestBody String postPayload) {
        String payAmountString = postPayload.split("&_csrf")[0];
        String username = payAmountString.split("=")[0].split("_")[1];
        int payAmountValue = Integer.parseInt(payAmountString.split("=")[1]);
        User user = userDao.findByUsername(username);
        user.setPaidAmount(user.getPaidAmount() + payAmountValue);
        userDao.save(user);
        return "redirect:user_list";
    }
}
