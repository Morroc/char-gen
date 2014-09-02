package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:23 PM
 */
public class PersonageHasAttributeDTO {
    private int id;

    private AttributeDTO attribute;

    private int currentValue;

    public PersonageHasAttributeDTO() {
    }

    public PersonageHasAttributeDTO(int id, AttributeDTO attribute, int currentValue) {
        this.id = id;
        this.attribute = attribute;
        this.currentValue = currentValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AttributeDTO getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeDTO attribute) {
        this.attribute = attribute;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
