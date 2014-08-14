package converters;

import entity.PersonageHasAttachedSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.PersonageHasAttachedSkillService;

/**
 * User: artemk
 * Date: 8/14/14
 * Time: 6:52 PM
 */
public class StringToPersonageHasAttachedSkillConverter implements Converter<String, PersonageHasAttachedSkill> {
    @Autowired
    private PersonageHasAttachedSkillService personageHasAttachedSkillService;

    public PersonageHasAttachedSkill convert(String id) {
        return personageHasAttachedSkillService.getPersonageHasAttachedSkillId(Integer.parseInt(id));
    }
}
