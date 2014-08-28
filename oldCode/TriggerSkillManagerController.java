package web;

import entity.TriggerSkill;
import enums.SkillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.TriggerSkillService;

/**
 * User: artemk
 * Date: 8/18/14
 * Time: 4:44 PM
 */
@Controller
@SessionAttributes
public class TriggerSkillManagerController {
    @Autowired
    private TriggerSkillService triggerSkillService;

    @RequestMapping("/triggerSkillsManager")
    public String pageModel(Model model) {

        model.addAttribute("triggerSkill", new TriggerSkill());
        model.addAttribute("triggerSkillsList", triggerSkillService.getAllTriggerSkills());
        model.addAttribute("skillTypes", SkillType.values());

        return "trigger_skill_manager";
    }

    @RequestMapping(value = "/addTriggerSkill", method = RequestMethod.POST)
    public String addTriggerSkill(@ModelAttribute("triggerSkill") TriggerSkill triggerSkill,
                                   BindingResult result) {

        triggerSkillService.addTriggerSkill(triggerSkill);

        return "redirect:/triggerSkillsManager";
    }

    @RequestMapping("/deleteTriggerSkill/{triggerSkillId}")
    public String deleteTriggerSkill(@PathVariable("triggerSkillId") Integer triggerSkillId) {

        triggerSkillService.deleteTriggerSkillById(triggerSkillId);

        return "redirect:/triggerSkillsManager";
    }
}
