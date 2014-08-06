package DAO;

import entity.Merit;
import entity.Personage;
import entity.Race;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/6/14
 * Time: 4:05 PM
 */
public class MeritDAOImpl implements MeritDAO{
    private static final Logger logger = Logger.getLogger(MeritDAOImpl.class);

    @Override
    public void addMerit(Merit merit) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(merit);
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
    public void updateMerit(Merit merit) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(merit);
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
    public Merit getMeritById(int triggerSkillId) throws SQLException {
        Session session = null;
        Merit merit = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            merit = (Merit) session.load(Merit.class, triggerSkillId);
            Hibernate.initialize(merit);
        } catch (Exception e) {
            logger.error("Ошибка 'findById'", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return merit;
    }

    @Override
    public Merit getMeritByName(String meritName) throws SQLException {
        Session session = null;
        Merit merit = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<Merit> merits = session.createSQLQuery("select * from merit where name= :name")
                    .addEntity(Merit.class)
                    .setString("name", meritName)
                    .list();
            merit = merits.get(0);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return merit;
    }

    @Override
    public List<Merit> getAllMerits() throws SQLException {
        Session session = null;
        List<Merit> merits = new ArrayList<Merit>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            merits = session.createCriteria(Merit.class).list();
        } catch (Exception e) {
            logger.error("Ошибка 'getAll'", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return merits;
    }

    @Override
    public void deleteMerit(Merit merit) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(merit);
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
    public List<Merit> getMeritsByPersonage(Personage personage) throws SQLException {
        Session session = null;
        List<Merit> merits = new ArrayList<Merit>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int personageId = personage.getId();
            Query query = session.createSQLQuery(
                    "select * from merit inner join personage on merit.personage_id = :id"
            ).addEntity(Merit.class).setInteger("id", personageId);
            merits = (List<Merit>) query.list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return merits;
    }

    @Override
    public List<Merit> getMeritsByRace(Race race) throws SQLException {
        Session session = null;
        List<Merit> merits = new ArrayList<Merit>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int raceId = race.getId();
            Query query = session.createSQLQuery(
                    "select * from merit inner join race on merit.race_id = :id"
            ).addEntity(Merit.class).setInteger("id", raceId);
            merits = (List<Merit>) query.list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return merits;
    }
}
