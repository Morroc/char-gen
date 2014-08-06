package DAO;

import entity.*;
import entity.Personage;

import java.sql.SQLException;
import java.util.Collection;

/**
 * User: artemk
 * Date: 8/3/14
 * Time: 2:24 PM
 */
public interface PersonageDAO {
    public void addPersonage(Personage personage) throws SQLException;

    public void updatePersonage(Personage personage) throws SQLException;

    public Personage getPersonageById(int personageId) throws SQLException;

    public Collection getAllPersonages() throws SQLException;

    public void deletePersonage(Personage personage) throws SQLException;

    public Collection getPersonagesByRace(Race race) throws SQLException;
}
