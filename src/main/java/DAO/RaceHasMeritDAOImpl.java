package DAO;

import entity.RaceHasMerit;
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
        return (RaceHasMerit) sessionFactory.getCurrentSession().load(RaceHasMerit.class, raceHasMeritId);
    }

    @Override
    public List<RaceHasMerit> getAllRaceHasMerits() {
        return sessionFactory.getCurrentSession().createQuery("from RaceHasMerit").list();
    }

    @Override
    public void deleteRaceHasMerit(RaceHasMerit raceHasMerit) {
        sessionFactory.getCurrentSession().delete(raceHasMerit);
    }
}
