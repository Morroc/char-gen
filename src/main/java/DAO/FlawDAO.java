package DAO;

import entity.Flaw;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 3:15 PM
 */
public interface FlawDAO {
    public void addFlaw(Flaw flaw);

    public void updateFlaw(Flaw flaw);

    public Flaw getFlawById(int flawId);

    public Flaw getFlawByName(String flawName);

    public List<Flaw> getAllFlaws();

    public void deleteFlaw(Flaw flaw);
}
