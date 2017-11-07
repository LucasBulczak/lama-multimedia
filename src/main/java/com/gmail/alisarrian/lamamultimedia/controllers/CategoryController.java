package com.gmail.alisarrian.lamamultimedia.controllers;

import com.gmail.alisarrian.lamamultimedia.models.Category;
import com.gmail.alisarrian.lamamultimedia.models.data.CategoryDao;
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
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryDao categoryDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("secHeader", "Categories");
        model.addAttribute("secDescription",
                "Click on the category name to see what movies it contains (from added to the database) ;-)");
        model.addAttribute("categories", categoryDao.findAll());

        return "movies/category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute(new Category());
        model.addAttribute("secHeader", "Add Category");


        return "movies/category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model,
                      @ModelAttribute @Valid Category category,
                      Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("secHeader", "Add Category");
            return "movies/category/add";
        }

        categoryDao.save(category);

        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCategoryForm(Model model) {

        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("secHeader", "Remove Category");

        return "movies/category/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCategoryForm(@RequestParam int[] ids) {

        for (int id : ids) {
            categoryDao.delete(id);
        }

        return "redirect:";
    }

}
