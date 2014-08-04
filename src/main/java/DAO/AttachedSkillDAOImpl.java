package DAO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import entity.AttachedSkill;
import org.hibernate.Session;
import org.hibernate.Query;
import utils.HibernateUtil;
import entity.Character;

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
    public Collection getAllAttachedSkills() throws SQLException {
        Session session = null;
        List attachedSkills = new ArrayList<AttachedSkill>();
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
    public Collection getAttachedSkillsByCharacter(Character character) throws SQLException {
        Session session = null;
        List attachedSkills = new ArrayList<AttachedSkill>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            int character_id = character.getId();
            Query query = session.createQuery(
                    "select * from attachedskill inner join generatordb.character on attachedskill.character_id = generatordb.character.id;"
            )
                    .setLong("character_id", character_id);
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
