package DAO;

import entity.RaceHasAttribute;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 6:39 PM
 */
@Repository
public class RaceHasAttributeDAOImpl implements RaceHasAttributeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRaceHasAttribute(RaceHasAttribute raceHasAttribute) {
        sessionFactory.getCurrentSession().save(raceHasAttribute);
    }

    @Override
    public void updateRaceHasAttribute(RaceHasAttribute raceHasAttribute) {
        sessionFactory.getCurrentSession().update(raceHasAttribute);
    }

    @Override
    public RaceHasAttribute getRaceHasAttributeById(int raceHasAttributeId) {
        RaceHasAttribute raceHasAttribute = (RaceHasAttribute) sessionFactory.getCurrentSession().
                load(RaceHasAttribute.class, raceHasAttributeId);
        Hibernate.initialize(raceHasAttribute);
        return raceHasAttribute;
    }

    @Override
    public List<RaceHasAttribute> getAllRaceHasAttributes() {
        return sessionFactory.getCurrentSession().createQuery("from RaceHasAttribute").list();
    }

    @Override
    public void deleteRaceHasAttribute(RaceHasAttribute raceHasAttribute) {
        sessionFactory.getCurrentSession().delete(raceHasAttribute);
    }

    @Override
    public RaceHasAttribute getRaceHasAttributeByAttributeIdAndRaceId(int attributeId, int raceId) {
        Session session = sessionFactory.getCurrentSession();
        List<RaceHasAttribute> raceHasAttributes = session.createSQLQuery(
                "select * from race_has_attribute " +
                        "where attribute_id = :attribute_id and race_id = :race_id")
                .addEntity(RaceHasAttribute.class)
                .setInteger("attribute_id", attributeId)
                .setInteger("race_id", raceId)
                .list();
        return raceHasAttributes.get(0);
    }

    @Override
    public List<RaceHasAttribute> getRaceHasAttributeByRaceId(int raceId) {
        Session session = sessionFactory.getCurrentSession();
        List<RaceHasAttribute> raceHasAttributes = session.createSQLQuery(
                "select * from race_has_attribute where race_id = :id"
        )
                .addEntity(RaceHasAttribute.class)
                .setInteger("id", raceId)
                .list();
        return raceHasAttributes;
    }
}
