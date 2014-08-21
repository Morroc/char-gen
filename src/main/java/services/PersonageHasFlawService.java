package services;

import DAO.PersonageHasFlawDAO;
import entity.PersonageHasFlaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: artemk
 * Date: 8/20/14
 * Time: 3:46 PM
 */
@Service
public class PersonageHasFlawService {
    @Autowired
    private PersonageHasFlawDAO personageHasFlawDAO;

    @Transactional
    public PersonageHasFlaw getPersonageHasFlawByFlawIdAndPersonageId(int flawId, int personageId) {
        return personageHasFlawDAO.getPersonageHasFlawByFlawIdAndPersonageId(flawId, personageId);
    }

    @Transactional
    public List<PersonageHasFlaw> getPersonageHasFlawsByPersonageId(int personageId) {
        return personageHasFlawDAO.getPersonageHasFlawsByPersonageId(personageId);
    }

    @Transactional
    public PersonageHasFlaw getPersonageHasFlawById(int personageHasFlawId) {
        return personageHasFlawDAO.getPersonageHasFlawById(personageHasFlawId);
    }

    @Transactional
    public void addLinkFlawWithPersonage(PersonageHasFlaw personageHasFlaw) {
        personageHasFlawDAO.addPersonageHasFlaw(personageHasFlaw);
    }

    @Transactional
    public void deleteLinkFlawWithPersonage(PersonageHasFlaw personageHasFlaw) {
        personageHasFlawDAO.deletePersonageHasFlaw(personageHasFlaw);
    }

    @Transactional
    public void updatePPersonageHasFlaw(PersonageHasFlaw personageHasFlaw) {
        personageHasFlawDAO.updatePersonageHasFlaw(personageHasFlaw);
    }
}
