package com.gmail.alisarrian.lamamultimedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("movies")
public class MovieController {

    static List<String> movies = new ArrayList<>();

    // Request path: /movies
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("movies", movies);
        model.addAttribute("title", "My Movies");

        return "movies/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMovieForm(Model model) {

        model.addAttribute("title", "Add Movie");

        return "movies/add";
    }

    //Jedna z dróg
//    @RequestMapping(value = "add", method = RequestMethod.POST)
//    public String processAddMovieForm(HttpServletRequest request) {
//        String movieTitle = request.getParameter("movieTitle");
//        return "redirect:";
//    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMovieForm(@RequestParam String movieTitle) {

        movies.add(movieTitle);

        //Redirect to /movies
        return "redirect:";
    }
}