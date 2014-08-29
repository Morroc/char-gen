package converters;

import entity.AttachedSkill;
import web.rest.dto.AttachedSkillDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/29/14
 * Time: 6:10 PM
 */
public class AttachedSkillConverter {
    public List<AttachedSkillDTO> convert(List<AttachedSkill> allAttachedSkills) {
        List<AttachedSkillDTO> result = new ArrayList<AttachedSkillDTO>(allAttachedSkills.size());
        for (AttachedSkill attachedSkill : allAttachedSkills) {
            result.add(convert(attachedSkill));
        }
        return result;
    }

    public AttachedSkillDTO convert(AttachedSkill attachedSkill) {
        return new AttachedSkillDTO(attachedSkill.getId(), attachedSkill.getName(), attachedSkill.getBaseCost(),
                attachedSkill.isDefaultSkill(), attachedSkill.isDifficult(),
                attachedSkill.isTheoretical(), attachedSkill.getAcquiringCost());
    }
}
