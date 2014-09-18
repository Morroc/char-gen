package DAO;

import entity.MeritHasAttributePrecondition;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by artemk on 9/17/14.
 */
@Repository
public class MeritHasAttributePreconditionDAOImpl implements MeritHasAttributePreconditionDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addMeritHasAttributePrecondition(MeritHasAttributePrecondition meritHasAttributePrecondition) {
        sessionFactory.getCurrentSession().save(meritHasAttributePrecondition);
    }

    @Override
    public void updateMeritHasAttributePrecondition(MeritHasAttributePrecondition meritHasAttributePrecondition) {
        sessionFactory.getCurrentSession().update(meritHasAttributePrecondition);
    }

    @Override
    public MeritHasAttributePrecondition getMeritHasAttributePreconditionById(int meritHasAttributePreconditionId) {
        MeritHasAttributePrecondition meritHasAttributePrecondition = (MeritHasAttributePrecondition) sessionFactory.getCurrentSession().
                load(MeritHasAttributePrecondition.class, meritHasAttributePreconditionId);
        Hibernate.initialize(meritHasAttributePrecondition);
        return meritHasAttributePrecondition;
    }

    @Override
    public List<MeritHasAttributePrecondition> getAllMeritHasAttributePreconditions() {
        return sessionFactory.getCurrentSession().createQuery("from MeritHasAttributePrecondition").list();
    }

    @Override
    public void deleteMeritHasAttributePrecondition(MeritHasAttributePrecondition meritHasAttributePrecondition) {
        sessionFactory.getCurrentSession().delete(meritHasAttributePrecondition);
    }

    @Override
    public MeritHasAttributePrecondition getMeritHasAttributePreconditionByMeritIdAndAttributeId(int meritId, int attributeId) {
        Session session = sessionFactory.getCurrentSession();
        List<MeritHasAttributePrecondition> meritHasAttributePreconditions = session.createSQLQuery(
                "select * from merit_has_attribute_preconditions " +
                        "where merit_id = :merit_id and attribute_id = :attribute_id")
                .addEntity(MeritHasAttributePrecondition.class)
                .setInteger("merit_id", meritId)
                .setInteger("attribute_id", attributeId)
                .list();
        return meritHasAttributePreconditions.get(0);
    }

    @Override
    public List<MeritHasAttributePrecondition> getMeritHasAttributePreconditionsByMeritId(int meritId) {
        Session session = sessionFactory.getCurrentSession();
        List<MeritHasAttributePrecondition> meritHasAttributePreconditions = session.createSQLQuery(
                "select * from merit_has_attribute_preconditions where merit_id = :id"
        )
                .addEntity(MeritHasAttributePrecondition.class)
                .setInteger("id", meritId)
                .list();
        return meritHasAttributePreconditions;
    }
}
