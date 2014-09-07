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
    PersonageConverter personageConverter = new PersonageConverter();

    public PersonageHasAttachedSkill convert(PersonageHasAttachedSkillDTO personageHasAttachedSkillDTO) {
        return new PersonageHasAttachedSkill(personageHasAttachedSkillDTO.getId(),
                attachedSkillConverter.convert(personageHasAttachedSkillDTO.getAttachedSkill()),
                personageConverter.convert(personageHasAttachedSkillDTO.getPersonage()),
                personageHasAttachedSkillDTO.getCurrentValue());
    }

    public PersonageHasAttachedSkillDTO convert(PersonageHasAttachedSkill personageHasAttachedSkill) {
        return new PersonageHasAttachedSkillDTO(personageHasAttachedSkill.getId(),
                attachedSkillConverter.convert(personageHasAttachedSkill.getAttachedSkillByPersonage()),
                personageConverter.convert(personageHasAttachedSkill.getPersonageByAttachedSkill()),
                personageHasAttachedSkill.getCurrentValue());
    }

    public List<PersonageHasAttachedSkillDTO> convert(List<PersonageHasAttachedSkill> allPersonageHasAttachedSkills) {
        List<PersonageHasAttachedSkillDTO> result = new ArrayList<PersonageHasAttachedSkillDTO>(allPersonageHasAttachedSkills.size());
        for (PersonageHasAttachedSkill personageHasAttachedSkill : allPersonageHasAttachedSkills) {
            result.add(convert(personageHasAttachedSkill));
        }
        return result;
    }
}
