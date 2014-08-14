package DAO;

import entity.Personage;
import entity.TriggerSkill;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/6/14
 * Time: 3:25 PM
 */
@Repository
public class TriggerSkillDAOImpl implements TriggerSkillDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTriggerSkill(TriggerSkill triggerSkill) {
        sessionFactory.getCurrentSession().save(triggerSkill);
    }

    @Override
    public void updateTriggerSkill(TriggerSkill triggerSkill) {
        sessionFactory.getCurrentSession().update(triggerSkill);
    }

    @Override
    public TriggerSkill getTriggerSkillById(int triggerSkillId) {
        return (TriggerSkill) sessionFactory.getCurrentSession().load(TriggerSkill.class, triggerSkillId);
    }

    @Override
    public TriggerSkill getTriggerSkillByName(String triggerSkillName) {
        Session session = sessionFactory.getCurrentSession();
        List<TriggerSkill> triggerSkills = session.createSQLQuery("select * from trigger_skill where name= :name")
                .addEntity(TriggerSkill.class)
                .setString("name", triggerSkillName)
                .list();
        return triggerSkills.get(0);
    }

    @Override
    public List<TriggerSkill> getAllTriggerSkills() {
        return sessionFactory.getCurrentSession().createQuery("from TriggerSkill").list();
    }

    @Override
    public void deleteTriggerSkill(TriggerSkill triggerSkill) {
        sessionFactory.getCurrentSession().delete(triggerSkill);
    }

    @Override
    public List<TriggerSkill> getTriggerSkillsByPersonage(Personage personage) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
                "select * from trigger_skill inner join personage on trigger_skill.personage_id = :id"
        )
                .addEntity(TriggerSkill.class)
                .setInteger("id", personage.getId());

        return (List<TriggerSkill>) query.list();
    }
}
