package DAO;

import entity.Attribute;
import entity.Personage;
import org.hibernate.Hibernate;
import org.hibernate.Query;
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

    @Override
    public List<Attribute> getAttributesByPersonage(Personage personage) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
                "select attrs.* \n" +
                        "from attribute attrs \n" +
                        "\tinner join personage_has_attribute phatttr \n" +
                        "\t  on phatttr.attribute_id = attrs.id \n" +
                        "\tinner join personage p\n" +
                        "\t  on p.id = phatttr.personage_id\n" +
                        "where p.id = :id"
        )
                .addEntity(Attribute.class)
                .setInteger("id", personage.getId());

        return (List<Attribute>) query.list();
    }
}
