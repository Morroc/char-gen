package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:19 PM
 */
public class FlawDTO {
    private int id;

    private String name;

    private int cost;

    private String description;

    private String turnOffPreconditions;

    public FlawDTO() {
    }

    public FlawDTO(int id, String name, int cost, String description, String turnOffPreconditions) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.turnOffPreconditions = turnOffPreconditions;
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

    public String getTurnOffPreconditions() {
        return turnOffPreconditions;
    }

    public void setTurnOffPreconditions(String turnOffPreconditions) {
        this.turnOffPreconditions = turnOffPreconditions;
    }
}
