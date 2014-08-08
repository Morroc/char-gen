package DAO;

import entity.AttachedSkill;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import entity.Personage;

/**
 * User: artemk
 * Date: 8/2/14
 * Time: 10:02 PM
 */
public interface AttachedSkillDAO {
    public void addAttachedSkill(AttachedSkill attachedSkill);

    public void updateAttachedSkill(AttachedSkill attachedSkill);

    public AttachedSkill getAttachedSkillById(int attachedSkillId);

    public AttachedSkill getAttachedSkillByName(String attachedSkillName);

    public List<AttachedSkill> getAllAttachedSkills();

    public void deleteAttachedSkill(AttachedSkill attachedSkill);

    public List<AttachedSkill> getAttachedSkillsByPersonage(Personage personage);
}
