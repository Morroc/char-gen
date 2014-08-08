package DAO;

import entity.Race;

import java.sql.SQLException;
import java.util.List;

/**
 * User: artemk
 * Date: 8/3/14
 * Time: 3:36 PM
 */
public interface RaceDAO {
    public void addRace(Race race);

    public void updateRace(Race race);

    public Race getRaceById(int raceId);

    public Race getRaceByName(String raceName);

    public List<Race> getAllRaces();

    public void deleteRace(Race race);
}
