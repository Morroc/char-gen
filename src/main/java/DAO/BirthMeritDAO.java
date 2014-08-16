package DAO;

import entity.BirthMerit;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 3:03 PM
 */
public interface BirthMeritDAO {
    public void addBirthMerit(BirthMerit birthMerit);

    public void updateBirthMerit(BirthMerit birthMerit);

    public BirthMerit getBirthMeritById(int birthMeritId);

    public BirthMerit getBirthMeritByName(String birthMeritName);

    public List<BirthMerit> getAllBirthMerits();

    public void deleteBirthMerit(BirthMerit birthMerit);
}
