package DAO;

import entity.RaceHasAttribute;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 6:39 PM
 */
public interface RaceHasAttributeDAO {
    public void addRaceHasAttribute(RaceHasAttribute raceHasAttribute);

    public void updateRaceHasAttribute(RaceHasAttribute raceHasAttribute);

    public RaceHasAttribute getRaceHasAttributeById(int raceHasAttributeId);

    public List<RaceHasAttribute> getAllRaceHasAttributes();

    public void deleteRaceHasAttribute(RaceHasAttribute raceHasAttribute);

    public RaceHasAttribute getRaceHasAttributeByAttributeIdAndRaceId(int attributeId, int raceId);

    public List<RaceHasAttribute> getRaceHasAttributeByRaceId(int raceId);
}
