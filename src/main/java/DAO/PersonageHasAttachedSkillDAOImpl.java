package DAO;

import entity.PersonageHasAttachedSkill;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/14/14
 * Time: 12:43 PM
 */
@Repository
public class PersonageHasAttachedSkillDAOImpl implements PersonageHasAttachedSkillDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPersonageHasAttachedSkill(PersonageHasAttachedSkill personageHasAttachedSkill) {
        sessionFactory.getCurrentSession().save(personageHasAttachedSkill);
    }

    @Override
    public void updatePersonageHasAttachedSkill(PersonageHasAttachedSkill personageHasAttachedSkill) {
        sessionFactory.getCurrentSession().update(personageHasAttachedSkill);
    }

    @Override
    public PersonageHasAttachedSkill getPersonageHasAttachedSkillById(int personageHasAttachedSkillId) {
        PersonageHasAttachedSkill personageHasAttachedSkill = (PersonageHasAttachedSkill) sessionFactory.getCurrentSession().
                load(PersonageHasAttachedSkill.class, personageHasAttachedSkillId);
        Hibernate.initialize(personageHasAttachedSkill);
        return personageHasAttachedSkill;
    }

    @Override
    public List<PersonageHasAttachedSkill> getAllPersonageHasAttachedSkills() {
        return sessionFactory.getCurrentSession().createQuery("from PersonageHasAttachedSkill").list();
    }

    @Override
    public void deletePersonageHasAttachedSkill(PersonageHasAttachedSkill personageHasAttachedSkill) {
        sessionFactory.getCurrentSession().delete(personageHasAttachedSkill);
    }

    @Override
    public PersonageHasAttachedSkill getPersonageHasAttachedSkillByAttachedSkillIdAndPersonageId(int attachedSkillId, int personageId) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonageHasAttachedSkill> personageHasAttachedSkills = session.createSQLQuery(
                "select * from personage_has_attached_skill " +
                        "where attached_skill_id = :attached_skill_id and personage_id = :personage_id")
                .addEntity(PersonageHasAttachedSkill.class)
                .setInteger("attached_skill_id", attachedSkillId)
                .setInteger("personage_id", personageId)
                .list();
        return personageHasAttachedSkills.get(0);
    }

    @Override
    public List<PersonageHasAttachedSkill> getPersonageHasAttachedSkillByPersonageId(int personageId) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonageHasAttachedSkill> personageHasAttachedSkills = session.createSQLQuery(
                "select * from personage_has_attached_skill where personage_id = :id"
        )
                .addEntity(PersonageHasAttachedSkill.class)
                .setInteger("id", personageId)
                .list();
        return personageHasAttachedSkills;
    }
}
