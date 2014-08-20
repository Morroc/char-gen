package DAO;

import entity.PersonageHasMerit;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/7/14
 * Time: 2:29 PM
 */
@Repository
public class PersonageHasMeritDAOImpl implements PersonageHasMeritDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPersonageHasMerit(PersonageHasMerit personageHasMerit) {
        sessionFactory.getCurrentSession().save(personageHasMerit);
    }

    @Override
    public void updatePersonageHasMerit(PersonageHasMerit personageHasMerit) {
        sessionFactory.getCurrentSession().update(personageHasMerit);
    }

    @Override
    public PersonageHasMerit getPersonageHasMeritById(int personageHasMeritId) {
        PersonageHasMerit personageHasMerit = (PersonageHasMerit) sessionFactory.getCurrentSession().
                load(PersonageHasMerit.class, personageHasMeritId);
        Hibernate.initialize(personageHasMerit);
        return personageHasMerit;
    }

    @Override
    public List<PersonageHasMerit> getAllPersonageHasMerits() {
        return sessionFactory.getCurrentSession().createQuery("from PersonageHasMerit").list();
    }

    @Override
    public void deletePersonageHasMerit(PersonageHasMerit personageHasMerit) {
        sessionFactory.getCurrentSession().delete(personageHasMerit);
    }

    @Override
    public PersonageHasMerit getPersonageHasMeritByMeritIdAndPersonageId(int meritId, int personageId) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonageHasMerit> personageHasMerits = session.createSQLQuery(
                "select * from personage_has_merit " +
                        "where merit_id = :merit_id and personage_id = :personage_id")
                .addEntity(PersonageHasMerit.class)
                .setInteger("merit_id", meritId)
                .setInteger("personage_id", personageId)
                .list();
        return personageHasMerits.get(0);
    }

    @Override
    public List<PersonageHasMerit> getPersonageHasMeritsByPersonageId(int personageId) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonageHasMerit> personageHasMerits = session.createSQLQuery(
                "select * from personage_has_merit where personage_id = :id"
        )
                .addEntity(PersonageHasMerit.class)
                .setInteger("id", personageId)
                .list();
        return personageHasMerits;
    }
}
