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
    public void addAttachedSkill(AttachedSkill attachedSkill) throws SQLException;

    public void updateAttachedSkill(AttachedSkill attachedSkill) throws SQLException;

    public AttachedSkill getAttachedSkillById(int attachedSkillId) throws SQLException;

    public List<AttachedSkill> getAllAttachedSkills() throws SQLException;

    public void deleteAttachedSkill(AttachedSkill attachedSkill) throws SQLException;

    public List<AttachedSkill> getAttachedSkillsByPersonage(Personage personage) throws SQLException;

    public AttachedSkill getAttachedSkillByName(String attachedSkillName) throws SQLException;
}
