package DAO;

import entity.PersonageHasBirthMerit;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 3:33 PM
 */
public interface PersonageHasBirthMeritDAO {
    public void addPersonageHasBirthMerit(PersonageHasBirthMerit personageHasBirthMerit);

    public void updatePersonageHasBirthMerit(PersonageHasBirthMerit personageHasBirthMerit);

    public PersonageHasBirthMerit getPersonageHasBirthMeritById(int personageHasBirthMeritId);

    public List<PersonageHasBirthMerit> getAllPersonageHasBirthMerits();

    public void deletePersonageHasBirthMerit(PersonageHasBirthMerit personageHasBirthMerit);

    public PersonageHasBirthMerit getPersonageHasBirthMeritByBirthMeritIdAndPersonageId(int birthMeritId, int personageId);

    public List<PersonageHasBirthMerit> getPersonageHasBirthMeritByPersonageId(int personageId);
}
