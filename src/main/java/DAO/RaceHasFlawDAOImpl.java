package DAO;

import entity.RaceHasFlaw;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 7:00 PM
 */
@Repository
public class RaceHasFlawDAOImpl implements RaceHasFlawDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRaceHasFlaw(RaceHasFlaw raceHasFlaw) {
        sessionFactory.getCurrentSession().save(raceHasFlaw);
    }

    @Override
    public void updateRaceHasFlaw(RaceHasFlaw raceHasFlaw) {
        sessionFactory.getCurrentSession().update(raceHasFlaw);
    }

    @Override
    public RaceHasFlaw getRaceHasFlawById(int raceHasFlawId) {
        RaceHasFlaw raceHasFlaw = (RaceHasFlaw) sessionFactory.getCurrentSession().load(RaceHasFlaw.class, raceHasFlawId);
        Hibernate.initialize(raceHasFlaw);
        return raceHasFlaw;
    }

    @Override
    public List<RaceHasFlaw> getAllRaceHasFlaws() {
        return sessionFactory.getCurrentSession().createQuery("from RaceHasFlaw").list();
    }

    @Override
    public void deleteRaceHasFlaw(RaceHasFlaw raceHasFlaw) {
        sessionFactory.getCurrentSession().delete(raceHasFlaw);
    }

    @Override
    public RaceHasFlaw getRaceHasFlawByFlawIdAndRaceId(int flawId, int raceId) {
        Session session = sessionFactory.getCurrentSession();
        List<RaceHasFlaw> raceHasFlaws = session.createSQLQuery(
                "select * from race_has_flaw " +
                        "where flaw_id = :flaw_id and race_id = :race_id")
                .addEntity(RaceHasFlaw.class)
                .setInteger("flaw_id", flawId)
                .setInteger("race_id", raceId)
                .list();
        return raceHasFlaws.get(0);
    }

    @Override
    public List<RaceHasFlaw> getRaceHasFlawByRaceId(int raceId) {
        Session session = sessionFactory.getCurrentSession();
        List<RaceHasFlaw> raceHasFlaws = session.createSQLQuery(
                "select * from race_has_flaw where race_id = :id"
        )
                .addEntity(RaceHasFlaw.class)
                .setInteger("id", raceId)
                .list();
        return raceHasFlaws;
    }
}
