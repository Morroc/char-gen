package web;

import entity.PersonageHasAttachedSkill;
import entity.PersonageHasAttribute;
import entity.PersonageHasTriggerSkill;
import enums.SkillLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import services.*;

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

    @RequestMapping("/personage/{personageId}")
    public String pageModel(@PathVariable("personageId") Integer personageId, Model model) {

        //personage
        model.addAttribute("personage", personageService.getPersonageById(personageId));
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

        return "personage";
    }

    @RequestMapping(value = "/personage/updateAttribute/{personageHasAttributeId}")
    public String updateAttribute(@PathVariable("personageHasAttributeId") Integer personageHasAttributeId,
                                  @RequestParam("addOrRemove") String addOrRemove) {

        PersonageHasAttribute personageHasAttribute = personageHasAttributeService.getPersonageHasAttributeById(personageHasAttributeId);
        if(addOrRemove.equals("add")) {
            personageHasAttribute.setCurrentValue(personageHasAttribute.getCurrentValue() + 1);
        }
        if(addOrRemove.equals("remove")) {
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
        if(addOrRemove.equals("add")) {
            personageHasAttachedSkill.setCurrentValue(personageHasAttachedSkill.getCurrentValue() + 1);
        }
        if(addOrRemove.equals("remove")) {
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
}
