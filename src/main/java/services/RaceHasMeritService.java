package services;

import DAO.RaceHasMeritDAO;
import entity.RaceHasMerit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: artemk
 * Date: 8/19/14
 * Time: 3:02 PM
 */
@Service
public class RaceHasMeritService {
    @Autowired
    private RaceHasMeritDAO raceHasMeritDAO;

    @Transactional
    public RaceHasMerit getRaceHasMeritByMeritIdAndRaceId(int meritId, int raceId) {
        return raceHasMeritDAO.getRaceHasMeritByMeritIdAndRaceId(meritId, raceId);
    }

    @Transactional
    public List<RaceHasMerit> getRaceHasMeritsByRaceId(int raceId) {
        return raceHasMeritDAO.getRaceHasMeritsByRaceId(raceId);
    }

    @Transactional
    public RaceHasMerit getRaceHasMeritById(int raceHasMeritId) {
        return raceHasMeritDAO.getRaceHasMeritById(raceHasMeritId);
    }

    @Transactional
    public void addLinkMeritWithRace(RaceHasMerit raceHasMerit) {
        raceHasMeritDAO.addRaceHasMerit(raceHasMerit);
    }

    @Transactional
    public void deleteLinkMeritWithRace(RaceHasMerit raceHasMerit) {
        raceHasMeritDAO.deleteRaceHasMerit(raceHasMerit);
    }
}
