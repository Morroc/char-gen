import entity.*;
import entity.Personage;
import factories.Factory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

/**
 * User: artemk
 * Date: 8/2/14
 * Time: 12:21 PM
 */
public class CharGenMain {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(CharGenMain.class);

        Race elf = new Race();
        elf.setName("Dwarf");
        elf.setMaxAge(300);
        try {
            Factory.getInstance().getRaceDAO().addRace(elf);
        } catch (SQLException e) {
            logger.error("SQL exception" + e);
        }

        Collection races = null;
        try {
            races = Factory.getInstance().getRaceDAO().getAllRaces();
        } catch (SQLException e) {
            logger.error("SQL exception" + e);
        }
        Iterator iterator = races.iterator();
        logger.info("========Все расы=========");
        while (iterator.hasNext()) {
            Race race = (Race) iterator.next();
            logger.info("Раса : " + race.getName() + "  Максимальный возраст : " + race.getMaxAge());
        }

//        Personage shmublon = new Personage();
//        shmublon.setName("Shmublon");
//        shmublon.setAge(500);
//        try {
//            Race elf = Factory.getInstance().getRaceDAO().getRaceByName("Elf");
//            shmublon.setRace(elf);
//        } catch (SQLException e) {
//            logger.error("SQL exception" + e);
//        }
//
//        try {
//            Factory.getInstance().getCharacterDAO().addPersonage(shmublon);
//        } catch (SQLException e) {
//            logger.error("SQL exception" + e);
//        }
//
//        Collection characters = null;
//        try {
//            characters = Factory.getInstance().getCharacterDAO().getAllCharacters();
//        } catch (SQLException e) {
//            logger.error("SQL exception" + e);
//        }
//        Iterator iterator = characters.iterator();
//        logger.info("========Все персонажи=========");
//        while (iterator.hasNext()) {
//            Personage character = (Personage) iterator.next();
//            logger.info("Имя : " + character.getName() + " Возраст : " + character.getAge() + " Раса : " + character.getRace().getName());
//        }

//        SessionFactory sf = HibernateUtil.getSessionFactory();
//        Session session = sf.openSession();
//
//        session.beginTransaction();
//
//        Race race = new Race();
//        race.setName("Human");
//        race.setMaxAge(150);
//        session.save(race);
//
//        Personage shmublon = new Personage();
//        shmublon.setName("Shmublon");
//        shmublon.setAge(30);
//        shmublon.setRace(race);
//
//        session.save(shmublon);
//
//        session.getTransaction().commit();
//        session.close();

    }
}
