package web;

import entity.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.RaceService;

/**
 * User: artemk
 * Date: 8/8/14
 * Time: 1:04 PM
 */
@Controller
@SessionAttributes
public class RaceManagerController {
    @Autowired
    private RaceService raceService;

    @RequestMapping("/raceManager")
    public String listRaces(Model model) {

        model.addAttribute("race", new Race());
        model.addAttribute("racesList", raceService.getAllRaces());

        return "WEB-INF/views/race_manager.jsp";
    }

    @RequestMapping(value = "/addRace", method = RequestMethod.POST)
    public String addRace(@ModelAttribute("race") Race race,
                             BindingResult result) {

        raceService.addRace(race);

        return "redirect:/raceManager";
    }

    @RequestMapping("/deleteRace/{raceId}")
    public String deleteRace(@PathVariable("raceId") Integer raceId) {

        raceService.deleteRaceById(raceId);

        return "redirect:/raceManager";
    }
}
