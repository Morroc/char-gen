package DAO;

import entity.Flaw;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 3:19 PM
 */
@Repository
public class FlawDAOImpl implements FlawDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addFlaw(Flaw flaw) {
        sessionFactory.getCurrentSession().save(flaw);
    }

    @Override
    public void updateFlaw(Flaw flaw) {
        sessionFactory.getCurrentSession().update(flaw);
    }

    @Override
    public Flaw getFlawById(int flawId) {
        Flaw flaw = (Flaw) sessionFactory.getCurrentSession().load(Flaw.class, flawId);
        Hibernate.initialize(flaw);
        return flaw;
    }

    @Override
    public Flaw getFlawByName(String flawName) {
        Session session = sessionFactory.getCurrentSession();
        List<Flaw> flaws = session.createSQLQuery("select * from flaw where name= :name")
                .addEntity(Flaw.class)
                .setString("name", flawName)
                .list();
        return flaws.get(0);
    }

    @Override
    public List<Flaw> getAllFlaws() {
        return sessionFactory.getCurrentSession().createQuery("from Flaw").list();
    }

    @Override
    public void deleteFlaw(Flaw flaw) {
        sessionFactory.getCurrentSession().delete(flaw);
    }
}
