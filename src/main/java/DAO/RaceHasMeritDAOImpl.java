package DAO;

import entity.RaceHasMerit;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/7/14
 * Time: 2:05 PM
 */
@Repository
public class RaceHasMeritDAOImpl implements RaceHasMeritDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRaceHasMerit(RaceHasMerit raceHasMerit) {
        sessionFactory.getCurrentSession().save(raceHasMerit);
    }

    @Override
    public void updateRaceHasMerit(RaceHasMerit raceHasMerit) {
        sessionFactory.getCurrentSession().update(raceHasMerit);
    }

    @Override
    public RaceHasMerit getRaceHasMeritById(int raceHasMeritId) {
        RaceHasMerit raceHasMerit = (RaceHasMerit) sessionFactory.getCurrentSession().load(RaceHasMerit.class, raceHasMeritId);
        Hibernate.initialize(raceHasMerit);
        return raceHasMerit;
    }

    @Override
    public List<RaceHasMerit> getAllRaceHasMerits() {
        return sessionFactory.getCurrentSession().createQuery("from RaceHasMerit").list();
    }

    @Override
    public void deleteRaceHasMerit(RaceHasMerit raceHasMerit) {
        sessionFactory.getCurrentSession().delete(raceHasMerit);
    }

    @Override
    public RaceHasMerit getRaceHasMeritByMeritIdAndRaceId(int meritId, int raceId) {
        Session session = sessionFactory.getCurrentSession();
        List<RaceHasMerit> raceHasMerits = session.createSQLQuery(
                "select * from race_has_merit " +
                        "where merit_id = :merit_id and race_id = :race_id")
                .addEntity(RaceHasMerit.class)
                .setInteger("merit_id", meritId)
                .setInteger("race_id", raceId)
                .list();
        return raceHasMerits.get(0);
    }

    @Override
    public List<RaceHasMerit> getRaceHasMeritByRaceId(int raceId) {
        Session session = sessionFactory.getCurrentSession();
        List<RaceHasMerit> raceHasMerits = session.createSQLQuery(
                "select * from race_has_merit where race_id = :id"
        )
                .addEntity(RaceHasMerit.class)
                .setInteger("id", raceId)
                .list();
        return raceHasMerits;
    }
}
