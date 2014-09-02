package converters;

import entity.PersonageHasAttachedSkill;
import web.rest.dto.PersonageHasAttachedSkillDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 9/2/14
 * Time: 3:58 PM
 */
public class PersonageHasAttachedSkillConverter {
    AttachedSkillConverter attachedSkillConverter = new AttachedSkillConverter();

    public PersonageHasAttachedSkillDTO convert(PersonageHasAttachedSkill personageHasAttachedSkill) {
        return new PersonageHasAttachedSkillDTO(personageHasAttachedSkill.getId(),
                attachedSkillConverter.convert(personageHasAttachedSkill.getAttachedSkillByPersonage()), personageHasAttachedSkill.getCurrentValue());
    }

    public List<PersonageHasAttachedSkillDTO> convert(List<PersonageHasAttachedSkill> allPersonageHasAttachedSkills) {
        List<PersonageHasAttachedSkillDTO> result = new ArrayList<PersonageHasAttachedSkillDTO>(allPersonageHasAttachedSkills.size());
        for (PersonageHasAttachedSkill personageHasAttachedSkill : allPersonageHasAttachedSkills) {
            result.add(convert(personageHasAttachedSkill));
        }
        return result;
    }
}
