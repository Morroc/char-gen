package factories;

import DAO.*;

/**
 * User: artemk
 * Date: 8/3/14
 * Time: 3:45 PM
 */
public class Factory {
    private static AttachedSkillDAO attachedSkillDAO = null;
    private static PersonageDAO personageDAO = null;
    private static RaceDAO raceDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public AttachedSkillDAO getAttachedSkillDAO(){
        if (attachedSkillDAO == null){
            attachedSkillDAO = new AttachedSkillDAOImpl();
        }
        return attachedSkillDAO;
    }

    public PersonageDAO getCharacterDAO(){
        if (personageDAO == null){
            personageDAO = new PersonageDAOImpl();
        }
        return personageDAO;
    }

    public RaceDAO getRaceDAO(){
        if (raceDAO == null){
            raceDAO = new RaceDAOImpl();
        }
        return raceDAO;
    }
}
