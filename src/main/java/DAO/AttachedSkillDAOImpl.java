package DAO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import entity.AttachedSkill;
import entity.Personage;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Query;
import utils.HibernateUtil;

/**
 * User: artemk
 * Date: 8/3/14
 * Time: 1:55 PM
 */
public class AttachedSkillDAOImpl implements AttachedSkillDAO {
    @Override
    public void addAttachedSkill(AttachedSkill attachedSkill) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(attachedSkill);
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
    public void updateAttachedSkill(AttachedSkill attachedSkill) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(attachedSkill);
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
    public AttachedSkill getAttachedSkillById(int attachedSkillId) throws SQLException {
        Session session = null;
        AttachedSkill attachedSkill = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            attachedSkill = (AttachedSkill) session.load(AttachedSkill.class, attachedSkillId);
            Hibernate.initialize(attachedSkill);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return attachedSkill;
    }

    @Override
    public AttachedSkill getAttachedSkillByName(String attachedSkillName) throws SQLException {
        Session session = null;
        AttachedSkill attachedSkill = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<AttachedSkill> attachedSkills = session.createSQLQuery("select * from attachedskill where name= :name")
                    .addEntity(AttachedSkill.class)
                    .setString("name", attachedSkillName)
                    .list();
            attachedSkill = attachedSkills.get(0);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return attachedSkill;
    }

    @Override
    public List<AttachedSkill> getAllAttachedSkills() throws SQLException {
        Session session = null;
        List<AttachedSkill> attachedSkills = new ArrayList<AttachedSkill>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            attachedSkills = session.createCriteria(AttachedSkill.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return attachedSkills;
    }

    @Override
    public void deleteAttachedSkill(AttachedSkill attachedSkill) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(attachedSkill);
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
    public List<AttachedSkill> getAttachedSkillsByPersonage(Personage personage) throws SQLException {
        Session session = null;
        List<AttachedSkill> attachedSkills = new ArrayList<AttachedSkill>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int personageId = personage.getId();
            Query query = session.createSQLQuery(
                    "select * from attachedskill inner join personage on attachedskill.personage_id = :id"
            ).addEntity(AttachedSkill.class).setInteger("id", personageId);
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
