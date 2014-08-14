package DAO;

import entity.PersonageHasAttachedSkill;

import java.util.List;

/**
 * User: artemk
 * Date: 8/14/14
 * Time: 12:41 PM
 */
public interface PersonageHasAttachedSkillDAO {
    public void addPersonageHasAttachedSkill(PersonageHasAttachedSkill personageHasAttachedSkill);

    public void updatePersonageHasAttachedSkill(PersonageHasAttachedSkill personageHasAttachedSkill);

    public PersonageHasAttachedSkill getPersonageHasAttachedSkillById(int personageHasAttachedSkillId);

    public List<PersonageHasAttachedSkill> getAllPersonageHasAttachedSkills();

    public void deletePersonageHasAttachedSkill(PersonageHasAttachedSkill personageHasAttachedSkill);
}
