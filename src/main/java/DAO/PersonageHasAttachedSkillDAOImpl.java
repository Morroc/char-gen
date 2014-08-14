package DAO;

import entity.PersonageHasAttachedSkill;
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
public class PersonageHasAttachedSkillDAOImpl implements PersonageHasAttachedSkillDAO{
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
        return (PersonageHasAttachedSkill) sessionFactory.getCurrentSession().load(PersonageHasAttachedSkill.class, personageHasAttachedSkillId);
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
    public PersonageHasAttachedSkill getPersonageHasAttachedSkillByAttachedSkillId(int attachedSkillId) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonageHasAttachedSkill> personageHasAttachedSkills = session.createSQLQuery("select * from personage_has_attached_skill where attached_skill_id = :id")
                .addEntity(PersonageHasAttachedSkill.class)
                .setInteger("id", attachedSkillId)
                .list();
        return personageHasAttachedSkills.get(0);
    }

}
