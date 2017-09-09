package com.gmail.alisarrian.lamamultimedia.controllers;

import com.gmail.alisarrian.lamamultimedia.models.Category;
import com.gmail.alisarrian.lamamultimedia.models.Movie;
import com.gmail.alisarrian.lamamultimedia.models.data.CategoryDao;
import com.gmail.alisarrian.lamamultimedia.models.data.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private CategoryDao categoryDao;

    // Request path: /movie
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("movies", movieDao.findAll());
        model.addAttribute("title", "My Movies");

        return "movie/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMovieForm(Model model) {

        model.addAttribute("title", "Add Movie");
        model.addAttribute(new Movie()); /* equal to model.addAttribute("movie", new Movie());*/
        model.addAttribute("categories", categoryDao.findAll());

        return "movie/add";
    }

    //Jedna z dróg
//    @RequestMapping(value = "add", method = RequestMethod.POST)
//    public String processAddMovieForm(HttpServletRequest request) {
//        String title = request.getParameter("title");
//        return "redirect:";
//    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMovieForm(Model model,
                                      @RequestParam int categoryId,
                                      @ModelAttribute @Valid Movie newMovie,
                                      Errors errors) {
        //TODO : 1
        //jeśli wpiszę złą datę, to wywala error, a nie cofa do widoku movie/add
        if (errors.hasErrors()) {

            model.addAttribute("title", "Add Movie");
            model.addAttribute("categories", categoryDao.findAll());

            return "movie/add";
        }

        Category cat = categoryDao.findOne(categoryId);
        newMovie.setCategory(cat);
        movieDao.save(newMovie);

        //Redirect to /movie
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveMovieForm(Model model) {

        model.addAttribute("movies", movieDao.findAll());
        model.addAttribute("title", "Remove Movie");

        return "movie/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveMovieForm(@RequestParam int[] ids) {

        for (int id : ids) {
            movieDao.delete(id);
        }

        //Redirect to /movie
        return "redirect:";
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String category(Model model,
                           @RequestParam int id) {

        Category cat = categoryDao.findOne(id);
        List<Movie> movies = cat.getMovies();

        model.addAttribute("movies", movies);
        model.addAttribute("title", "Movies in Category: " + cat.getName());

        return "movie/index";
    }
}