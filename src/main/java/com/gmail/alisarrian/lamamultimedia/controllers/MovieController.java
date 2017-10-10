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
        model.addAttribute("secHeader", "List of Movies");
        model.addAttribute("secDescription",
                "I have only watched part of them. The rest is on my \"to-do list\" ;-) ");

        return "movies/movie/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMovieForm(Model model) {

        model.addAttribute("secHeader", "Add new movie");
        model.addAttribute("secDescription",
                "Remember! You have to complete all fields!");
        model.addAttribute(new Movie()); /* equal to model.addAttribute("movie", new Movie());*/
        model.addAttribute("categories", categoryDao.findAll());

        return "movies/movie/add";
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

        if (errors.hasErrors()) {

            model.addAttribute("secHeader", "Add new movie");
            model.addAttribute("secDescription",
                    "Remember! You have to complete all fields!");
            model.addAttribute("categories", categoryDao.findAll());

            return "movies/movie/add";
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
        model.addAttribute("secHeader", "Remove Movie");

        return "movies/movie/remove";
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
        model.addAttribute("secHeader", "Movies in Category: " + cat.getName());

        return "movies/movie/index";
    }

    @RequestMapping(value = "singleMovie", method = RequestMethod.GET)
    public String singleMovie(Model model,
                              @RequestParam int id) {

        Movie movie = movieDao.findOne(id);

        model.addAttribute("movie", movie);
        model.addAttribute("secHeader", movie.getTitle() + " (" + movie.getYear() + ")");
        //model.addAttribute("secDescription", movie.getCategory().getName());

        return "movies/movie/singleMovie";
    }
}