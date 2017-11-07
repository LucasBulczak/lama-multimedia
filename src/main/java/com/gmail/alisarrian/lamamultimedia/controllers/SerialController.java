package com.gmail.alisarrian.lamamultimedia.controllers;

import com.gmail.alisarrian.lamamultimedia.models.Episode;
import com.gmail.alisarrian.lamamultimedia.models.Season;
import com.gmail.alisarrian.lamamultimedia.models.Serial;
import com.gmail.alisarrian.lamamultimedia.models.data.EpisodeDao;
import com.gmail.alisarrian.lamamultimedia.models.data.SeasonDao;
import com.gmail.alisarrian.lamamultimedia.models.data.SerialDao;
import javafx.geometry.Pos;
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
@RequestMapping("serial")
public class SerialController {

    @Autowired
    private SerialDao serialDao;

    @Autowired
    private SeasonDao seasonDao;

    @Autowired
    private EpisodeDao episodeDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("serials", serialDao.findAll());
        model.addAttribute("secHeader", "List of Serials");
        model.addAttribute("secDescription",
                "I have only watched part of them. The rest also is on my \"to-do list\" ;-) ");

        return "serials/serial/index";
    }

    @RequestMapping(value = "singleSerial", method = RequestMethod.GET)
    public String singleMovie(Model model,
                              @RequestParam int id) {

        Serial serial = serialDao.findOne(id);

        model.addAttribute("serial", serial);
        model.addAttribute("secHeader", serial.getTitle() + " (" + serial.getReleasedDate() + ")");

        List<Season> seasons = serial.getSeasons();

        model.addAttribute("seasons", seasons);

        return "serials/serial/singleSerial";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddSerialForm(Model model) {

        model.addAttribute(new Serial());
        model.addAttribute("secHeader", "Add Serial");


        return "serials/serial/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddSerialForm(Model model,
                                       @ModelAttribute @Valid Serial serial,
                                       Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("secHeader", "Add Serial");
            return "serials/serial/add";
        }

        serialDao.save(serial);

        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveSerialForm(Model model) {

        model.addAttribute("serials", serialDao.findAll());
        model.addAttribute("secHeader", "Remove Serial");

        return "serials/serial/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveSerialForm(@RequestParam int[] ids) {

        for (int id : ids) {
            serialDao.delete(id);
        }

        return "redirect:";
    }

    @RequestMapping(value = "season", method = RequestMethod.GET)
    public String season(Model model,
                         @RequestParam int id){

        Season season = seasonDao.findOne(id);
        model.addAttribute("season", season);

        model.addAttribute("secHeader", season.getSerial().getTitle() + ", " + season.getName());

        List<Episode> episodes = season.getEpisodes();
        model.addAttribute("episodes", episodes);

        return "serials/serial/season";
    }

    @RequestMapping(value = "addSeason", method = RequestMethod.GET)
    public String displayAddSeasonForm(Model model) {

        model.addAttribute(new Season());
        model.addAttribute("secHeader", "Add Season");


        return "serials/serial/addSeason";
    }

    @RequestMapping(value = "addSeason", method = RequestMethod.POST)
    public String processAddSeasonForm(Model model,
                      @RequestParam int id,
                      @ModelAttribute @Valid Season season,
                      Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("secHeader", "Add Season");
            return "serials/serial/addSeason";
        }

        Serial serial = serialDao.findOne(id);
        season.setSerial(serial);
        seasonDao.save(season);

        return "redirect:";
    }

    @RequestMapping(value = "removeSeason", method = RequestMethod.GET)
    public String displayRemoveSeasonForm(Model model,
                                          @RequestParam int id) {

        Serial serial = serialDao.findOne(id);
        List<Season> seasons = serial.getSeasons();
        model.addAttribute("seasons", seasons);

        model.addAttribute("secHeader", "Remove Season");

        return "serials/serial/removeSeason";
    }

    @RequestMapping(value = "removeSeason", method = RequestMethod.POST)
    public String processRemoveSeasonForm(@RequestParam int[] ids) {

        for (int id : ids) {
            seasonDao.delete(id);
        }

        return "redirect:";
    }

    @RequestMapping(value = "addEpisode", method = RequestMethod.GET)
    public String displayAddEpisodeForm(Model model,
                                        @RequestParam int id) {

        model.addAttribute(new Episode());

        Season season = seasonDao.findOne(id);
        model.addAttribute("secHeader", "Add Episode to " + season.getName());

        return "serials/serial/addEpisode";
    }

    @RequestMapping(value = "addEpisode", method = RequestMethod.POST)
    public String processAddEpisodeForm(Model model,
                                        @RequestParam int id,
                                        @ModelAttribute @Valid Episode episode,
                                        Errors errors) {

        if (errors.hasErrors()) {
            Season season = seasonDao.findOne(id);
            model.addAttribute("secHeader", "Add Episode to " + season.getName());
            return "serials/serial/addEpisode";
        }

        Season season = seasonDao.findOne(id);
        episode.setSeason(season);

        episodeDao.save(episode);

        return "redirect:";
    }

    @RequestMapping(value = "removeEpisode", method = RequestMethod.GET)
    public String displayRemoveEpisodeForm(Model model,
                                           @RequestParam int id) {

        Season season = seasonDao.findOne(id);
        List<Episode> episodes = season.getEpisodes();
        model.addAttribute("episodes", episodes);

        model.addAttribute("secHeader", "Remove Episode from " + season.getName());

        return "serials/serial/removeEpisode";
    }


    @RequestMapping(value = "removeEpisode", method = RequestMethod.POST)
    public String processRemoveEpisodeForm(@RequestParam int[] ids) {

        for (int id : ids) {
            episodeDao.delete(id);
        }

        return "redirect:";
    }
}
