package web.rest.dto;

import enums.AttributePriority;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:23 PM
 */
public class PersonageHasAttributeDTO {
    private int id;

    private AttributeDTO attribute;

    private PersonageDTO personage;

    private int currentValue;

    private AttributePriority priority;

    public PersonageHasAttributeDTO() {
    }

    public PersonageHasAttributeDTO(int id, AttributeDTO attribute, PersonageDTO personage, int currentValue, AttributePriority priority) {
        this.id = id;
        this.attribute = attribute;
        this.personage = personage;
        this.currentValue = currentValue;
        this.priority = priority;
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

    public PersonageDTO getPersonage() {
        return personage;
    }

    public void setPersonage(PersonageDTO personage) {
        this.personage = personage;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public AttributePriority getPriority() {
        return priority;
    }

    public void setPriority(AttributePriority priority) {
        this.priority = priority;
    }
}
