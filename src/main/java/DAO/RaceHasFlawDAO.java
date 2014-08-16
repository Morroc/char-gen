package DAO;

import entity.RaceHasFlaw;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 7:00 PM
 */
public interface RaceHasFlawDAO {
    public void addRaceHasFlaw(RaceHasFlaw raceHasFlaw);

    public void updateRaceHasFlaw(RaceHasFlaw raceHasFlaw);

    public RaceHasFlaw getRaceHasFlawById(int raceHasFlawId);

    public List<RaceHasFlaw> getAllRaceHasFlaws();

    public void deleteRaceHasFlaw(RaceHasFlaw raceHasFlaw);

    public RaceHasFlaw getRaceHasFlawByFlawIdAndRaceId(int flawId, int raceId);

    public List<RaceHasFlaw> getRaceHasFlawByRaceId(int raceId);
}
