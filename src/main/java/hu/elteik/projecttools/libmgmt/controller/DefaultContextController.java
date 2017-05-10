package hu.elteik.projecttools.libmgmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Bázis on 2017. 04. 14..
 */
@Controller
public class DefaultContextController {
    @RequestMapping("/")
    public String redirectRoot(){
        return "redirect:index";
    }

    @RequestMapping("/index")
    public String index(Map<String,Object> model){
        model.put("message", "Hello, World!");
        return "index";
    }

    @RequestMapping("/book_list")
    public String book_list(Map<String,Object> model){
        //model.put("message", "Könyvtár adatai!");
        return "book_list";
    }
}
