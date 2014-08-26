package web.rest;

import entity.AttachedSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.AttachedSkillService;
import web.rest.dto.AttachedSkillDTO;

import java.util.ArrayList;
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

    @RequestMapping(value="/all", method= RequestMethod.GET, headers="Accept=application/json")
    public List<AttachedSkillDTO> listRaces() {
        return convert(attachedSkillService.getAllAttachedSkills());
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public List<AttachedSkillDTO> deleteAttachedSkill(@PathVariable Integer id) {
        attachedSkillService.deleteAttachedSkillById(id);
        return listRaces();
    }

    private List<AttachedSkillDTO> convert(List<AttachedSkill> allAttachedSkills) {
        List<AttachedSkillDTO> result = new ArrayList<AttachedSkillDTO>(allAttachedSkills.size());
        for (AttachedSkill attachedSkill : allAttachedSkills) {
            result.add(new AttachedSkillDTO(attachedSkill.getId(), attachedSkill.getName(), attachedSkill.getBaseCost(),
                    attachedSkill.isDefaultSkill(), attachedSkill.isDifficult(),
                    attachedSkill.isTheoretical(), attachedSkill.getAcquiringCost()));
        }
        return result;
    }
}
