package DAO;

import entity.*;
import entity.Character;

import java.sql.SQLException;
import java.util.Collection;

/**
 * User: artemk
 * Date: 8/3/14
 * Time: 2:24 PM
 */
public interface CharacterDAO {
    public void addCharacter(Character character) throws SQLException;

    public void updateCharacter(Character character) throws SQLException;

    public Character getCharacterById(int characterId) throws SQLException;

    public Collection getAllCharacters() throws SQLException;

    public void deleteCharacter(Character character) throws SQLException;

    public Collection getCharactersByRace(Race race) throws SQLException;
}
