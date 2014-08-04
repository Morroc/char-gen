import entity.*;
import entity.Character;
import factories.Factory;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

/**
 * User: artemk
 * Date: 8/2/14
 * Time: 12:21 PM
 */
public class CharGenMain {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(CharGenMain.class);

//        Race elf = new Race();
//        elf.setName("Dwarf");
//        elf.setMaxAge(300);
//        try {
//            Factory.getInstance().getRaceDAO().addRace(elf);
//        } catch (SQLException e) {
//            logger.error("SQL exception" + e);
//        }

//        Collection races = null;
//        try {
//            races = Factory.getInstance().getRaceDAO().getAllRaces();
//        } catch (SQLException e) {
//            logger.error("SQL exception" + e);
//        }
//        Iterator iterator = races.iterator();
//        logger.info("========Все расы=========");
//        while (iterator.hasNext()) {
//            Race race = (Race) iterator.next();
//            logger.info("Раса : " + race.getName() + "  Максимальный возраст : " + race.getMaxAge());
//        }

        Character shmublon = new Character();
        shmublon.setName("Shmublon");
        shmublon.setAge("500");
        try {
            Race elf = Factory.getInstance().getRaceDAO().getRaceByName("Elf");
            shmublon.setRace(elf);
        } catch (SQLException e) {
            logger.error("SQL exception" + e);
        }

        try {
            Factory.getInstance().getCharacterDAO().addCharacter(shmublon);
        } catch (SQLException e) {
            logger.error("SQL exception" + e);
        }

        Collection characters = null;
        try {
            characters = Factory.getInstance().getCharacterDAO().getAllCharacters();
        } catch (SQLException e) {
            logger.error("SQL exception" + e);
        }
        Iterator iterator = characters.iterator();
        logger.info("========Все персонажи=========");
        while (iterator.hasNext()) {
            Character character = (Character) iterator.next();
            logger.info("Имя : " + character.getName() + " Возраст : " + character.getAge() + " Раса : " + character.getRace().getName());
        }

    }
}
