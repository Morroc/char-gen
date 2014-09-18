package DAO;

import entity.MeritHasAttributePrecondition;

import java.util.List;

/**
 * Created by artemk on 9/17/14.
 */
public interface MeritHasAttributePreconditionDAO {
    public void addMeritHasAttributePrecondition(MeritHasAttributePrecondition meritHasAttributePrecondition);

    public void updateMeritHasAttributePrecondition(MeritHasAttributePrecondition meritHasAttributePrecondition);

    public MeritHasAttributePrecondition getMeritHasAttributePreconditionById(int meritHasAttributePreconditionId);

    public List<MeritHasAttributePrecondition> getAllMeritHasAttributePreconditions();

    public void deleteMeritHasAttributePrecondition(MeritHasAttributePrecondition meritHasAttributePrecondition);

    public MeritHasAttributePrecondition getMeritHasAttributePreconditionByMeritIdAndAttributeId(int meritId, int attributeId);

    public List<MeritHasAttributePrecondition> getMeritHasAttributePreconditionsByMeritId(int personageId);
}
