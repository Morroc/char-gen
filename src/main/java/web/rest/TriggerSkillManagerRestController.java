package web.rest;

import converters.TriggerSkillConverter;
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
@RequestMapping(value = "/rest/triggerSkill", consumes = "application/json", produces = "application/json")
public class TriggerSkillManagerRestController {
    @Autowired
    private TriggerSkillService triggerSkillService;

    TriggerSkillConverter triggerSkillConverter = new TriggerSkillConverter();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<TriggerSkillDTO> listTriggerSkills() {
        return triggerSkillConverter.convert(triggerSkillService.getAllTriggerSkills());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public List<TriggerSkillDTO> deleteTriggerSkill(@PathVariable Integer id) {
        triggerSkillService.deleteTriggerSkillById(id);
        return listTriggerSkills();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public List<TriggerSkillDTO> addTriggerSkill(@RequestBody TriggerSkillDTO triggerSkillDTO) {
        TriggerSkill triggerSkill = triggerSkillConverter.convert(triggerSkillDTO);

        triggerSkillService.addTriggerSkill(triggerSkill);
        return listTriggerSkills();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public List<TriggerSkillDTO> updateTriggerSkill(@PathVariable Integer id, @RequestBody TriggerSkillDTO triggerSkillDTO) {
        triggerSkillDTO.setId(id);
        TriggerSkill triggerSkill = triggerSkillConverter.convert(triggerSkillDTO);
        triggerSkillService.updateTriggerSkill(triggerSkill);
        return listTriggerSkills();
    }

    @RequestMapping(value = "/skillTypes", method = RequestMethod.GET)
    public List<SkillType> skillTypes() {
        List<SkillType> skillTypes = new ArrayList<SkillType>();
        for (SkillType skillType : SkillType.values()) {
            skillTypes.add(skillType);
        }
        return skillTypes;
    }
}
