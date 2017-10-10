package com.gmail.alisarrian.lamamultimedia.controllers;

import com.gmail.alisarrian.lamamultimedia.models.data.SerialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("serial")
public class SerialController {

    @Autowired
    private SerialDao serialDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("serials", serialDao.findAll());
        model.addAttribute("secHeader", "List of Serials");
        model.addAttribute("secDescription",
                "I have only watched part of them. The rest also is on my \"to-do list\" ;-) ");

        return "serials/serial/index";
    }
}
