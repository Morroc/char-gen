package converters;

import entity.PersonageHasTriggerSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.PersonageHasTriggerSkillService;

/**
 * User: artemk
 * Date: 8/18/14
 * Time: 6:58 PM
 */
public class StringToPersonageHasTriggerSkillConverter implements Converter<String, PersonageHasTriggerSkill> {
    @Autowired
    private PersonageHasTriggerSkillService personageHasTriggerSkillService;

    public PersonageHasTriggerSkill convert(String id) {
        return personageHasTriggerSkillService.getPersonageHasTriggerSkillById(Integer.parseInt(id));
    }
}
