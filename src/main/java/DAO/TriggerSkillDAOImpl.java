package DAO;

import entity.Personage;
import entity.TriggerSkill;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/6/14
 * Time: 3:25 PM
 */
public class TriggerSkillDAOImpl implements TriggerSkillDAO {
    private static final Logger logger = Logger.getLogger(AttachedSkillDAOImpl.class);

    @Override
    public void addTriggerSkill(TriggerSkill triggerSkill) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(triggerSkill);
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
    public void updateTriggerSkill(TriggerSkill triggerSkill) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(triggerSkill);
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
    public TriggerSkill getTriggerSkillById(int triggerSkillId) throws SQLException {
        Session session = null;
        TriggerSkill triggerSkill = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            triggerSkill = (TriggerSkill) session.load(TriggerSkill.class, triggerSkillId);
            Hibernate.initialize(triggerSkill);
        } catch (Exception e) {
            logger.error("Ошибка 'findById'", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return triggerSkill;
    }

    @Override
    public TriggerSkill getTriggerSkillByName(String triggerSkillName) throws SQLException {
        Session session = null;
        TriggerSkill triggerSkill = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<TriggerSkill> attachedSkills = session.createSQLQuery("select * from triggerskill where name= :name")
                    .addEntity(TriggerSkill.class)
                    .setString("name", triggerSkillName)
                    .list();
            triggerSkill = attachedSkills.get(0);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return triggerSkill;
    }

    @Override
    public List<TriggerSkill> getAllTriggerSkills() throws SQLException {
        Session session = null;
        List<TriggerSkill> triggerSkills = new ArrayList<TriggerSkill>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            triggerSkills = session.createCriteria(TriggerSkill.class).list();
        } catch (Exception e) {
            logger.error("Ошибка 'getAll'", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return triggerSkills;
    }

    @Override
    public void deleteTriggerSkill(TriggerSkill triggerSkill) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(triggerSkill);
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
    public List<TriggerSkill> getTriggerSkillsByPersonage(Personage personage) throws SQLException {
        Session session = null;
        List<TriggerSkill> triggerSkills = new ArrayList<TriggerSkill>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int personageId = personage.getId();
            Query query = session.createSQLQuery(
                    "select * from triggerskill inner join personage on triggerskill.personage_id = :id"
            ).addEntity(TriggerSkill.class).setInteger("id", personageId);
            triggerSkills = (List<TriggerSkill>) query.list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return triggerSkills;
    }
}
