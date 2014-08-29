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
            result.add(new TriggerSkillDTO(triggerSkill.getId(), triggerSkill.getName(), triggerSkill.getType(),
                    triggerSkill.getBaseCost(), triggerSkill.getExpertCost(), triggerSkill.getMasterCost(), triggerSkill.getPostMasterCost()));
        }
        return result;
    }
}
