package DAO;

import entity.PersonageHasTriggerSkill;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 3:49 PM
 */
public interface PersonageHasTriggerSkillDAO {
    public void addPersonageHasTriggerSkill(PersonageHasTriggerSkill personageHasTriggerSkill);

    public void updatePersonageHasTriggerSkill(PersonageHasTriggerSkill personageHasTriggerSkill);

    public PersonageHasTriggerSkill getPersonageHasTriggerSkillById(int personageHasTriggerSkillId);

    public List<PersonageHasTriggerSkill> getAllPersonageHasTriggerSkills();

    public void deletePersonageHasTriggerSkill(PersonageHasTriggerSkill personageHasTriggerSkill);

    public PersonageHasTriggerSkill getPersonageHasTriggerSkillByTriggerSkillIdAndPersonageId(int triggerSkillId, int personageId);

    public List<PersonageHasTriggerSkill> getPersonageHasTriggerSkillByPersonageId(int personageId);
}
