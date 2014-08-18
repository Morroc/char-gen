package services;

import DAO.PersonageDAO;
import DAO.TriggerSkillDAO;
import entity.AttachedSkill;
import entity.TriggerSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: artemk
 * Date: 8/13/14
 * Time: 3:58 PM
 */
@Service
public class TriggerSkillService {
    @Autowired
    private TriggerSkillDAO triggerSkillDAO ;

    @Autowired
    private PersonageDAO personageDAO;

    @Transactional
    public void addTriggerSkill(TriggerSkill triggerSkill) {
        triggerSkillDAO.addTriggerSkill(triggerSkill);
    }

    @Transactional
    public List<TriggerSkill> getAllTriggerSkills() {
        return triggerSkillDAO.getAllTriggerSkills();
    }

    @Transactional
    public void deleteTriggerSkillById(int triggerSkillId) {
        triggerSkillDAO.deleteTriggerSkill(triggerSkillDAO.getTriggerSkillById(triggerSkillId));
    }

    @Transactional
    public TriggerSkill getTriggerSkillById(int triggerSkillId) {
        return triggerSkillDAO.getTriggerSkillById(triggerSkillId);
    }

    @Transactional
    public List<TriggerSkill> getTriggerSkillsByPersonageId(int personageId) {
        return triggerSkillDAO.getTriggerSkillsByPersonage(personageDAO.getPersonageById(personageId));
    }
}
