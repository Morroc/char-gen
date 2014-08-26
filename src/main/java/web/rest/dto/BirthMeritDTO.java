package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 2:37 PM
 */
public class BirthMeritDTO {
    private int id;

    private String name;

    private int cost;

    private String description;

    private String actionBonus;

    public BirthMeritDTO(int id, String name, int cost, String description, String actionBonus) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.actionBonus = actionBonus;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionBonus() {
        return actionBonus;
    }

    public void setActionBonus(String actionBonus) {
        this.actionBonus = actionBonus;
    }
}
