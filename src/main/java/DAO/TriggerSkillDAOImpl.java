package DAO;

import entity.Personage;
import entity.TriggerSkill;
import org.hibernate.Hibernate;
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
        TriggerSkill triggerSkill = (TriggerSkill) sessionFactory.getCurrentSession().load(TriggerSkill.class, triggerSkillId);
        Hibernate.initialize(triggerSkill);
        return triggerSkill;
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
                "select trsk.* \n" +
                        "from trigger_skill trsk \n" +
                        "\tinner join personage_has_trigger_skill phtr \n" +
                        "\t  on phtr.trigger_skill_id = trsk.id \n" +
                        "\tinner join personage p\n" +
                        "\t  on p.id = phtr.personage_id\n" +
                        "where p.id = :id"
        )
                .addEntity(TriggerSkill.class)
                .setInteger("id", personage.getId());

        return (List<TriggerSkill>) query.list();
    }
}
