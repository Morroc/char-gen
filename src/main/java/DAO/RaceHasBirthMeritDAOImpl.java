package DAO;

import entity.RaceHasBirthMerit;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 6:50 PM
 */
@Repository
public class RaceHasBirthMeritDAOImpl implements RaceHasBirthMeritDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRaceHasBirthMerit(RaceHasBirthMerit raceHasBirthMerit) {
        sessionFactory.getCurrentSession().save(raceHasBirthMerit);
    }

    @Override
    public void updateRaceHasBirthMerit(RaceHasBirthMerit raceHasBirthMerit) {
        sessionFactory.getCurrentSession().update(raceHasBirthMerit);
    }

    @Override
    public RaceHasBirthMerit getRaceHasBirthMeritById(int raceHasBirthMeritId) {
        RaceHasBirthMerit raceHasBirthMerit = (RaceHasBirthMerit) sessionFactory.getCurrentSession().
                load(RaceHasBirthMerit.class, raceHasBirthMeritId);
        Hibernate.initialize(raceHasBirthMerit);
        return raceHasBirthMerit;
    }

    @Override
    public List<RaceHasBirthMerit> getAllRaceHasBirthMerits() {
        return sessionFactory.getCurrentSession().createQuery("from RaceHasBirthMerit").list();
    }

    @Override
    public void deleteRaceHasBirthMerit(RaceHasBirthMerit raceHasBirthMerit) {
        sessionFactory.getCurrentSession().delete(raceHasBirthMerit);
    }

    @Override
    public RaceHasBirthMerit getRaceHasBirthMeritByBirthMeritIdAndRaceId(int birthMeritId, int raceId) {
        Session session = sessionFactory.getCurrentSession();
        List<RaceHasBirthMerit> raceHasBirthMerits = session.createSQLQuery(
                "select * from race_has_birth_merit " +
                        "where birth_merit_id = :birth_merit_id and race_id = :race_id")
                .addEntity(RaceHasBirthMerit.class)
                .setInteger("birth_merit_id", birthMeritId)
                .setInteger("race_id", raceId)
                .list();
        return raceHasBirthMerits.get(0);
    }

    @Override
    public List<RaceHasBirthMerit> getRaceHasBirthMeritsByRaceId(int raceId) {
        Session session = sessionFactory.getCurrentSession();
        List<RaceHasBirthMerit> raceHasBirthMerits = session.createSQLQuery(
                "select * from race_has_birth_merit where race_id = :id"
        )
                .addEntity(RaceHasBirthMerit.class)
                .setInteger("id", raceId)
                .list();
        return raceHasBirthMerits;
    }
}
