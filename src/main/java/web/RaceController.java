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
 * Date: 8/17/14
 * Time: 8:05 AM
 */
@Controller
@SessionAttributes
public class RaceController {
    @Autowired
    private RaceService raceService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private RaceHasAttributeService raceHasAttributeService;

    @Autowired
    private MeritService meritService;

    @Autowired
    private RaceHasMeritService raceHasMeritService;


    @Autowired
    private PersonageHasMeritService personageHasMeritService;

    @Autowired
    private PersonageHasAttributeService personageHasAttributeService;

    @Autowired
    private PersonageService personageService;

    @Autowired
    private RaceHasFlawService raceHasFlawService;

    @Autowired
    private PersonageHasFlawService personageHasFlawService;

    @Autowired
    private FlawService flawService;

    @RequestMapping("/race/{raceId}")
    public String pageModel(@PathVariable("raceId") Integer raceId, Model model) {


        model.addAttribute("race", raceService.getRaceById(raceId));

        model.addAttribute("allAttributesList", attributeService.getAllAttributes());
        model.addAttribute("raceHasAttribute", new RaceHasAttribute());
        model.addAttribute("raceHasAttributeByRace", raceHasAttributeService.getRaceHasAttributesByRaceId(raceId));

        model.addAttribute("allMeritsList", meritService.getAllMerits());
        model.addAttribute("raceHasMerit", new RaceHasMerit());
        model.addAttribute("raceHasMeritByRace", raceHasMeritService.getRaceHasMeritsByRaceId(raceId));

        model.addAttribute("allFlawsList", flawService.getAllFlaws());
        model.addAttribute("raceHasFlaw", new RaceHasFlaw());
        model.addAttribute("raceHasFlawsByRace", raceHasFlawService.getRaceHasFlawsByRaceId(raceId));

        return "race";
    }

    @RequestMapping(value = "/race/linkAttributeToRace", method = RequestMethod.POST)
    public String addRaceHasAttribute(@Validated @ModelAttribute("raceHasAttribute") RaceHasAttribute raceHasAttribute,
                                      BindingResult result) {

        raceHasAttributeService.addLinkAttributeWithRace(raceHasAttribute);
        int raceId = raceHasAttribute.getRaceByAttribute().getId();
        List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
        for (Personage personage : personages) {
            PersonageHasAttribute personageHasAttribute = new PersonageHasAttribute();
            personageHasAttribute.setAttributeByPersonage(raceHasAttribute.getAttributeByRace());
            personageHasAttribute.setPersonageByAttribute(personage);
            personageHasAttribute.setCurrentValue(Constants.DEFAULT_VALUE_OF_ATTRIBUTE);
            personageHasAttributeService.addLinkAttributeWithPersonage(personageHasAttribute);
        }
        return "redirect:/race/" + raceId;
    }

    @RequestMapping(value = "/race/unlinkAttributeFromRace/{raceHasAttributeId}")
    public String unlinkAttributeFromRace(@PathVariable("raceHasAttributeId") RaceHasAttribute raceHasAttribute,
                                          @RequestParam("raceId") Integer raceId) {

        raceHasAttributeService.deleteLinkAttributeWithRaceById(raceHasAttribute.getId());
        List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
        for (Personage personage : personages) {
            PersonageHasAttribute personageHasAttribute = personageHasAttributeService.
                    getPersonageHasAttributeByAttributeIdAndPersonageId(raceHasAttribute.getAttributeByRace().getId(), personage.getId());
            personageHasAttributeService.deleteLinkAttributeWithPersonage(personageHasAttribute);
        }

        return "redirect:/race/" + raceId;
    }

    @RequestMapping(value = "/race/linkMeritToRace", method = RequestMethod.POST)
    public String addRaceHasMerit(@Validated @ModelAttribute("raceHasMerit") RaceHasMerit raceHasMerit,
                                  BindingResult result) {

        raceHasMeritService.addLinkMeritWithRace(raceHasMerit);
        int raceId = raceHasMerit.getRaceByMerit().getId();
        if (raceHasMerit.isDefaultForRace()) {
            List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
            for (Personage personage : personages) {
                PersonageHasMerit personageHasMerit = new PersonageHasMerit();
                personageHasMerit.setMeritByPersonage(raceHasMerit.getMeritByRace());
                personageHasMerit.setPersonageByMerit(personage);
                personageHasMeritService.addLinkMeritWithPersonage(personageHasMerit);
            }
        }
        return "redirect:/race/" + raceId;
    }

    @RequestMapping(value = "/race/unlinkMeritFromRace/{raceHasMeritId}")
    public String unlinkMeritFromRace(@PathVariable("raceHasMeritId") RaceHasMerit raceHasMerit,
                                      @RequestParam("raceId") Integer raceId) {

        raceHasMeritService.deleteLinkMeritWithRace(raceHasMerit);
        if (raceHasMerit.isDefaultForRace()) {
            List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
            for (Personage personage : personages) {
                PersonageHasMerit personageHasMerit = personageHasMeritService.
                        getPersonageHasMeritByMeritIdAndPersonageId(raceHasMerit.getMeritByRace().getId(), personage.getId());
                personageHasMeritService.deleteLinkMeritWithPersonage(personageHasMerit);
            }
        }

        return "redirect:/race/" + raceId;
    }

    @RequestMapping(value = "/race/linkFlawToRace", method = RequestMethod.POST)
    public String addRaceHasFlaw(@Validated @ModelAttribute("raceHasFlaw") RaceHasFlaw raceHasFlaw,
                                 BindingResult result) {

        raceHasFlawService.addLinkFlawWithRace(raceHasFlaw);
        int raceId = raceHasFlaw.getRaceByFlaw().getId();
        if (raceHasFlaw.isDefaultForRace()) {
            List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
            for (Personage personage : personages) {
                PersonageHasFlaw personageHasFlaw = new PersonageHasFlaw();
                personageHasFlaw.setFlawByPersonage(raceHasFlaw.getFlawByRace());
                personageHasFlaw.setPersonageByFlaw(personage);
                personageHasFlawService.addLinkFlawWithPersonage(personageHasFlaw);
            }
        }
        return "redirect:/race/" + raceId;
    }

    @RequestMapping(value = "/race/unlinkFlawFromRace/{raceHasFlawId}")
    public String unlinkFlawFromRace(@PathVariable("raceHasFlawId") RaceHasFlaw raceHasFlaw,
                                     @RequestParam("raceId") Integer raceId) {

        raceHasFlawService.deleteLinkFlawWithRace(raceHasFlaw);
        if (raceHasFlaw.isDefaultForRace()) {
            List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
            for (Personage personage : personages) {
                PersonageHasFlaw personageHasFlaw = personageHasFlawService.
                        getPersonageHasFlawByFlawIdAndPersonageId(raceHasFlaw.getFlawByRace().getId(), personage.getId());
                personageHasFlawService.deleteLinkFlawWithPersonage(personageHasFlaw);
            }
        }

        return "redirect:/race/" + raceId;
    }
}
