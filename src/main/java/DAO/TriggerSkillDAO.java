package DAO;

import entity.AttachedSkill;
import entity.Personage;
import entity.TriggerSkill;

import java.sql.SQLException;
import java.util.List;

/**
 * User: artemk
 * Date: 8/6/14
 * Time: 3:22 PM
 */
public interface TriggerSkillDAO {
    public void addTriggerSkill(TriggerSkill triggerSkill) throws SQLException;

    public void updateTriggerSkill(TriggerSkill triggerSkill) throws SQLException;

    public TriggerSkill getTriggerSkillById(int triggerSkillId) throws SQLException;

    public TriggerSkill getTriggerSkillByName(String triggerSkillName) throws SQLException;

    public List<TriggerSkill> getAllTriggerSkills() throws SQLException;

    public void deleteTriggerSkill(TriggerSkill triggerSkill) throws SQLException;

    public List<TriggerSkill> getTriggerSkillsByPersonage(Personage personage) throws SQLException;
}
