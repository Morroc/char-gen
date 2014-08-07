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
            Race race = new Race();
            race.setName("Dwarf");
            race.setMaxAge(300);
            Factory.getInstance().getRaceDAO().addRace(race);

            Personage personage = new Personage();
            personage.setName("Wulfer");
            personage.setAge(100);
            personage.setRace(race);
            Factory.getInstance().getPersonageDAO().addPersonage(personage);
        } catch (SQLException e) {
            logger.error("SQL exception" + e);
        }

    }
}
