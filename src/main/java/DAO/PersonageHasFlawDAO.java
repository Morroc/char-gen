package DAO;

import entity.PersonageHasFlaw;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 3:40 PM
 */
public interface PersonageHasFlawDAO {
    public void addPersonageHasFlaw(PersonageHasFlaw personageHasFlaw);

    public void updatePersonageHasFlaw(PersonageHasFlaw personageHasFlaw);

    public PersonageHasFlaw getPersonageHasFlawById(int personageHasFlawId);

    public List<PersonageHasFlaw> getAllPersonageHasFlaws();

    public void deletePersonageHasFlaw(PersonageHasFlaw personageHasFlaw);

    public PersonageHasFlaw getPersonageHasFlawByFlawIdAndPersonageId(int flawId, int personageId);

    public List<PersonageHasFlaw> getPersonageHasFlawByPersonageId(int personageId);
}
