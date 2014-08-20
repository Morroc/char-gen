package services;

import DAO.PersonageHasMeritDAO;
import entity.PersonageHasMerit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: artemk
 * Date: 8/19/14
 * Time: 4:19 PM
 */
@Service
public class PersonageHasMeritService {
    @Autowired
    private PersonageHasMeritDAO personageHasMeritDAO;

    @Transactional
    public PersonageHasMerit getPersonageHasMeritByMeritIdAndPersonageId(int meritId, int personageId) {
        return personageHasMeritDAO.getPersonageHasMeritByMeritIdAndPersonageId(meritId, personageId);
    }

    @Transactional
    public List<PersonageHasMerit> getPersonageHasMeritsByPersonageId(int personageId) {
        return personageHasMeritDAO.getPersonageHasMeritsByPersonageId(personageId);
    }

    @Transactional
    public PersonageHasMerit getPersonageHasMeritById(int personageHasMeritId) {
        return personageHasMeritDAO.getPersonageHasMeritById(personageHasMeritId);
    }

    @Transactional
    public void addLinkMeritWithPersonage(PersonageHasMerit personageHasMerit) {
        personageHasMeritDAO.addPersonageHasMerit(personageHasMerit);
    }

    @Transactional
    public void deleteLinkMeritWithPersonage(PersonageHasMerit personageHasMerit) {
        personageHasMeritDAO.deletePersonageHasMerit(personageHasMerit);
    }

    @Transactional
    public void updatePersonageHasMerit(PersonageHasMerit personageHasMerit) {
        personageHasMeritDAO.updatePersonageHasMerit(personageHasMerit);
    }

}
