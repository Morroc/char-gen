package web;

import constants.Constants;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import services.*;

import java.util.List;

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

    @Autowired
    private RaceHasAttributeService raceHasAttributeService;

    @Autowired
    private PersonageHasAttributeService personageHasAttributeService;

    @Autowired
    private RaceHasMeritService raceHasMeritService;

    @Autowired
    private PersonageHasMeritService personageHasMeritService;

    @RequestMapping("/personageManager")
    public String pageModel(Model model) {

        model.addAttribute("personage", new Personage());
        model.addAttribute("personagesList", personageService.getAllPersonages());
        model.addAttribute("racesList", raceService.getAllRaces());

        return "personage_manager";
    }

    @RequestMapping(value = "/addPersonage", method = RequestMethod.POST)
    public String addPersonage(@Validated @ModelAttribute("personage") Personage personage,
                               BindingResult result) {

        //add personage
        personageService.addPersonage(personage);

        //add attributes
        int raceIdOfPersonage = personageService.getRaceByPersonageId(personage.getId());
        List<RaceHasAttribute> raceHasAttributes = raceHasAttributeService.getRaceHasAttributesByRaceId(raceIdOfPersonage);
        for (RaceHasAttribute raceHasAttribute : raceHasAttributes) {
            PersonageHasAttribute personageHasAttribute = new PersonageHasAttribute();
            personageHasAttribute.setAttributeByPersonage(raceHasAttribute.getAttributeByRace());
            personageHasAttribute.setPersonageByAttribute(personage);
            personageHasAttribute.setCurrentValue(Constants.DEFAULT_VALUE_OF_ATTRIBUTE);
            personageHasAttributeService.addLinkAttributeWithPersonage(personageHasAttribute);
        }

        //add default race merits
        List<RaceHasMerit> raceHasMerits = raceHasMeritService.getRaceHasMeritsByRaceId(raceIdOfPersonage);
        for (RaceHasMerit raceHasMerit : raceHasMerits) {
            if (raceHasMerit.isDefaultForRace()) {
                PersonageHasMerit personageHasMerit = new PersonageHasMerit();
                personageHasMerit.setMeritByPersonage(raceHasMerit.getMeritByRace());
                personageHasMerit.setPersonageByMerit(personage);
                personageHasMeritService.addLinkMeritWithPersonage(personageHasMerit);
            }
        }

        return "redirect:/personageManager";
    }

    @RequestMapping("/deletePersonage/{personageId}")
    public String deletePersonage(@PathVariable("personageId") Integer personageId) {

        //delete attributes
        List<PersonageHasAttribute> personageHasAttributes = personageHasAttributeService.getPersonageHasAttributesByPersonageId(personageId);
        for (PersonageHasAttribute personageHasAttribute : personageHasAttributes) {
            personageHasAttributeService.deleteLinkAttributeWithPersonage(personageHasAttribute);
        }

        //delete default race merits
        List<PersonageHasMerit> personageHasMerits = personageHasMeritService.getPersonageHasMeritsByPersonageId(personageId);
        for (PersonageHasMerit personageHasMerit : personageHasMerits) {
            personageHasMeritService.deleteLinkMeritWithPersonage(personageHasMerit);
        }

        //delete personage
        personageService.deletePersonageById(personageId);

        return "redirect:/personageManager";
    }
}
