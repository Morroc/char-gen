package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:23 PM
 */
public class PersonageHasAttributeDTO {
    private int id;

    private int currentValue;

    public PersonageHasAttributeDTO() {
    }

    public PersonageHasAttributeDTO(int id, int currentValue) {
        this.id = id;
        this.currentValue = currentValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
