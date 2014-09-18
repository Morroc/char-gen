package web.rest.dto;

import java.util.List;

/**
 * Created by artemk on 9/18/14.
 */
public class MeritWithPreconditionDTO {
    private MeritDTO merit;

    private List<MeritHasAttributePreconditionDTO> preconditions;

    public MeritWithPreconditionDTO() {
    }

    public MeritWithPreconditionDTO(MeritDTO merit, List<MeritHasAttributePreconditionDTO> preconditions) {
        this.merit = merit;
        this.preconditions = preconditions;
    }

    public MeritDTO getMerit() {
        return merit;
    }

    public void setMerit(MeritDTO merit) {
        this.merit = merit;
    }

    public List<MeritHasAttributePreconditionDTO> getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(List<MeritHasAttributePreconditionDTO> preconditions) {
        this.preconditions = preconditions;
    }
}
