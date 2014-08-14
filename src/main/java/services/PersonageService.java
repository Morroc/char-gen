package services;

import DAO.PersonageDAO;
import entity.Personage;
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
}
