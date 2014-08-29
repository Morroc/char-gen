package web.rest;

import converters.AttachedSkillConverter;
import entity.AttachedSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.AttachedSkillService;
import web.rest.dto.AttachedSkillDTO;

import java.util.List;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 6:28 PM
 */
@RestController
@RequestMapping("/rest/attachedSkill")
public class AttachedSkillManagerRestController {
    @Autowired
    private AttachedSkillService attachedSkillService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<AttachedSkillDTO> listAttachedSkills() {
        AttachedSkillConverter attachedSkillConverter = new AttachedSkillConverter();
        return attachedSkillConverter.convert(attachedSkillService.getAllAttachedSkills());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public List<AttachedSkillDTO> deleteAttachedSkill(@PathVariable Integer id) {
        attachedSkillService.deleteAttachedSkillById(id);
        return listAttachedSkills();
    }

    @RequestMapping(value = "/addAttachedSkill", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<AttachedSkillDTO> addAttachedSkill(@ModelAttribute("attachedSkill") AttachedSkill attachedSkill) {
        attachedSkillService.addAttachedSkill(attachedSkill);
        return listAttachedSkills();
    }
}
