package services;

import DAO.PersonageHasAttributeDAO;
import entity.PersonageHasAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 7:32 PM
 */
@Service
public class PersonageHasAttributeService {
    @Autowired
    private PersonageHasAttributeDAO personageHasAttributeDAO;

    @Transactional
    public PersonageHasAttribute getPersonageHasAttributeByAttributeIdAndPersonageId(int attributeId, int personageId) {
        return personageHasAttributeDAO.getPersonageHasAttributeByAttributeIdAndPersonageId(attributeId, personageId);
    }

    @Transactional
    public List<PersonageHasAttribute> getPersonageHasAttributesByPersonageId(int personageId) {
        return personageHasAttributeDAO.getPersonageHasAttributeByPersonageId(personageId);
    }

    @Transactional
    public PersonageHasAttribute getPersonageHasAttributeById(int personageHasAttributeId) {
        return personageHasAttributeDAO.getPersonageHasAttributeById(personageHasAttributeId);
    }

    @Transactional
    public void addLinkAttributeWithPersonage(PersonageHasAttribute personageHasAttribute) {
        personageHasAttributeDAO.addPersonageHasAttribute(personageHasAttribute);
    }

    @Transactional
    public void deleteLinkAttributeWithPersonage(PersonageHasAttribute personageHasAttribute) {
        personageHasAttributeDAO.deletePersonageHasAttribute(personageHasAttribute);
    }
}
