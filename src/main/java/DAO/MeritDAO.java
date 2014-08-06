package DAO;

import entity.Merit;
import entity.Personage;
import entity.Race;

import java.sql.SQLException;
import java.util.List;

/**
 * User: artemk
 * Date: 8/6/14
 * Time: 4:01 PM
 */
public interface MeritDAO {
    public void addMerit(Merit merit) throws SQLException;

    public void updateMerit(Merit merit) throws SQLException;

    public Merit getMeritById(int meritId) throws SQLException;

    public Merit getMeritByName(String meritName) throws SQLException;

    public List<Merit> getAllMerits() throws SQLException;

    public void deleteMerit(Merit merit) throws SQLException;

    public List<Merit> getMeritsByPersonage(Personage personage) throws SQLException;

    public List<Merit> getMeritsByRace(Race race) throws SQLException;
}
