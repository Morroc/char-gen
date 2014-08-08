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
    public void addTriggerSkill(TriggerSkill triggerSkill);

    public void updateTriggerSkill(TriggerSkill triggerSkill);

    public TriggerSkill getTriggerSkillById(int triggerSkillId);

    public TriggerSkill getTriggerSkillByName(String triggerSkillName);

    public List<TriggerSkill> getAllTriggerSkills();

    public void deleteTriggerSkill(TriggerSkill triggerSkill);

    public List<TriggerSkill> getTriggerSkillsByPersonage(Personage personage);
}
