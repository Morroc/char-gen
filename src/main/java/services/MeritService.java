package services;

import DAO.MeritDAO;
import DAO.PersonageHasMeritDAO;
import DAO.RaceHasMeritDAO;
import entity.Merit;
import entity.Race;
import entity.RaceHasMerit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * User: artemk
 * Date: 8/7/14
 * Time: 2:14 PM
 */
@Service
public class MeritService {
    @Autowired
    private MeritDAO meritDAO;

    @Transactional
    public List<Merit> getAllMerits() {
        return meritDAO.getAllMerits();
    }

    @Transactional
    public void addMerit(Merit merit) {
        meritDAO.addMerit(merit);
    }

    @Transactional
    public void updateMerit(Merit merit) {
        meritDAO.updateMerit(merit);
    }

    @Transactional
    public void deleteMeritById(int meritId) {
        meritDAO.deleteMerit(meritDAO.getMeritById(meritId));
    }
}
