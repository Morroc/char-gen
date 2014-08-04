package DAO;

import entity.*;
import entity.Character;
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
public class CharacterDAOImpl implements CharacterDAO {
    @Override
    public void addCharacter(Character character) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(character);
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
    public void updateCharacter(Character character) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(character);
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
    public Character getCharacterById(int characterId) throws SQLException {
        Session session = null;
        Character character = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            character = (Character) session.load(Character.class, characterId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return character;
    }

    @Override
    public Collection getAllCharacters() throws SQLException {
        Session session = null;
        List characters = new ArrayList<Character>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            characters = session.createCriteria(Character.class).list();
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
    public void deleteCharacter(Character character) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(character);
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
    public Collection getCharactersByRace(Race race) throws SQLException {
        Session session = null;
        List attachedSkills = new ArrayList<AttachedSkill>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            int race_id = race.getId();
            Query query = session.createQuery(
                    "select * from generatordb.character inner join race on generatordb.character.race_id = race.id;"
            )
                    .setLong("race_id", race_id);
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
