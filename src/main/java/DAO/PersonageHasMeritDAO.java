package DAO;

import entity.PersonageHasMerit;

import java.sql.SQLException;
import java.util.List;

/**
 * User: artemk
 * Date: 8/7/14
 * Time: 2:28 PM
 */
public interface PersonageHasMeritDAO {
    public void addPersonageHasMerit(PersonageHasMerit personageHasMerit);

    public void updatePersonageHasMerit(PersonageHasMerit personageHasMerit);

    public PersonageHasMerit getPersonageHasMeritById(int personageHasMeritId);

    public List<PersonageHasMerit> getAllPersonageHasMerits();

    public void deletePersonageHasMerit(PersonageHasMerit personageHasMerit);

    public PersonageHasMerit getPersonageHasMeritByMeritIdAndPersonageId(int meritId, int personageId);

    public List<PersonageHasMerit> getPersonageHasMeritsByPersonageId(int personageId);
}
