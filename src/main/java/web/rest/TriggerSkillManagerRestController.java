package web.rest;

import entity.TriggerSkill;
import enums.SkillType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.TriggerSkillService;
import web.rest.dto.TriggerSkillDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/28/14
 * Time: 2:04 PM
 */
@RestController
@RequestMapping("/rest/triggerSkill")
public class TriggerSkillManagerRestController {
    @Autowired
    private TriggerSkillService triggerSkillService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<TriggerSkillDTO> listTriggerSkills() {
        return convert(triggerSkillService.getAllTriggerSkills());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public List<TriggerSkillDTO> deleteTriggerSkill(@PathVariable Integer id) {
        triggerSkillService.deleteTriggerSkillById(id);
        return listTriggerSkills();
    }

    private List<TriggerSkillDTO> convert(List<TriggerSkill> allTriggerSkills) {
        List<TriggerSkillDTO> result = new ArrayList<TriggerSkillDTO>(allTriggerSkills.size());
        for (TriggerSkill triggerSkill : allTriggerSkills) {
            result.add(new TriggerSkillDTO(triggerSkill.getId(), triggerSkill.getName(), triggerSkill.getType(),
                    triggerSkill.getBaseCost(), triggerSkill.getExpertCost(), triggerSkill.getMasterCost(), triggerSkill.getPostMasterCost()));
        }
        return result;
    }

    @RequestMapping(value = "/addTriggerSkill", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<TriggerSkillDTO> addTriggerSkill(@ModelAttribute("triggerSkill") TriggerSkill triggerSkill) {
        triggerSkillService.addTriggerSkill(triggerSkill);
        return listTriggerSkills();
    }

    @RequestMapping(value = "/skillTypes", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<SkillType> skillTypes() {
        List<SkillType> skillTypes = new ArrayList<SkillType>();
        for (SkillType skillType : SkillType.values()) {
            skillTypes.add(skillType);
        }
        return skillTypes;
    }
}
