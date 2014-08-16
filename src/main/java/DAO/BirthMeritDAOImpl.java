package DAO;

import entity.BirthMerit;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 3:06 PM
 */
@Repository
public class BirthMeritDAOImpl implements BirthMeritDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addBirthMerit(BirthMerit birthMerit) {
        sessionFactory.getCurrentSession().save(birthMerit);
    }

    @Override
    public void updateBirthMerit(BirthMerit birthMerit) {
        sessionFactory.getCurrentSession().update(birthMerit);
    }

    @Override
    public BirthMerit getBirthMeritById(int birthMeritId) {
        BirthMerit birthMerit = (BirthMerit) sessionFactory.getCurrentSession().load(BirthMerit.class, birthMeritId);
        Hibernate.initialize(birthMerit);
        return birthMerit;
    }

    @Override
    public BirthMerit getBirthMeritByName(String birthMeritName) {
        Session session = sessionFactory.getCurrentSession();
        List<BirthMerit> birthMerits = session.createSQLQuery("select * from birth_merit where name= :name")
                .addEntity(BirthMerit.class)
                .setString("name", birthMeritName)
                .list();
        return birthMerits.get(0);
    }

    @Override
    public List<BirthMerit> getAllBirthMerits() {
        return sessionFactory.getCurrentSession().createQuery("from BirthMerit").list();
    }

    @Override
    public void deleteBirthMerit(BirthMerit birthMerit) {
        sessionFactory.getCurrentSession().delete(birthMerit);
    }
}
