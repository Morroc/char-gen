package converters;

import entity.TriggerSkill;
import web.rest.dto.TriggerSkillDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/29/14
 * Time: 6:17 PM
 */
public class TriggerSkillConverter {
    public List<TriggerSkillDTO> convert(List<TriggerSkill> allTriggerSkills) {
        List<TriggerSkillDTO> result = new ArrayList<TriggerSkillDTO>(allTriggerSkills.size());
        for (TriggerSkill triggerSkill : allTriggerSkills) {
            result.add(convert(triggerSkill));
        }
        return result;
    }

    public TriggerSkillDTO convert(TriggerSkill triggerSkill) {
        return new TriggerSkillDTO(triggerSkill.getId(), triggerSkill.getName(), triggerSkill.getType(),
                triggerSkill.getBaseCost(), triggerSkill.getExpertCost(),
                triggerSkill.getMasterCost(), triggerSkill.getPostMasterCost());
    }

    public TriggerSkill convert(TriggerSkillDTO triggerSkillDTO) {
        return new TriggerSkill(triggerSkillDTO.getId(), triggerSkillDTO.getName(), triggerSkillDTO.getType(),
                triggerSkillDTO.getBaseCost(), triggerSkillDTO.getExpertCost(),
                triggerSkillDTO.getMasterCost(), triggerSkillDTO.getPostMasterCost());
    }
}
