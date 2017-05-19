package hu.elteik.projecttools.libmgmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import hu.elteik.projecttools.libmgmt.data.entity.*;

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
    public String profile(Map<String, Object> model) {
        //A bejelentkezett user adatait kell majd itt átadni
        User user = new User("sheenjek", "Vickey R. Frye", "VickeyRFrye@teleworm.us", "434-454-3937", "1167 Worley Avenue 25", "admin");
        user.setPaidAmount(315);
        user.setFeeAmount(400);
        model.put("user", user);
        return "profile";
    }

}
