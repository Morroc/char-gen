package DAO;

import entity.Merit;
import entity.Personage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/6/14
 * Time: 4:05 PM
 */
@Repository
public class MeritDAOImpl implements MeritDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addMerit(Merit merit) {
        sessionFactory.getCurrentSession().save(merit);
    }

    @Override
    public void updateMerit(Merit merit) {
        sessionFactory.getCurrentSession().update(merit);
    }

    @Override
    public Merit getMeritById(int meritId) {
        return (Merit) sessionFactory.getCurrentSession().load(Merit.class, meritId);
    }

    @Override
    public Merit getMeritByName(String meritName) {
        Session session = sessionFactory.getCurrentSession();
        List<Merit> merits = session.createSQLQuery("select * from merit where name= :name")
                .addEntity(Personage.class)
                .setString("name", meritName)
                .list();
        return merits.get(0);
    }

    @Override
    public List<Merit> getAllMerits() {
        return sessionFactory.getCurrentSession().createQuery("from Merit").list();
    }

    @Override
    public void deleteMerit(Merit merit) {
        sessionFactory.getCurrentSession().delete(merit);
    }
}
