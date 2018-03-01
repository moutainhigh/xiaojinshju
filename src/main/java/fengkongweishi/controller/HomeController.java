package fengkongweishi.controller;

import fengkongweishi.util.Common;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String index(Model model) {

        System.out.println(Common.getPrincipal());

        if ("ROLE_ADMIN".equals(Common.getPrincipal().getRole().getName())) {
            return "index";
            //return "redirect:admin/index.html";
//		 }else	if(Common.getPrincipal().getRole().getName().equals("ROLE_EMPLOYEE")){
//				return "redirect:fengkongweishi/index.html";

//		}else if(Common.getPrincipal().getRole().getName().equals("ROLE_MANAGER")){
//			return "redirect:fengkongweishi/index.html";
//		}else if(Common.getPrincipal().getRole().getName().equals("ROLE_USER")){
//			return "redirect:fengkongweishi/index.html";
        } else {
            return "index";
        }
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }


    @RequestMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @RequestMapping("/password")
    public String password(Model model) {
        return "password";
    }


}
