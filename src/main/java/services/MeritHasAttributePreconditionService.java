package services;

import DAO.MeritHasAttributePreconditionDAO;
import entity.MeritHasAttributePrecondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by artemk on 9/17/14.
 */
@Service
public class MeritHasAttributePreconditionService {
    @Autowired
    private MeritHasAttributePreconditionDAO meritHasAttributePreconditionDAO;

    @Transactional
    public MeritHasAttributePrecondition getMeritHasAttributePreconditionByMeritIdAndattributeId(int meritId, int attributeId) {
        return meritHasAttributePreconditionDAO.getMeritHasAttributePreconditionByMeritIdAndAttributeId(meritId, attributeId);
    }

    @Transactional
    public List<MeritHasAttributePrecondition> getMeritHasAttributePreconditionsByMeritId(int meritId) {
        return meritHasAttributePreconditionDAO.getMeritHasAttributePreconditionsByMeritId(meritId);
    }

    @Transactional
    public MeritHasAttributePrecondition getMeritHasAttributePreconditionById(int meritHasAttributePreconditionId) {
        return meritHasAttributePreconditionDAO.getMeritHasAttributePreconditionById(meritHasAttributePreconditionId);
    }

    @Transactional
    public void addLinkAttributeWithMerit(MeritHasAttributePrecondition meritHasAttributePrecondition) {
        meritHasAttributePreconditionDAO.addMeritHasAttributePrecondition(meritHasAttributePrecondition);
    }

    @Transactional
    public void deleteLinkAttributeWithMerit(MeritHasAttributePrecondition meritHasAttributePrecondition) {
        meritHasAttributePreconditionDAO.deleteMeritHasAttributePrecondition(meritHasAttributePrecondition);
    }

    @Transactional
    public void deleteLinkAttributeWithMeritById(Integer id) {
        deleteLinkAttributeWithMerit(getMeritHasAttributePreconditionById(id));
    }

    @Transactional
    public void updateMeritHasAttributePrecondition(MeritHasAttributePrecondition meritHasAttributePrecondition) {
        meritHasAttributePreconditionDAO.updateMeritHasAttributePrecondition(meritHasAttributePrecondition);
    }
}
