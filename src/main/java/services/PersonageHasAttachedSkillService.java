package services;

import DAO.PersonageHasAttachedSkillDAO;
import entity.PersonageHasAttachedSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: artemk
 * Date: 8/14/14
 * Time: 5:43 PM
 */
@Service
public class PersonageHasAttachedSkillService {
    @Autowired
    private PersonageHasAttachedSkillDAO personageHasAttachedSkillDAO;

    @Transactional
    public PersonageHasAttachedSkill getPersonageHasAttachedSkillByAttachedSkillIdAndPersonageId(int attachedSkillId, int personageId) {
        return personageHasAttachedSkillDAO.getPersonageHasAttachedSkillByAttachedSkillIdAndPersonageId(attachedSkillId, personageId);
    }

    @Transactional
    public List<PersonageHasAttachedSkill> getPersonageHasAttachedSkillsByPersonageId(int personageId) {
        return personageHasAttachedSkillDAO.getPersonageHasAttachedSkillByPersonageId(personageId);
    }

    @Transactional
    public PersonageHasAttachedSkill getPersonageHasAttachedSkillById(int personageHasAttachedSkillId) {
        return personageHasAttachedSkillDAO.getPersonageHasAttachedSkillById(personageHasAttachedSkillId);
    }

    @Transactional
    public void addLinkAttachedSkillWithPersonage(PersonageHasAttachedSkill personageHasAttachedSkill) {
        personageHasAttachedSkillDAO.addPersonageHasAttachedSkill(personageHasAttachedSkill);
    }

    @Transactional
    public void deleteLinkAttachedSkillWithPersonage(PersonageHasAttachedSkill personageHasAttachedSkill) {
        personageHasAttachedSkillDAO.deletePersonageHasAttachedSkill(personageHasAttachedSkill);
    }

    @Transactional
    public void updatePersonageHasAttachedSkill(PersonageHasAttachedSkill personageHasAttachedSkill) {
        personageHasAttachedSkillDAO.updatePersonageHasAttachedSkill(personageHasAttachedSkill);
    }
}
