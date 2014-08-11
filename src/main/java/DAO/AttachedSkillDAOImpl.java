package DAO;

import entity.AttachedSkill;
import entity.Personage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: artemk
 * Date: 8/3/14
 * Time: 1:55 PM
 */
@Repository
public class AttachedSkillDAOImpl implements AttachedSkillDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addAttachedSkill(AttachedSkill attachedSkill) {
        sessionFactory.getCurrentSession().save(attachedSkill);
    }

    @Override
    public void updateAttachedSkill(AttachedSkill attachedSkill) {
        sessionFactory.getCurrentSession().update(attachedSkill);
    }

    @Override
    public AttachedSkill getAttachedSkillById(int attachedSkillId) {
        return (AttachedSkill) sessionFactory.getCurrentSession().load(AttachedSkill.class, attachedSkillId);
    }

    @Override
    public AttachedSkill getAttachedSkillByName(String attachedSkillName) {
        Session session = sessionFactory.getCurrentSession();
        List<AttachedSkill> attachedSkills = session.createSQLQuery("select * from attachedskill where name= :name")
                .addEntity(Personage.class)
                .setString("name", attachedSkillName)
                .list();
        return attachedSkills.get(0);
    }

    @Override
    public List<AttachedSkill> getAllAttachedSkills() {
        return sessionFactory.getCurrentSession().createQuery("from AttachedSkill").list();
    }

    @Override
    public void deleteAttachedSkill(AttachedSkill attachedSkill) {
        sessionFactory.getCurrentSession().delete(attachedSkill);
    }

    @Override
    public List<AttachedSkill> getAttachedSkillsByPersonage(Personage personage) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
                "select * from attachedskill inner join personage on attachedskill.personage_id = :id"
        )
                .addEntity(AttachedSkill.class)
                .setInteger("id", personage.getId());

        return (List<AttachedSkill>) query.list();
    }
}
