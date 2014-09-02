package DAO;

import entity.PersonageHasBirthMerit;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 3:33 PM
 */
@Repository
public class PersonageHasBirthMeritDAOImpl implements PersonageHasBirthMeritDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPersonageHasBirthMerit(PersonageHasBirthMerit personageHasBirthMerit) {
        sessionFactory.getCurrentSession().save(personageHasBirthMerit);
    }

    @Override
    public void updatePersonageHasBirthMerit(PersonageHasBirthMerit personageHasBirthMerit) {
        sessionFactory.getCurrentSession().update(personageHasBirthMerit);
    }

    @Override
    public PersonageHasBirthMerit getPersonageHasBirthMeritById(int personageHasBirthMeritId) {
        PersonageHasBirthMerit personageHasBirthMerit = (PersonageHasBirthMerit) sessionFactory.getCurrentSession().
                load(PersonageHasBirthMerit.class, personageHasBirthMeritId);
        Hibernate.initialize(personageHasBirthMerit);
        return personageHasBirthMerit;
    }

    @Override
    public List<PersonageHasBirthMerit> getAllPersonageHasBirthMerits() {
        return sessionFactory.getCurrentSession().createQuery("from PersonageHasBirthMerit").list();
    }

    @Override
    public void deletePersonageHasBirthMerit(PersonageHasBirthMerit personageHasBirthMerit) {
        sessionFactory.getCurrentSession().delete(personageHasBirthMerit);
    }

    @Override
    public PersonageHasBirthMerit getPersonageHasBirthMeritByBirthMeritIdAndPersonageId(int birthMeritId, int personageId) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonageHasBirthMerit> personageHasBirthMerits = session.createSQLQuery(
                "select * from personage_has_birth_merit " +
                        "where birth_merit_id = :birth_merit_id and personage_id = :personage_id")
                .addEntity(PersonageHasBirthMerit.class)
                .setInteger("birth_merit_id", birthMeritId)
                .setInteger("personage_id", personageId)
                .list();
        return personageHasBirthMerits.get(0);
    }

    @Override
    public List<PersonageHasBirthMerit> getPersonageHasBirthMeritsByPersonageId(int personageId) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonageHasBirthMerit> personageHasBirthMerits = session.createSQLQuery(
                "select * from personage_has_birth_merit where personage_id = :id"
        )
                .addEntity(PersonageHasBirthMerit.class)
                .setInteger("id", personageId)
                .list();
        return personageHasBirthMerits;
    }
}
