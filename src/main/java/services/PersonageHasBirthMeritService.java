package services;

import DAO.PersonageHasBirthMeritDAO;
import entity.PersonageHasBirthMerit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: artemk
 * Date: 9/2/14
 * Time: 6:20 PM
 */
@Service
public class PersonageHasBirthMeritService {
    @Autowired
    private PersonageHasBirthMeritDAO personageHasBirthMeritDAO;

    @Transactional
    public PersonageHasBirthMerit getPersonageHasBirthMeritByBirthMeritIdAndPersonageId(int birthMeritId, int personageId) {
        return personageHasBirthMeritDAO.getPersonageHasBirthMeritByBirthMeritIdAndPersonageId(birthMeritId, personageId);
    }

    @Transactional
    public List<PersonageHasBirthMerit> getPersonageHasBirthMeritsByPersonageId(int personageId) {
        return personageHasBirthMeritDAO.getPersonageHasBirthMeritsByPersonageId(personageId);
    }

    @Transactional
    public PersonageHasBirthMerit getPersonageHasBirthMeritById(int personageHasBirthMeritId) {
        return personageHasBirthMeritDAO.getPersonageHasBirthMeritById(personageHasBirthMeritId);
    }

    @Transactional
    public void addLinkBirthMeritWithPersonage(PersonageHasBirthMerit personageHasBirthMerit) {
        personageHasBirthMeritDAO.addPersonageHasBirthMerit(personageHasBirthMerit);
    }

    @Transactional
    public void deleteLinkBirthMeritWithPersonage(PersonageHasBirthMerit personageHasBirthMerit) {
        personageHasBirthMeritDAO.deletePersonageHasBirthMerit(personageHasBirthMerit);
    }

    @Transactional
    public void updatePersonageHasBirthMerit(PersonageHasBirthMerit personageHasBirthMerit) {
        personageHasBirthMeritDAO.updatePersonageHasBirthMerit(personageHasBirthMerit);
    }
}
