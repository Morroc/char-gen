package services;

import DAO.AttachedSkillDAO;
import DAO.PersonageDAO;
import DAO.PersonageHasAttachedSkillDAO;
import entity.AttachedSkill;
import entity.PersonageHasAttachedSkill;
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
public class AttachedSkillService {
    @Autowired
    private AttachedSkillDAO attachedSkillDAO;

    @Autowired
    private PersonageHasAttachedSkillDAO personageHasAttachedSkillDAO;

    @Transactional
    public void addAttachedSkill(AttachedSkill attachedSkill) {
        attachedSkillDAO.addAttachedSkill(attachedSkill);
    }

    @Transactional
    public List<AttachedSkill> getAllAttachedSkills() {
        return attachedSkillDAO.getAllAttachedSkills();
    }

    @Transactional
    public void deleteAttachedSkillId(int attachedSkillId) {
        attachedSkillDAO.deleteAttachedSkill(attachedSkillDAO.getAttachedSkillById(attachedSkillId));
    }

    @Transactional
    public void addLinkWithPersonage(PersonageHasAttachedSkill personageHasAttachedSkill) {
        personageHasAttachedSkillDAO.addPersonageHasAttachedSkill(personageHasAttachedSkill);
    }

    @Transactional
    public AttachedSkill getAttachedSkillById(int attachedSkillId) {
        return attachedSkillDAO.getAttachedSkillById(attachedSkillId);
    }
}
