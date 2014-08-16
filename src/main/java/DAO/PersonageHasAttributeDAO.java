package DAO;

import entity.PersonageHasAttribute;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 3:22 PM
 */
public interface PersonageHasAttributeDAO {
    public void addPersonageHasAttribute(PersonageHasAttribute personageHasAttribute);

    public void updatePersonageHasAttribute(PersonageHasAttribute personageHasAttribute);

    public PersonageHasAttribute getPersonageHasAttributeById(int personageHasAttributeId);

    public List<PersonageHasAttribute> getAllPersonageHasAttributes();

    public void deletePersonageHasAttribute(PersonageHasAttribute personageHasAttribute);

    public PersonageHasAttribute getPersonageHasAttributeByAttributeIdAndPersonageId(int attributeId, int personageId);

    public List<PersonageHasAttribute> getPersonageHasAttributeByPersonageId(int personageId);
}
