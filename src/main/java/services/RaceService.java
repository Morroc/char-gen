package services;

import DAO.RaceDAO;
import entity.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.rest.dto.RaceDTO;

import java.util.List;

/**
 * User: artemk
 * Date: 8/8/14
 * Time: 1:04 PM
 */
@Service
public class RaceService {
    @Autowired
    private RaceDAO raceDAO;

    @Transactional
    public void addRace(Race race) {
        raceDAO.addRace(race);
    }

    @Transactional
    public List<Race> getAllRaces() {
        return raceDAO.getAllRaces();
    }

    @Transactional
    public void deleteRaceById(int raceId) {
        raceDAO.deleteRace(raceDAO.getRaceById(raceId));
    }

    @Transactional
    public Race getRaceById(int raceId) {
        return raceDAO.getRaceById(raceId);
    }

    @Transactional
    public Race getRaceByName(String name) {
        return raceDAO.getRaceByName(name);
    }

    @Transactional
    public void updateRace(Race race) {
        raceDAO.updateRace(race);
    }
}
