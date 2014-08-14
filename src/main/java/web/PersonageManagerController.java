package web;

import entity.Personage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import services.PersonageService;
import services.RaceService;

/**
 * User: artemk
 * Date: 8/12/14
 * Time: 10:02 AM
 */
@Controller
@SessionAttributes
public class PersonageManagerController {
    @Autowired
    private PersonageService personageService;

    @Autowired
    private RaceService raceService;

    @RequestMapping("/personageManager")
    public String listPersonages(Model model) {

        model.addAttribute("personage", new Personage());
        model.addAttribute("personagesList", personageService.getAllPersonages());
        model.addAttribute("racesList", raceService.getAllRaces());

        return "personage_editor";
    }

    @RequestMapping(value = "/addPersonage", method = RequestMethod.POST)
    public String addPersonage(@Validated @ModelAttribute("personage") Personage personage,
                               BindingResult result) {

        personageService.addPersonage(personage);

        return "redirect:/personageManager";
    }

    @RequestMapping("/deletePersonage/{personageId}")
    public String deletePersonage(@PathVariable("personageId") Integer personageId) {

        personageService.deletePersonageById(personageId);

        return "redirect:/personageManager";
    }
}
