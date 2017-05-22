package hu.elteik.projecttools.libmgmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Map;

/**
 * Created by Bázis on 2017. 04. 14..
 */

/**
 * A Spring Controller which manages the /index page (HTTP requests, forms).
 * Fills the model with values from the current security context and sends them to the view.
 */
@Controller
public class DefaultContextController {

    /**
     * Redirection from root to the /index endpoint/view
     *
     * @return index view
     */
    @RequestMapping("/")
    public String redirectRoot() {
        return "redirect:index";
    }

    /**
     * Initializes index page. Sets navbar authority. Handles user roles.
     *
     * @param model     model Map available to the current view
     * @param principal user authority
     * @return the current view
     */
    @RequestMapping("/index")
    public String index(Map<String, Object> model, Principal principal) {
        if (principal != null)
            model.put("currentUser", principal.getName());
        return "index";
    }
}
