package services;

import DAO.PersonageDAO;
import DAO.RaceDAO;
import entity.Personage;
import entity.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: artemk
 * Date: 8/12/14
 * Time: 10:03 AM
 */
@Service
public class PersonageService {
    @Autowired
    private PersonageDAO personageDAO;

    @Autowired
    private RaceDAO raceDAO;

    @Transactional
    public void addPersonage(Personage personage) {
        personageDAO.addPersonage(personage);
    }

    @Transactional
    public List<Personage> getAllPersonages() {
        return personageDAO.getAllPersonages();
    }

    @Transactional
    public void deletePersonageById(int personageId) {
        personageDAO.deletePersonage(personageDAO.getPersonageById(personageId));
    }

    @Transactional
    public Personage getPersonageById(int personageId) {
        return personageDAO.getPersonageById(personageId);
    }

    @Transactional
    public List<Personage> getPersonagesByRaceId(int raceId) {
        return personageDAO.getPersonagesByRace(raceDAO.getRaceById(raceId));
    }

    @Transactional
    public Integer getRaceIdByPersonageId(int personageId) {
        return personageDAO.getRaceIdOfPersonage(personageDAO.getPersonageById(personageId));
    }

    @Transactional
    public void updatePersonage(Personage personage) {
        personageDAO.updatePersonage(personage);
    }
}
