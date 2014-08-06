import entity.*;
import entity.Personage;
import factories.Factory;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * User: artemk
 * Date: 8/2/14
 * Time: 12:21 PM
 */
public class CharGenMain {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(CharGenMain.class);


        try {
            AttachedSkill attachedSkill = Factory.getInstance().getAttachedSkillDAO().getAttachedSkillByName("Sword");
            attachedSkill.setName("Axe");
            Factory.getInstance().getAttachedSkillDAO().updateAttachedSkill(attachedSkill);
        } catch (SQLException e) {
            logger.error("SQL exception" + e);
        }

    }
}
