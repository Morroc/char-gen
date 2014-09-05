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
@RequestMapping(value = "/rest/attachedSkill", consumes = "application/json", produces = "application/json")
public class AttachedSkillManagerRestController {
    @Autowired
    private AttachedSkillService attachedSkillService;

    AttachedSkillConverter attachedSkillConverter = new AttachedSkillConverter();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<AttachedSkillDTO> listAttachedSkills() {
        return attachedSkillConverter.convert(attachedSkillService.getAllAttachedSkills());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public List<AttachedSkillDTO> deleteAttachedSkill(@PathVariable Integer id) {
        attachedSkillService.deleteAttachedSkillById(id);
        return listAttachedSkills();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public List<AttachedSkillDTO> updateAttachedSkill(@PathVariable Integer id, @RequestBody AttachedSkillDTO attachedSkillDTO) {
        attachedSkillDTO.setId(id);
        AttachedSkill attachedSkill = attachedSkillConverter.convert(attachedSkillDTO);
        attachedSkillService.updateAttachedSkill(attachedSkill);
        return listAttachedSkills();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public List<AttachedSkillDTO> addAttachedSkill(@RequestBody AttachedSkillDTO attachedSkillDTO) {

        AttachedSkill attachedSkill = attachedSkillConverter.convert(attachedSkillDTO);
        attachedSkillService.addAttachedSkill(attachedSkill);
        return listAttachedSkills();
    }
}
