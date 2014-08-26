package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:20 PM
 */
public class MeritDTO {
    private int id;

    private String name;

    private int cost;

    private String description;

    private String preconditions;

    private String actionBonus;

    public MeritDTO(int id, String name, int cost, String description, String preconditions, String actionBonus) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.preconditions = preconditions;
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

    public String getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(String preconditions) {
        this.preconditions = preconditions;
    }

    public String getActionBonus() {
        return actionBonus;
    }

    public void setActionBonus(String actionBonus) {
        this.actionBonus = actionBonus;
    }
}
