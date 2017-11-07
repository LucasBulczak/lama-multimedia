package com.gmail.alisarrian.lamamultimedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class HomeController {

    String welcomeMessage =
            "This application was created as part of Java Language learning, " +
                    "so bear with me ;-)" +
            "\n" +
            "Have a lot of fun like me while programming. " +
            "\n" +
            "This site contains a list of films, serials and books " +
            "that I would like to see / read.";

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("secHeader", "Hello Everybody!");
        model.addAttribute("secDescription", this.welcomeMessage);

        return "home/index";
    }
}
