package services;

import DAO.RaceHasBirthMeritDAO;
import entity.RaceHasBirthMerit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * Created by artemk on 9/14/14.
 */
@Service
public class RaceHasBirthMeritService {
    @Autowired
    private RaceHasBirthMeritDAO raceHasBirthMeritDAO;

    @Transactional
    public RaceHasBirthMerit getRaceHasBirthMeritByBirthMeritIdAndRaceId(int birthMeritId, int raceId) {
        return raceHasBirthMeritDAO.getRaceHasBirthMeritByBirthMeritIdAndRaceId(birthMeritId, raceId);
    }

    @Transactional
    public List<RaceHasBirthMerit> getRaceHasBirthMeritsByRaceId(int raceId) {
        return raceHasBirthMeritDAO.getRaceHasBirthMeritsByRaceId(raceId);
    }

    @Transactional
    public RaceHasBirthMerit getRaceHasBirthMeritById(int raceHasBirthMeritId) {
        return raceHasBirthMeritDAO.getRaceHasBirthMeritById(raceHasBirthMeritId);
    }

    @Transactional
    public void addLinkBirthMeritWithRace(RaceHasBirthMerit raceHasBirthMerit) {
        raceHasBirthMeritDAO.addRaceHasBirthMerit(raceHasBirthMerit);
    }

    @Transactional
    public void deleteLinkBirthMeritWithRace(RaceHasBirthMerit raceHasBirthMerit) {
        raceHasBirthMeritDAO.deleteRaceHasBirthMerit(raceHasBirthMerit);
    }

    public boolean roleBirthMerit(int probability) {
        boolean isPresent = false;
        Random random = new Random();
        if(probability <= 1) {
            isPresent = true;
        } else {
            if (random.nextInt(probability) == 0) {
                isPresent = true;
            }
        }
        return isPresent;
    }
}
