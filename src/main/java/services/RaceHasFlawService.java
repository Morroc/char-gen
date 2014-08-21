package services;

import DAO.RaceHasFlawDAO;
import entity.RaceHasFlaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: artemk
 * Date: 8/20/14
 * Time: 4:32 PM
 */
@Service
public class RaceHasFlawService {
    @Autowired
    private RaceHasFlawDAO raceHasFlawDAO;

    @Transactional
    public RaceHasFlaw getRaceHasFlawByFlawIdAndRaceId(int flawId, int raceId) {
        return raceHasFlawDAO.getRaceHasFlawByFlawIdAndRaceId(flawId, raceId);
    }

    @Transactional
    public List<RaceHasFlaw> getRaceHasFlawsByRaceId(int raceId) {
        return raceHasFlawDAO.getRaceHasFlawByRaceId(raceId);
    }

    @Transactional
    public RaceHasFlaw getRaceHasFlawById(int raceHasFlawId) {
        return raceHasFlawDAO.getRaceHasFlawById(raceHasFlawId);
    }

    @Transactional
    public void addLinkFlawWithRace(RaceHasFlaw raceHasFlaw) {
        raceHasFlawDAO.addRaceHasFlaw(raceHasFlaw);
    }

    @Transactional
    public void deleteLinkFlawWithRace(RaceHasFlaw raceHasFlaw) {
        raceHasFlawDAO.deleteRaceHasFlaw(raceHasFlaw);
    }
}
