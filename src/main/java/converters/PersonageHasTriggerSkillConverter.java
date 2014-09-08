package converters;


import entity.PersonageHasTriggerSkill;
import web.rest.dto.PersonageHasTriggerSkillDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 9/2/14
 * Time: 4:41 PM
 */
public class PersonageHasTriggerSkillConverter {
    TriggerSkillConverter triggerSkillConverter = new TriggerSkillConverter();
    PersonageConverter personageConverter = new PersonageConverter();

    public PersonageHasTriggerSkill convert(PersonageHasTriggerSkillDTO personageHasTriggerSkillDTO) {
        return new PersonageHasTriggerSkill(personageHasTriggerSkillDTO.getId(),
                triggerSkillConverter.convert(personageHasTriggerSkillDTO.getTriggerSkill()),
                personageConverter.convert(personageHasTriggerSkillDTO.getPersonage()),
                personageHasTriggerSkillDTO.getCurrentLevel(), personageHasTriggerSkillDTO.isHasTalent(),
                personageHasTriggerSkillDTO.isHasTeacher());
    }

    public PersonageHasTriggerSkillDTO convert(PersonageHasTriggerSkill personageHasTriggerSkill) {
        return new PersonageHasTriggerSkillDTO(personageHasTriggerSkill.getId(),
                triggerSkillConverter.convert(personageHasTriggerSkill.getTriggerSkillByPersonage()),
                personageConverter.convert(personageHasTriggerSkill.getPersonageByTriggerSkill()),
                personageHasTriggerSkill.getCurrentLevel(), personageHasTriggerSkill.isHasTalent(),
                personageHasTriggerSkill.isHasTeacher());
    }

    public List<PersonageHasTriggerSkillDTO> convert(List<PersonageHasTriggerSkill> allPersonageHasTriggerSkills) {
        List<PersonageHasTriggerSkillDTO> result = new ArrayList<PersonageHasTriggerSkillDTO>(allPersonageHasTriggerSkills.size());
        for (PersonageHasTriggerSkill personageHasTriggerSkill : allPersonageHasTriggerSkills) {
            result.add(convert(personageHasTriggerSkill));
        }
        return result;
    }

}
