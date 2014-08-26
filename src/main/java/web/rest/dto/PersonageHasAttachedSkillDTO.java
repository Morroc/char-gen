package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:22 PM
 */
public class PersonageHasAttachedSkillDTO {
    private int id;

    private int currentValue;

    public PersonageHasAttachedSkillDTO(int id, int currentValue) {
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
