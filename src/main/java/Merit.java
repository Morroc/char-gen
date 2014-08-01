package main.java;

import javafx.util.Pair;

import java.util.List;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 8:18 PM
 */
public class Merit {
    private String name;
    private int cost;
    private String description;
    private List<Pair<Attribute, Integer>> preconditions;
    private String actionBonus;

    private boolean isAvailable() {
        return true;
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

    public List<Pair<Attribute, Integer>> getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(List<Pair<Attribute, Integer>> preconditions) {
        this.preconditions = preconditions;
    }

    public String getActionBonus() {
        return actionBonus;
    }

    public void setActionBonus(String actionBonus) {
        this.actionBonus = actionBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Merit)) return false;

        Merit merit = (Merit) o;

        if (cost != merit.cost) return false;
        if (actionBonus != null ? !actionBonus.equals(merit.actionBonus) : merit.actionBonus != null) return false;
        if (description != null ? !description.equals(merit.description) : merit.description != null) return false;
        if (name != null ? !name.equals(merit.name) : merit.name != null) return false;
        if (preconditions != null ? !preconditions.equals(merit.preconditions) : merit.preconditions != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + cost;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (preconditions != null ? preconditions.hashCode() : 0);
        result = 31 * result + (actionBonus != null ? actionBonus.hashCode() : 0);
        return result;
    }
}
