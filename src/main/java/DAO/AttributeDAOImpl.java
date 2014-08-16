package DAO;

import entity.Attribute;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 2:31 PM
 */
@Repository
public class AttributeDAOImpl implements AttributeDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addAttribute(Attribute attribute) {
        sessionFactory.getCurrentSession().save(attribute);
    }

    @Override
    public void updateAttribute(Attribute attribute) {
        sessionFactory.getCurrentSession().update(attribute);
    }

    @Override
    public Attribute getAttributeById(int attributeId) {
        Attribute attribute = (Attribute) sessionFactory.getCurrentSession().load(Attribute.class, attributeId);
        Hibernate.initialize(attribute);
        return attribute;
    }

    @Override
    public Attribute getAttributeByName(String attributeName) {
        Session session = sessionFactory.getCurrentSession();
        List<Attribute> attributes = session.createSQLQuery("select * from attribute where name= :name")
                .addEntity(Attribute.class)
                .setString("name", attributeName)
                .list();
        return attributes.get(0);
    }

    @Override
    public List<Attribute> getAllAttributes() {
        return sessionFactory.getCurrentSession().createQuery("from Attribute").list();
    }

    @Override
    public void deleteAttribute(Attribute attribute) {
        sessionFactory.getCurrentSession().delete(attribute);
    }
}
