package web;

import enums.SkillLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/14/14
 * Time: 5:29 PM
 */
@Controller
@SessionAttributes
public class PersonageController {
    @Autowired
    private PersonageService personageService;

    @Autowired
    private AttachedSkillService attachedSkillService;

    @Autowired
    private TriggerSkillService triggerSkillService;

    @Autowired
    private PersonageHasAttachedSkillService personageHasAttachedSkillService;

    @Autowired
    private PersonageHasTriggerSkillService personageHasTriggerSkillService;

    @Autowired
    private PersonageHasAttributeService personageHasAttributeService;

    @Autowired
    private PersonageHasMeritService personageHasMeritService;

    @Autowired
    private RaceHasMeritService raceHasMeritService;

    @Autowired
    private MeritService meritService;

    @Autowired
    private FlawService flawService;

    @Autowired
    private PersonageHasFlawService personageHasFlawService;

    @Autowired
    private RaceHasFlawService raceHasFlawService;

    @RequestMapping("/personage/{personageId}")
    public String pageModel(@PathVariable("personageId") Integer personageId, Model model) {

        Personage personage = personageService.getPersonageById(personageId);
        //personage
        model.addAttribute("personage", personage);
        //attributes
        model.addAttribute("personageHasAttributesByPersonage", personageHasAttributeService.getPersonageHasAttributesByPersonageId(personageId));
        //attached skills
        model.addAttribute("allAttachedSkillsList", attachedSkillService.getAllAttachedSkills());
        model.addAttribute("personageHasAttachedSkill", new PersonageHasAttachedSkill());
        model.addAttribute("personageHasAttachedSkillsByPersonage", personageHasAttachedSkillService.getPersonageHasAttachedSkillsByPersonageId(personageId));
        //trigger skills
        model.addAttribute("allTriggerSkillsList", triggerSkillService.getAllTriggerSkills());
        model.addAttribute("personageHasTriggerSkill", new PersonageHasTriggerSkill());
        model.addAttribute("personageHasTriggerSkillsByPersonage", personageHasTriggerSkillService.getPersonageHasTriggerSkillsByPersonageId(personageId));
        model.addAttribute("skillLevels", SkillLevel.values());

        //all races has merits
        List<RaceHasMerit> raceHasMerits = raceHasMeritService.getRaceHasMeritsByRaceId(personage.getRace().getId());
        List<Merit> allMerits = meritService.getAllMerits();
        //merits
        List<RaceHasMerit> defaultForRaceMerits = new ArrayList<RaceHasMerit>();
        List<RaceHasMerit> withDifferentCostForRaceMerits = new ArrayList<RaceHasMerit>();
        List<PersonageHasMerit> onlyForPersonageMerits = new ArrayList<PersonageHasMerit>();
        List<Merit> allMeritsWithoutRacesMerits = new ArrayList<Merit>();
        allMeritsWithoutRacesMerits.addAll(allMerits);
        List<Merit> removeListForPersonageMerits = new ArrayList<Merit>();
        if (raceHasMerits.isEmpty()) {
            onlyForPersonageMerits.addAll(personageHasMeritService.getPersonageHasMeritsByPersonageId(personageId));
        } else {
            for (RaceHasMerit raceHasMerit : raceHasMerits) {
                if (raceHasMerit.isDefaultForRace()) {
                    defaultForRaceMerits.add(raceHasMerit);
                    for (Merit merit : allMeritsWithoutRacesMerits) {
                        if (raceHasMerit.getMeritByRace().getId() == merit.getId()) {
                            removeListForPersonageMerits.add(merit);
                        }
                    }
                } else if (raceHasMerit.getRaceCost() != 0) {
                    for (Merit merit : allMeritsWithoutRacesMerits) {
                        if (raceHasMerit.getMeritByRace().getId() == merit.getId()) {
                            removeListForPersonageMerits.add(merit);
                        }
                    }
                    List<PersonageHasMerit> personageHasMerits = personageHasMeritService.getPersonageHasMeritsByPersonageId(personageId);
                    for (PersonageHasMerit personageHasMerit : personageHasMerits) {
                        RaceHasMerit raceHasMeritForCurrentPersonageMerit = raceHasMeritService.
                                getRaceHasMeritByMeritIdAndRaceId(personageHasMerit.getMeritByPersonage().getId(),
                                        personage.getRace().getId());
                        if (personageHasMerit.getMeritByPersonage().getId() == raceHasMerit.getMeritByRace().getId()) {
                            withDifferentCostForRaceMerits.add(raceHasMerit);
                        } else if (raceHasMeritForCurrentPersonageMerit == null) {
                            onlyForPersonageMerits.add(personageHasMerit);
                        } else if (!raceHasMeritForCurrentPersonageMerit.isDefaultForRace()) {
                            onlyForPersonageMerits.add(personageHasMerit);
                        }
                    }
                }
            }
        }
        allMeritsWithoutRacesMerits.removeAll(removeListForPersonageMerits);

        List<RaceHasMerit> raceHasMeritsWithoutDefaults = raceHasMerits;
        raceHasMeritsWithoutDefaults.removeAll(defaultForRaceMerits);

        //default for race
        model.addAttribute("defaultForRaceMerits", defaultForRaceMerits);

        //with different cost for race
        model.addAttribute("withDifferentCostForRaceMerits", withDifferentCostForRaceMerits);

        //only for personage
        model.addAttribute("onlyForPersonageMerits", onlyForPersonageMerits);

        //race merits
        model.addAttribute("raceHasMeritsWithoutDefaults", raceHasMeritsWithoutDefaults);

        //all merits
        model.addAttribute("allMeritsWithoutRacesMerits", allMeritsWithoutRacesMerits);
        model.addAttribute("personageHasMerit", new PersonageHasMerit());

        //flaws
        model.addAttribute("personageHasFlaw", new PersonageHasFlaw());
        model.addAttribute("personageHasFlawsByPersonage", personageHasFlawService.getPersonageHasFlawsByPersonageId(personageId));

        List<RaceHasFlaw> raceHasFlaws = raceHasFlawService.getRaceHasFlawsByRaceId(personage.getRace().getId());
        List<PersonageHasFlaw> allPersonageHasFlaws = personageHasFlawService.getPersonageHasFlawsByPersonageId(personageId);

        List<RaceHasFlaw> defaultForRaceFlaws = new ArrayList<RaceHasFlaw>();

        if (!raceHasFlaws.isEmpty()) {
            for (RaceHasFlaw raceHasFlaw : raceHasFlaws) {
                if (raceHasFlaw.isDefaultForRace()) {
                    defaultForRaceFlaws.add(raceHasFlaw);
                }
            }
        }

        List<PersonageHasFlaw> allPersonageHasFlawsWithoutDefaultForRace = new ArrayList<PersonageHasFlaw>();

        if (!raceHasFlaws.isEmpty()) {
            for (PersonageHasFlaw personageHasFlaw : allPersonageHasFlaws) {
                for (RaceHasFlaw defaultRaceHasFlaw : defaultForRaceFlaws) {
                    if (personageHasFlaw.getFlawByPersonage().getId() != defaultRaceHasFlaw.getFlawByRace().getId()) {
                        allPersonageHasFlawsWithoutDefaultForRace.add(personageHasFlaw);
                    }
                }
            }
        } else {
            allPersonageHasFlawsWithoutDefaultForRace.addAll(allPersonageHasFlaws);
        }

        model.addAttribute("defaultForRaceFlaws", defaultForRaceFlaws);

        model.addAttribute("allPersonageHasFlawsWithoutDefaultForRace", allPersonageHasFlawsWithoutDefaultForRace);

        List<Flaw> allFlawsWithoutDefaultForRace = flawService.getAllFlaws();
        List<Flaw> removeFlawsWithoutDefaultForRace = new ArrayList<Flaw>();
        if (!raceHasFlaws.isEmpty()) {
            for (RaceHasFlaw raceHasFlaw : defaultForRaceFlaws) {
                for (Flaw flaw : allFlawsWithoutDefaultForRace) {
                    if (raceHasFlaw.isDefaultForRace()) {
                        if (raceHasFlaw.getFlawByRace().getId() == flaw.getId()) {
                            removeFlawsWithoutDefaultForRace.add(flaw);
                        }
                    }
                }
            }
        }

        allFlawsWithoutDefaultForRace.removeAll(removeFlawsWithoutDefaultForRace);

        model.addAttribute("allFlawsWithoutDefaultForRace", allFlawsWithoutDefaultForRace);

        return "oldCode/personage.jsp";
    }

    @RequestMapping(value = "/personage/updateAttribute/{personageHasAttributeId}")
    public String updateAttribute(@PathVariable("personageHasAttributeId") Integer personageHasAttributeId,
                                  @RequestParam("addOrRemove") String addOrRemove) {

        PersonageHasAttribute personageHasAttribute = personageHasAttributeService.getPersonageHasAttributeById(personageHasAttributeId);
        if (addOrRemove.equals("add")) {
            personageHasAttribute.setCurrentValue(personageHasAttribute.getCurrentValue() + 1);
        }
        if (addOrRemove.equals("remove")) {
            personageHasAttribute.setCurrentValue(personageHasAttribute.getCurrentValue() - 1);
        }
        personageHasAttributeService.updatePersonageHasAttribute(personageHasAttribute);
        int personageId = personageHasAttribute.getPersonageByAttribute().getId();

        return "redirect:/personage/" + personageId;
    }

    @RequestMapping(value = "/personage/updateAttachedSkill/{personageHasAttachedSkillId}")
    public String updateAttachedSkill(@PathVariable("personageHasAttachedSkillId") Integer personageHasAttachedSkillId,
                                      @RequestParam("addOrRemove") String addOrRemove) {

        PersonageHasAttachedSkill personageHasAttachedSkill = personageHasAttachedSkillService
                .getPersonageHasAttachedSkillById(personageHasAttachedSkillId);
        if (addOrRemove.equals("add")) {
            personageHasAttachedSkill.setCurrentValue(personageHasAttachedSkill.getCurrentValue() + 1);
        }
        if (addOrRemove.equals("remove")) {
            personageHasAttachedSkill.setCurrentValue(personageHasAttachedSkill.getCurrentValue() - 1);
        }
        personageHasAttachedSkillService.updatePersonageHasAttachedSkill(personageHasAttachedSkill);
        int personageId = personageHasAttachedSkill.getPersonageByAttachedSkill().getId();

        return "redirect:/personage/" + personageId;
    }

    @RequestMapping(value = "/personage/linkAttachedSkillToPersonage", method = RequestMethod.POST)
    public String addPersonageHasAttachedSkill(@Validated @ModelAttribute("personageHasAttachedSkill") PersonageHasAttachedSkill personageHasAttachedSkill,
                                               BindingResult result) {

        personageHasAttachedSkillService.addLinkAttachedSkillWithPersonage(personageHasAttachedSkill);
        int personageId = personageHasAttachedSkill.getPersonageByAttachedSkill().getId();

        return "redirect:/personage/" + personageId;
    }

    @RequestMapping(value = "/personage/unlinkAttachedSkillFromPersonage/{personageHasAttachedSkillId}")
    public String unlinkAttachedSkillFromPersonage(@PathVariable("personageHasAttachedSkillId") PersonageHasAttachedSkill personageHasAttachedSkill,
                                                   @RequestParam("personageId") Integer personageId) {

        personageHasAttachedSkillService.deleteLinkAttachedSkillWithPersonage(personageHasAttachedSkill);

        return "redirect:/personage/" + personageId;
    }

    @RequestMapping(value = "/personage/linkTriggerSkillToPersonage", method = RequestMethod.POST)
    public String addPersonageHasTriggerSkill(@Validated @ModelAttribute("personageHasTriggerSkill") PersonageHasTriggerSkill personageHasTriggerSkill,
                                              BindingResult result) {

        personageHasTriggerSkillService.addLinkTriggerSkillWithPersonage(personageHasTriggerSkill);
        int personageId = personageHasTriggerSkill.getPersonageByTriggerSkill().getId();

        return "redirect:/personage/" + personageId;
    }

    @RequestMapping(value = "/personage/unlinkTriggerSkillFromPersonage/{personageHasTriggerSkillId}")
    public String unlinkTriggerSkillFromPersonage(@PathVariable("personageHasTriggerSkillId") PersonageHasTriggerSkill personageHasTriggerSkill,
                                                  @RequestParam("personageId") Integer personageId) {

        personageHasTriggerSkillService.deleteLinkTriggerSkillWithPersonage(personageHasTriggerSkill);

        return "redirect:/personage/" + personageId;
    }

    @RequestMapping(value = "/personage/linkMeritToPersonage", method = RequestMethod.POST)
    public String addPersonageHasMerit(@Validated @ModelAttribute("personageHasMerit") PersonageHasMerit personageHasMerit,
                                       BindingResult result) {

        personageHasMeritService.addLinkMeritWithPersonage(personageHasMerit);
        int personageId = personageHasMerit.getPersonageByMerit().getId();

        return "redirect:/personage/" + personageId;
    }

    @RequestMapping(value = "/personage/unlinkMeritFromPersonage/{personageHasMeritId}")
    public String unlinkMeritFromPersonage(@PathVariable("personageHasMeritId") PersonageHasMerit personageHasMerit,
                                           @RequestParam("personageId") Integer personageId) {

        personageHasMeritService.deleteLinkMeritWithPersonage(personageHasMerit);

        return "redirect:/personage/" + personageId;
    }

    @RequestMapping(value = "/personage/unlinkMeritFromPersonageByRaceHasMerit/{raceHasMerit}")
    public String unlinkMeritFromPersonageByRaceHasMerit(@PathVariable("raceHasMerit") RaceHasMerit raceHasMerit,
                                                         @RequestParam("personageId") Integer personageId) {

        PersonageHasMerit personageHasMerit = personageHasMeritService.
                getPersonageHasMeritByMeritIdAndPersonageId(raceHasMerit.getMeritByRace().getId(), personageId);
        personageHasMeritService.deleteLinkMeritWithPersonage(personageHasMerit);

        return "redirect:/personage/" + personageId;
    }

    @RequestMapping(value = "/personage/linkFlawToPersonage", method = RequestMethod.POST)
    public String addPersonageHasFlaw(@Validated @ModelAttribute("personageHasFlaw") PersonageHasFlaw personageHasFlaw,
                                      BindingResult result) {

        personageHasFlawService.addLinkFlawWithPersonage(personageHasFlaw);
        int personageId = personageHasFlaw.getPersonageByFlaw().getId();

        return "redirect:/personage/" + personageId;
    }

    @RequestMapping(value = "/personage/unlinkFlawFromPersonage/{personageHasFlawId}")
    public String unlinkFlawFromPersonage(@PathVariable("personageHasFlawId") PersonageHasFlaw personageHasFlaw,
                                          @RequestParam("personageId") Integer personageId) {

        personageHasFlawService.deleteLinkFlawWithPersonage(personageHasFlaw);

        return "redirect:/personage/" + personageId;
    }
}
