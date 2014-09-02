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

    public PersonageHasTriggerSkillDTO convert(PersonageHasTriggerSkill personageHasTriggerSkill) {
        return new PersonageHasTriggerSkillDTO(personageHasTriggerSkill.getId(),
                triggerSkillConverter.convert(personageHasTriggerSkill.getTriggerSkillByPersonage()),
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
