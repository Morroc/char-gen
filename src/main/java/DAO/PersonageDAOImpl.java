package DAO;

import entity.*;
import entity.Personage;
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
    @Override
    public void addPersonage(Personage personage) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(personage);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return personage;
    }

    @Override
    public Collection getAllPersonages() throws SQLException {
        Session session = null;
        List characters = new ArrayList<Personage>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            characters = session.createCriteria(Personage.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Collection getPersonagesByRace(Race race) throws SQLException {
        Session session = null;
        List attachedSkills = new ArrayList<AttachedSkill>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            int raceId = race.getId();
            Query query = session.createQuery(
                    "select * from personage inner join race on personage.race_id = race.id;"
            )
                    .setLong("race_id", raceId);
            attachedSkills = (List<AttachedSkill>) query.list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return attachedSkills;
    }
}
