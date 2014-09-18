package web.rest.dto;

/**
 * Created by artemk on 9/17/14.
 */
public class MeritHasAttributePreconditionDTO {
    private int id;

    private MeritDTO merit;

    private AttributeDTO attribute;

    private int neededValue;

    public MeritHasAttributePreconditionDTO() {
    }

    public MeritHasAttributePreconditionDTO(int id, MeritDTO merit, AttributeDTO attribute, int neededValue) {
        this.id = id;
        this.merit = merit;
        this.attribute = attribute;
        this.neededValue = neededValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MeritDTO getMerit() {
        return merit;
    }

    public void setMerit(MeritDTO merit) {
        this.merit = merit;
    }

    public AttributeDTO getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeDTO attribute) {
        this.attribute = attribute;
    }

    public int getNeededValue() {
        return neededValue;
    }

    public void setNeededValue(int neededValue) {
        this.neededValue = neededValue;
    }
}
