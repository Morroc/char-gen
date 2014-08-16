package DAO;

import entity.RaceHasMerit;

import java.sql.SQLException;
import java.util.List;

/**
 * User: artemk
 * Date: 8/7/14
 * Time: 2:02 PM
 */
public interface RaceHasMeritDAO {
    public void addRaceHasMerit(RaceHasMerit raceHasMerit);

    public void updateRaceHasMerit(RaceHasMerit raceHasMerit);

    public RaceHasMerit getRaceHasMeritById(int raceHasMeritId);

    public List<RaceHasMerit> getAllRaceHasMerits();

    public void deleteRaceHasMerit(RaceHasMerit raceHasMerit);

    public RaceHasMerit getRaceHasMeritByMeritIdAndRaceId(int meritId, int raceId);

    public List<RaceHasMerit> getRaceHasMeritByRaceId(int raceId);
}
