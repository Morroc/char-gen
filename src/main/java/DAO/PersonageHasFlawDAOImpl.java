package DAO;

import entity.PersonageHasFlaw;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 3:41 PM
 */
@Repository
public class PersonageHasFlawDAOImpl implements PersonageHasFlawDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPersonageHasFlaw(PersonageHasFlaw personageHasFlaw) {
        sessionFactory.getCurrentSession().save(personageHasFlaw);
    }

    @Override
    public void updatePersonageHasFlaw(PersonageHasFlaw personageHasFlaw) {
        sessionFactory.getCurrentSession().update(personageHasFlaw);
    }

    @Override
    public PersonageHasFlaw getPersonageHasFlawById(int personageHasFlawId) {
        PersonageHasFlaw personageHasFlaw = (PersonageHasFlaw) sessionFactory.getCurrentSession().
                load(PersonageHasFlaw.class, personageHasFlawId);
        Hibernate.initialize(personageHasFlaw);
        return personageHasFlaw;
    }

    @Override
    public List<PersonageHasFlaw> getAllPersonageHasFlaws() {
        return sessionFactory.getCurrentSession().createQuery("from PersonageHasFlaw").list();
    }

    @Override
    public void deletePersonageHasFlaw(PersonageHasFlaw personageHasFlaw) {
        sessionFactory.getCurrentSession().delete(personageHasFlaw);
    }

    @Override
    public PersonageHasFlaw getPersonageHasFlawByFlawIdAndPersonageId(int flawId, int personageId) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonageHasFlaw> personageHasFlaws = session.createSQLQuery(
                "select * from personage_has_flaw " +
                        "where flaw_id = :flaw_id and personage_id = :personage_id")
                .addEntity(PersonageHasFlaw.class)
                .setInteger("flaw_id", flawId)
                .setInteger("personage_id", personageId)
                .list();
        return personageHasFlaws.get(0);
    }

    @Override
    public List<PersonageHasFlaw> getPersonageHasFlawByPersonageId(int personageId) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonageHasFlaw> personageHasFlaws = session.createSQLQuery(
                "select * from personage_has_flaw where personage_id = :id"
        )
                .addEntity(PersonageHasFlaw.class)
                .setInteger("id", personageId)
                .list();
        return personageHasFlaws;
    }
}
