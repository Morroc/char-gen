package web;

import entity.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.RaceService;

/**
 * User: artemk
 * Date: 8/21/14
 * Time: 4:45 PM
 */
@RestController
@RequestMapping("/service/race")
public class RaceManagerFullRestSpringServiceController {
    @Autowired
    private RaceService raceService;

//    @RequestMapping(value = "/raceManager", method = RequestMethod.GET, headers="Accept=application/json")
//    public String listRaces(Model model) {
//
//        model.addAttribute("race", new Race());
//        model.addAttribute("racesList", raceService.getAllRaces());
//
//        return "race_manager_rest";
//    }

//    @RequestMapping(value = "/addRace", method = RequestMethod.POST, headers="Accept=application/json")
//    public String addRace(@ModelAttribute("race") Race race,
//                          BindingResult result) {
//
//        raceService.addRace(race);
//
//        return "redirect:/raceManager";
//    }
//
//    @RequestMapping(value = "/deleteRace/{raceId}", method = RequestMethod.GET, headers="Accept=application/json")
//    public String deleteRace(@PathVariable("raceId") Integer raceId) {
//
//        raceService.deleteRaceById(raceId);
//
//        return "redirect:/raceManager";
//    }

    @RequestMapping(value = "/getRace/{raceName}", method = RequestMethod.GET, headers="Accept=application/json")
    public @ResponseBody Race getRaceById(@PathVariable("raceName") String raceName) {
        return raceService.getRaceByName(raceName);
    }
}
