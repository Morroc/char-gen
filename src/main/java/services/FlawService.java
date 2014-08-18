package services;

import DAO.FlawDAO;
import entity.Flaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: artemk
 * Date: 8/18/14
 * Time: 1:08 PM
 */
@Service
public class FlawService {
    @Autowired
    private FlawDAO flawDAO;

    @Transactional
    public List<Flaw> getAllFlaws() {
        return flawDAO.getAllFlaws();
    }

    @Transactional
    public void addFlaw(Flaw flaw) {
        flawDAO.addFlaw(flaw);
    }

    @Transactional
    public void updateFlaw(Flaw flaw) {
        flawDAO.addFlaw(flaw);
    }

    @Transactional
    public void deleteFlawById(int flawId) {
        flawDAO.deleteFlaw(flawDAO.getFlawById(flawId));
    }
}
