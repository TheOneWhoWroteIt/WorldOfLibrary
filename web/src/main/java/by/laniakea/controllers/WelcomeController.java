package by.laniakea.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }


    @GetMapping("/rules")
    public String rules(Model model){
        return "rules";
    }
}
