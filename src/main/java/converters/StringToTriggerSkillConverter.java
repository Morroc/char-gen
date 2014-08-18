package converters;

import entity.PersonageHasTriggerSkill;
import entity.TriggerSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.TriggerSkillService;

/**
 * User: artemk
 * Date: 8/18/14
 * Time: 6:51 PM
 */
public class StringToTriggerSkillConverter implements Converter<String, TriggerSkill> {
    @Autowired
    private TriggerSkillService triggerSkillService;

    public TriggerSkill convert(String id) {
        return triggerSkillService.getTriggerSkillById(Integer.parseInt(id));
    }
}
