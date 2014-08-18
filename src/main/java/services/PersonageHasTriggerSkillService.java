package services;

import DAO.PersonageHasTriggerSkillDAO;
import entity.PersonageHasTriggerSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: artemk
 * Date: 8/18/14
 * Time: 4:41 PM
 */
@Service
public class PersonageHasTriggerSkillService {
    @Autowired
    private PersonageHasTriggerSkillDAO personageHasTriggerSkillDAO;

    @Transactional
    public PersonageHasTriggerSkill getPersonageHasTriggerSkillByTriggerSkillIdAndPersonageId(int triggerSkillId, int personageId) {
        return personageHasTriggerSkillDAO.getPersonageHasTriggerSkillByTriggerSkillIdAndPersonageId(triggerSkillId, personageId);
    }

    @Transactional
    public List<PersonageHasTriggerSkill> getPersonageHasTriggerSkillsByPersonageId(int personageId) {
        return personageHasTriggerSkillDAO.getPersonageHasTriggerSkillByPersonageId(personageId);
    }

    @Transactional
    public PersonageHasTriggerSkill getPersonageHasTriggerSkillById(int personageHasTriggerSkillId) {
        return personageHasTriggerSkillDAO.getPersonageHasTriggerSkillById(personageHasTriggerSkillId);
    }

    @Transactional
    public void addLinkTriggerSkillWithPersonage(PersonageHasTriggerSkill personageHasTriggerSkill) {
        personageHasTriggerSkillDAO.addPersonageHasTriggerSkill(personageHasTriggerSkill);
    }

    @Transactional
    public void deleteLinkTriggerSkillWithPersonage(PersonageHasTriggerSkill personageHasTriggerSkill) {
        personageHasTriggerSkillDAO.deletePersonageHasTriggerSkill(personageHasTriggerSkill);
    }

    @Transactional
    public void updatePersonageHasTriggerSkill(PersonageHasTriggerSkill personageHasTriggerSkill) {
        personageHasTriggerSkillDAO.updatePersonageHasTriggerSkill(personageHasTriggerSkill);
    }
}
