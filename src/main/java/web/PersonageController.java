package web;

import entity.PersonageHasAttachedSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import services.AttachedSkillService;
import services.PersonageHasAttachedSkillService;
import services.PersonageHasAttributeService;
import services.PersonageService;

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
    private PersonageHasAttachedSkillService personageHasAttachedSkillService;

    @Autowired
    private PersonageHasAttributeService personageHasAttributeService;

    @RequestMapping("/personage/{personageId}")
    public String pageModel(@PathVariable("personageId") Integer personageId, Model model) {

        model.addAttribute("personageHasAttachedSkill", new PersonageHasAttachedSkill());
        model.addAttribute("personageHasAttachedSkillsByPersonage", personageHasAttachedSkillService.getPersonageHasAttachedSkillsByPersonageId(personageId));
        model.addAttribute("personage", personageService.getPersonageById(personageId));
        model.addAttribute("allAttachedSkillsList", attachedSkillService.getAllAttachedSkills());
        model.addAttribute("personageHasAttributesByPersonage", personageHasAttributeService.getPersonageHasAttributesByPersonageId(personageId));

        return "personage";
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
}
