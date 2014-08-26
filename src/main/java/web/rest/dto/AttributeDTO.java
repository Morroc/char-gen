package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 2:35 PM
 */
public class AttributeDTO {
    private int id;

    private String name;

    private String actionLevelBonus;

    public AttributeDTO() {
    }

    public AttributeDTO(int id, String name, String actionLevelBonus) {
        this.id = id;
        this.name = name;
        this.actionLevelBonus = actionLevelBonus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActionLevelBonus() {
        return actionLevelBonus;
    }

    public void setActionLevelBonus(String actionLevelBonus) {
        this.actionLevelBonus = actionLevelBonus;
    }
}
