package hu.elteik.projecttools.libmgmt.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.*;

/**
 * Created by Bázis on 2017. 04. 14..
 */
@Controller
public class DefaultContextController {
        @RequestMapping("/")
        public String redirectRoot() {
                return "redirect:index";
        }

        @RequestMapping("/index")
        public String index(Map<String, Object> model, Principal principal) {
                if(principal!= null)
                model.put("currentUser", principal.getName());
                return "index";
        }
}
