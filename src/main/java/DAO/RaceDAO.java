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
    public void addRace(Race race) throws SQLException;

    public void updateRace(Race race) throws SQLException;

    public Race getRaceById(int raceId) throws SQLException;

    public Race getRaceByName(String raceName) throws SQLException;

    public List<Race> getAllRaces() throws SQLException;

    public void deleteRace(Race race) throws SQLException;
}
