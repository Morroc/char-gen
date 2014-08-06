package DAO;

import entity.*;
import entity.Personage;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: artemk
 * Date: 8/3/14
 * Time: 3:23 PM
 */
public class PersonageDAOImpl implements PersonageDAO {
    private static final Logger logger = Logger.getLogger(AttachedSkillDAOImpl.class);

    @Override
    public void addPersonage(Personage personage) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(personage);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Ошибка при вставке", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updatePersonage(Personage personage) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(personage);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Ошибка при вставке", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Personage getPersonageById(int personageId) throws SQLException {
        Session session = null;
        Personage personage = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            personage = (Personage) session.load(Personage.class, personageId);
            Hibernate.initialize(personage);
        } catch (Exception e) {
            logger.error("Ошибка 'findById'", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return personage;
    }

    @Override
    public List<Personage> getAllPersonages() throws SQLException {
        Session session = null;
        List<Personage> characters = new ArrayList<Personage>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            characters = session.createCriteria(Personage.class).list();
        } catch (Exception e) {
            logger.error("Ошибка 'getAll'", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return characters;
    }

    @Override
    public void deletePersonage(Personage personage) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(personage);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Ошибка при удалении", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Personage> getPersonagesByRace(Race race) throws SQLException {
        Session session = null;
        List<Personage> personages = new ArrayList<Personage>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int raceId = race.getId();
            Query query = session.createSQLQuery(
                    "select * from personage inner join race on personage.race_id = :id"
            ).addEntity(Personage.class).setInteger("id", raceId);
            personages = (List<Personage>) query.list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return personages;
    }
}
