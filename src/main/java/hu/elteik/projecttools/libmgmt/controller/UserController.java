package hu.elteik.projecttools.libmgmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Bázis on 2017. 04. 15..
 */
@Controller
public class UserController {
    //todo user stuff

	@RequestMapping("/registration")
    public String registration(){
        return "registration";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
	
	 @RequestMapping("/profile")
    public String profile() {

        return "profile";
    }

}
