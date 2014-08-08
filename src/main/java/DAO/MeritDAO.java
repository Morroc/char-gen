package DAO;

import entity.Merit;
import entity.Personage;
import entity.Race;

import java.sql.SQLException;
import java.util.List;

/**
 * User: artemk
 * Date: 8/6/14
 * Time: 4:01 PM
 */
public interface MeritDAO {
    public void addMerit(Merit merit);

    public void updateMerit(Merit merit);

    public Merit getMeritById(int meritId);

    public Merit getMeritByName(String meritName);

    public List<Merit> getAllMerits();

    public void deleteMerit(Merit merit);
}
