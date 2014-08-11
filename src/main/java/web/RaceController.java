package web;

import entity.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.RaceService;

import java.util.Map;

/**
 * User: artemk
 * Date: 8/8/14
 * Time: 1:04 PM
 */
@Controller
@SessionAttributes
public class RaceController {
    @Autowired
    private RaceService raceService;

    @RequestMapping("/index")
    public String listRaces(Model model) {

        model.addAttribute("race", new Race());
        model.addAttribute("raceList", raceService.getAllRaces());

        return "race";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addRace(@ModelAttribute("race") Race race,
                             BindingResult result) {

        raceService.addRace(race);

        return "redirect:/index";
    }

    @RequestMapping("/delete/{raceId}")
    public String deleteContact(@PathVariable("raceId") Integer raceId) {

        raceService.deleteRaceById(raceId);

        return "redirect:/index";
    }
}
