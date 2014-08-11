package DAO;

import entity.Race;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/3/14
 * Time: 3:41 PM
 */
@Repository
public class RaceDAOImpl implements RaceDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRace(Race race) {
        sessionFactory.getCurrentSession().save(race);
    }

    @Override
    public void updateRace(Race race) {
        sessionFactory.getCurrentSession().update(race);
    }

    @Override
    public Race getRaceById(int raceId) {
        return (Race) sessionFactory.getCurrentSession().load(Race.class, raceId);
    }

    @Override
    public Race getRaceByName(String raceName) {
        Session session = sessionFactory.getCurrentSession();
        List<Race> races = session.createSQLQuery("select * from race where name= :name")
                .addEntity(Race.class)
                .setString("name", raceName)
                .list();
        return races.get(0);
    }

    @Override
    public List<Race> getAllRaces() {
        return sessionFactory.getCurrentSession().createQuery("from Race").list();
    }

    @Override
    public void deleteRace(Race race) {
        sessionFactory.getCurrentSession().delete(race);
    }
}
