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
    public void addPersonage(Personage personage);

    public void updatePersonage(Personage personage);

    public Personage getPersonageById(int personageId);

    public Personage getPersonageByName(String personageName);

    public List<Personage> getAllPersonages();

    public void deletePersonage(Personage personage);

    public List<Personage> getPersonagesByRace(Race race);
}
