package services;

import DAO.MeritDAO;
import DAO.PersonageHasMeritDAO;
import DAO.RaceHasMeritDAO;
import entity.Merit;
import entity.Race;
import entity.RaceHasMerit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * User: artemk
 * Date: 8/7/14
 * Time: 2:14 PM
 */
@Service
public class MeritService {
    @Autowired
    private MeritDAO meritDAO;

    @Autowired
    private RaceHasMeritDAO raceHasMeritDAO;

    @Autowired
    private PersonageHasMeritDAO personageHasMeritDAO;

    @Transactional
    public void addMeritToRace(Merit merit, Race race) {
        meritDAO.addMerit(merit);
        RaceHasMerit raceHasMerit = new RaceHasMerit(merit, race);
        raceHasMeritDAO.addRaceHasMerit(raceHasMerit);
    }

    @Transactional
    public List<Merit> getAllMerits() {
        return meritDAO.getAllMerits();
    }
}
