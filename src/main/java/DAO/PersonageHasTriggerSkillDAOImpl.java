package DAO;

import entity.PersonageHasTriggerSkill;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 3:50 PM
 */
@Repository
public class PersonageHasTriggerSkillDAOImpl implements PersonageHasTriggerSkillDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPersonageHasTriggerSkill(PersonageHasTriggerSkill personageHasTriggerSkill) {
        sessionFactory.getCurrentSession().save(personageHasTriggerSkill);
    }

    @Override
    public void updatePersonageHasTriggerSkill(PersonageHasTriggerSkill personageHasTriggerSkill) {
        sessionFactory.getCurrentSession().update(personageHasTriggerSkill);
    }

    @Override
    public PersonageHasTriggerSkill getPersonageHasTriggerSkillById(int personageHasTriggerSkillId) {
        PersonageHasTriggerSkill personageHasTriggerSkill = (PersonageHasTriggerSkill) sessionFactory.getCurrentSession().
                load(PersonageHasTriggerSkill.class, personageHasTriggerSkillId);
        Hibernate.initialize(personageHasTriggerSkill);
        return personageHasTriggerSkill;
    }

    @Override
    public List<PersonageHasTriggerSkill> getAllPersonageHasTriggerSkills() {
        return sessionFactory.getCurrentSession().createQuery("from PersonageHasTriggerSkill").list();
    }

    @Override
    public void deletePersonageHasTriggerSkill(PersonageHasTriggerSkill personageHasTriggerSkill) {
        sessionFactory.getCurrentSession().delete(personageHasTriggerSkill);
    }

    @Override
    public PersonageHasTriggerSkill getPersonageHasTriggerSkillByTriggerSkillIdAndPersonageId(int triggerSkillId, int personageId) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonageHasTriggerSkill> personageHasTriggerSkills = session.createSQLQuery(
                "select * from personage_has_trigger_skill " +
                        "where trigger_skill_id = :trigger_skill_id and personage_id = :personage_id")
                .addEntity(PersonageHasTriggerSkill.class)
                .setInteger("trigger_skill_id", triggerSkillId)
                .setInteger("personage_id", personageId)
                .list();
        return personageHasTriggerSkills.get(0);
    }

    @Override
    public List<PersonageHasTriggerSkill> getPersonageHasTriggerSkillByPersonageId(int personageId) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonageHasTriggerSkill> personageHasTriggerSkills = session.createSQLQuery(
                "select * from personage_has_trigger_skill where personage_id = :id"
        )
                .addEntity(PersonageHasTriggerSkill.class)
                .setInteger("id", personageId)
                .list();
        return personageHasTriggerSkills;
    }
}
