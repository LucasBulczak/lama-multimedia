package com.gmail.alisarrian.lamamultimedia.controllers;

import com.gmail.alisarrian.lamamultimedia.models.Book;
import com.gmail.alisarrian.lamamultimedia.models.data.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("books", bookDao.findAll());
        model.addAttribute("secHeader", "List of Books");
        model.addAttribute("secDescription",
                "I have only read part of them. The rest is on my \"to-do list\" ;-) ");

        return "books/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMovieForm(Model model) {

        model.addAttribute("secHeader", "Add new book");
        model.addAttribute("secDescription",
                "Remember! You have to complete all fields!");
        model.addAttribute(new Book());

        return "books/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMovieForm(Model model,
                                      @ModelAttribute @Valid Book newBook,
                                      Errors errors) {

        if (errors.hasErrors()) {

            model.addAttribute("secHeader", "Add new book");
            model.addAttribute("secDescription",
                    "Remember! You have to complete all fields!");

            return "books/add";
        }

        bookDao.save(newBook);

        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveSerialForm(Model model) {

        model.addAttribute("books", bookDao.findAll());
        model.addAttribute("secHeader", "Remove Book");

        return "books/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveSerialForm(@RequestParam int[] ids) {

        for (int id : ids) {
            bookDao.delete(id);
        }

        return "redirect:";
    }
}
