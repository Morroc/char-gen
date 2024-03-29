package web;

import entity.AttachedSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.AttachedSkillService;

/**
 * User: artemk
 * Date: 8/13/14
 * Time: 6:40 PM
 */
@Controller
@SessionAttributes
public class AttachedSkillManagerController {
    @Autowired
    private AttachedSkillService attachedSkillService;

    @RequestMapping("/attachedSkillsManager")
    public String pageModel(Model model) {

        model.addAttribute("attachedSkill", new AttachedSkill());
        model.addAttribute("attachedSkillsList", attachedSkillService.getAllAttachedSkills());

        return "oldCode/attached_skill_manager.jsp";
    }

    @RequestMapping(value = "/addAttachedSkill", method = RequestMethod.POST)
    public String addAttachedSkill(@ModelAttribute("attachedSkill") AttachedSkill attachedSkill,
                          BindingResult result) {

        attachedSkillService.addAttachedSkill(attachedSkill);

        return "redirect:/attachedSkillsManager";
    }

    @RequestMapping("/deleteAttachedSkill/{attachedSkillId}")
    public String deleteAttachedSkill(@PathVariable("attachedSkillId") Integer attachedSkillId) {

        attachedSkillService.deleteAttachedSkillById(attachedSkillId);

        return "redirect:/attachedSkillsManager";
    }
}
