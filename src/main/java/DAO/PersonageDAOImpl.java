package DAO;

import entity.Personage;
import entity.Race;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/3/14
 * Time: 3:23 PM
 */
@Repository
public class PersonageDAOImpl implements PersonageDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPersonage(Personage personage) {
        sessionFactory.getCurrentSession().save(personage);
    }

    @Override
    public void updatePersonage(Personage personage) {
        sessionFactory.getCurrentSession().update(personage);
    }

    @Override
    public Personage getPersonageById(int personageId) {
        return (Personage) sessionFactory.getCurrentSession().load(Personage.class, personageId);
    }

    @Override
    public Personage getPersonageByName(String personageName) {
        Session session = sessionFactory.getCurrentSession();
        List<Personage> personages = session.createSQLQuery("select * from personage where name= :name")
                .addEntity(Personage.class)
                .setString("name", personageName)
                .list();
        return personages.get(0);
    }

    @Override
    public List<Personage> getAllPersonages() {
        return sessionFactory.getCurrentSession().createQuery("from personage").list();
    }

    @Override
    public void deletePersonage(Personage personage) {
        sessionFactory.getCurrentSession().delete(personage);
    }

    @Override
    public List<Personage> getPersonagesByRace(Race race) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
                "select * from personage inner join race on personage.race_id = :id"
        )
                .addEntity(Personage.class)
                .setInteger("id", race.getId());

        return (List<Personage>) query.list();
    }
}
