package DAO;

import entity.AttachedSkill;

import java.sql.SQLException;
import java.util.Collection;

import entity.Personage;

/**
 * User: artemk
 * Date: 8/2/14
 * Time: 10:02 PM
 */
public interface AttachedSkillDAO {
    public void addAttachedSkill(AttachedSkill attachedSkill) throws SQLException;

    public void updateAttachedSkill(AttachedSkill attachedSkill) throws SQLException;

    public AttachedSkill getAttachedSkillById(int attachedSkillId) throws SQLException;

    public Collection getAllAttachedSkills() throws SQLException;

    public void deleteAttachedSkill(AttachedSkill attachedSkill) throws SQLException;

    public Collection getAttachedSkillsByPersonage(Personage personage) throws SQLException;
}
