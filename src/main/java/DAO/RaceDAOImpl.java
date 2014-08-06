package DAO;

import entity.Personage;
import entity.Race;
import org.hibernate.Hibernate;
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
 * Time: 3:41 PM
 */
public class RaceDAOImpl implements RaceDAO{
    @Override
    public void addRace(Race race) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(race);
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
    public void updateRace(Race race) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(race);
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
    public Race getRaceById(int raceId) throws SQLException {
        Session session = null;
        Race race = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            race =  (Race) session.load(Race.class, raceId);
            Hibernate.initialize(race);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return race;
    }

    @Override
    public Race getRaceByName(String raceName) throws SQLException {
        Session session = null;
        Race race = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<Race> races = session.createSQLQuery("select * from race where name= :name")
                    .addEntity(Race.class)
                    .setString("name", raceName)
                    .list();
            race = races.get(0);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return race;
    }

    @Override
    public List<Race> getAllRaces() throws SQLException {
        Session session = null;
        List<Race> races = new ArrayList<Race>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            races = session.createCriteria(Race.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return races;
    }

    @Override
    public void deleteRace(Race race) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(race);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
