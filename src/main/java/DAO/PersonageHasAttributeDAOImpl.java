package DAO;

import entity.PersonageHasAttribute;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 3:27 PM
 */
@Repository
public class PersonageHasAttributeDAOImpl implements PersonageHasAttributeDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPersonageHasAttribute(PersonageHasAttribute personageHasAttribute) {
        sessionFactory.getCurrentSession().save(personageHasAttribute);
    }

    @Override
    public void updatePersonageHasAttribute(PersonageHasAttribute personageHasAttribute) {
        sessionFactory.getCurrentSession().update(personageHasAttribute);
    }

    @Override
    public PersonageHasAttribute getPersonageHasAttributeById(int personageHasAttributeId) {
        PersonageHasAttribute personageHasAttribute = (PersonageHasAttribute) sessionFactory.getCurrentSession().
                load(PersonageHasAttribute.class, personageHasAttributeId);
        Hibernate.initialize(personageHasAttribute);
        return personageHasAttribute;
    }

    @Override
    public List<PersonageHasAttribute> getAllPersonageHasAttributes() {
        return sessionFactory.getCurrentSession().createQuery("from PersonageHasAttribute").list();
    }

    @Override
    public void deletePersonageHasAttribute(PersonageHasAttribute personageHasAttribute) {
        sessionFactory.getCurrentSession().delete(personageHasAttribute);
    }

    @Override
    public PersonageHasAttribute getPersonageHasAttributeByAttributeIdAndPersonageId(int attributeId, int personageId) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonageHasAttribute> personageHasAttributes = session.createSQLQuery(
                "select * from personage_has_attribute " +
                        "where attribute_id = :attribute_id and personage_id = :personage_id")
                .addEntity(PersonageHasAttribute.class)
                .setInteger("attribute_id", attributeId)
                .setInteger("personage_id", personageId)
                .list();
        if(!personageHasAttributes.isEmpty()) {
            return personageHasAttributes.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<PersonageHasAttribute> getPersonageHasAttributesByPersonageId(int personageId) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonageHasAttribute> personageHasAttributes = session.createSQLQuery(
                "select * from personage_has_attribute where personage_id = :id"
        )
                .addEntity(PersonageHasAttribute.class)
                .setInteger("id", personageId)
                .list();
        return personageHasAttributes;
    }
}
