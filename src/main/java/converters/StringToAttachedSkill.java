package converters;

import entity.AttachedSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.AttachedSkillService;

/**
 * User: artemk
 * Date: 8/14/14
 * Time: 2:54 PM
 */
public class StringToAttachedSkill implements Converter<String, AttachedSkill> {
    @Autowired
    private AttachedSkillService attachedSkillService;

    public AttachedSkill convert(String id) {
        return attachedSkillService.getAttachedSkillById(Integer.parseInt(id));
    }
}
