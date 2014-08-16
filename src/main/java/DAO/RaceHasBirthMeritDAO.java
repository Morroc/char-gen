package DAO;

import entity.RaceHasBirthMerit;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 6:50 PM
 */
public interface RaceHasBirthMeritDAO {
    public void addRaceHasBirthMerit(RaceHasBirthMerit raceHasBirthMerit);

    public void updateRaceHasBirthMerit(RaceHasBirthMerit raceHasBirthMerit);

    public RaceHasBirthMerit getRaceHasBirthMeritById(int raceHasBirthMeritId);

    public List<RaceHasBirthMerit> getAllRaceHasBirthMerits();

    public void deleteRaceHasBirthMerit(RaceHasBirthMerit raceHasBirthMerit);

    public RaceHasBirthMerit getRaceHasBirthMeritByBirthMeritIdAndRaceId(int birthMeritId, int raceId);

    public List<RaceHasBirthMerit> getRaceHasBirthMeritByRaceId(int raceId);
}
