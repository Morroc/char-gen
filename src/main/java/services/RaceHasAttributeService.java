package services;

import DAO.RaceHasAttributeDAO;
import entity.RaceHasAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 7:36 PM
 */
@Service
public class RaceHasAttributeService {
    @Autowired
    private RaceHasAttributeDAO raceHasAttributeDAO;

    @Transactional
    public RaceHasAttribute getRaceHasAttributeByAttributeIdAndRaceId(int attributeId, int raceId) {
        return raceHasAttributeDAO.getRaceHasAttributeByAttributeIdAndRaceId(attributeId, raceId);
    }

    @Transactional
    public List<RaceHasAttribute> getRaceHasAttributesByRaceId(int raceId) {
        return raceHasAttributeDAO.getRaceHasAttributeByRaceId(raceId);
    }

    @Transactional
    public RaceHasAttribute getRaceHasAttributeById(int raceHasAttributeId) {
        return raceHasAttributeDAO.getRaceHasAttributeById(raceHasAttributeId);
    }

    @Transactional
    public void addLinkAttributeWithRace(RaceHasAttribute raceHasAttribute) {
        raceHasAttributeDAO.addRaceHasAttribute(raceHasAttribute);
    }

    @Transactional
    public void deleteLinkAttributeWithRace(RaceHasAttribute raceHasAttribute) {
        raceHasAttributeDAO.deleteRaceHasAttribute(raceHasAttribute);
    }
}
