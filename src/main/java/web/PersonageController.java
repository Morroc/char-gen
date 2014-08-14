package web;

import entity.PersonageHasAttachedSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import services.AttachedSkillService;
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

    @RequestMapping("/personage/{personageId}")
    public String personage(@PathVariable("personageId") Integer personageId, Model model) {

        model.addAttribute("personageHasAttachedSkill", new PersonageHasAttachedSkill());
        model.addAttribute("personage", personageService.getPersonageById(personageId));
        model.addAttribute("attachedSkillsListByPersonage", attachedSkillService.getAttachedSkillsByPersonageId(personageId));
        model.addAttribute("allAttachedSkillsList", attachedSkillService.getAllAttachedSkills());

        return "personage";
    }

    @RequestMapping(value = "/personage/linkAttachedSkillToPersonage", method = RequestMethod.POST)
    public String addPersonageHasAttachedSkill(@Validated @ModelAttribute("personageHasAttachedSkill") PersonageHasAttachedSkill personageHasAttachedSkill,
                                               BindingResult result) {

        attachedSkillService.addLinkWithPersonage(personageHasAttachedSkill);
        int personageId = personageHasAttachedSkill.getPersonageByAttachedSkill().getId();

        return "redirect:/personage/" + personageId;
    }

    @RequestMapping("/personage/unlinkAttachedSkill/{attachedSkillId}")
    public String unlinkAttachedSkill(@PathVariable("attachedSkillId") Integer attachedSkillId) {

        PersonageHasAttachedSkill personageHasAttachedSkill = attachedSkillService.getPersonageHasAttachedSkillByAttachedSkillId(attachedSkillId);
        int personageId = personageHasAttachedSkill.getPersonageByAttachedSkill().getId();
        attachedSkillService.deleteLinkWithPersonage(personageHasAttachedSkill);

        return "redirect:/personage/" + personageId;
    }
}
