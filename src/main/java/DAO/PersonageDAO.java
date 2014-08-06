package DAO;

import entity.*;
import entity.Personage;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * User: artemk
 * Date: 8/3/14
 * Time: 2:24 PM
 */
public interface PersonageDAO {
    public void addPersonage(Personage personage) throws SQLException;

    public void updatePersonage(Personage personage) throws SQLException;

    public Personage getPersonageById(int personageId) throws SQLException;

    public List<Personage> getAllPersonages() throws SQLException;

    public void deletePersonage(Personage personage) throws SQLException;

    public List<Personage> getPersonagesByRace(Race race) throws SQLException;
}
