package com.gmail.alisarrian.lamamultimedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class HomeController {

    String welcomeMessage =
            "This application was created as part of Java Language learning," +
                    "so bear with me ;-)" +
            "\n" +
            "Have a lot of fun like me while programming." +
            "\n" +
            "This site contains a list of films, serial and books" +
            "that I would like to see / read. If I have already done so," +
            "then the item will be marked \"viewed\" / \"read\" " +
            "and my subjective rating will be inserted.";

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("title", "Hello Everybody!");
        model.addAttribute("message", this.welcomeMessage);

        return "home/index";
    }
}
