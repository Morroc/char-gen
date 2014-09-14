package services;

import DAO.BirthMeritDAO;
import entity.BirthMerit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by artemk on 9/14/14.
 */
@Service
public class BirthMeritService {
    @Autowired
    private BirthMeritDAO birthMeritDAO;

    @Transactional
    public List<BirthMerit> getAllBirthMerits() {
        return birthMeritDAO.getAllBirthMerits();
    }

    @Transactional
    public void addBirthMerit(BirthMerit birthMerit) {
        birthMeritDAO.addBirthMerit(birthMerit);
    }

    @Transactional
    public void updateBirthMerit(BirthMerit birthMerit) {
        birthMeritDAO.updateBirthMerit(birthMerit);
    }

    @Transactional
    public BirthMerit getBirthMeritById(int birthMeritId) {
        return birthMeritDAO.getBirthMeritById(birthMeritId);
    }

    @Transactional
    public void deleteBirthMeritById(int birthMeritId) {
        birthMeritDAO.deleteBirthMerit(getBirthMeritById(birthMeritId));
    }
}
