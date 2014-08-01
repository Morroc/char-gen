package main.java;

import javafx.util.Pair;

import java.util.List;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:51 PM
 */
public class Flaw {
    private String name;
    private int cost;
    private String description;
    private List<Pair<Attribute, Integer>> turnOffPreconditions;
    private boolean isTurnOffAvailable;
    private int turnOffCost;

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

    public List<Pair<Attribute, Integer>> getTurnOffPreconditions() {
        return turnOffPreconditions;
    }

    public void setTurnOffPreconditions(List<Pair<Attribute, Integer>> turnOffPreconditions) {
        this.turnOffPreconditions = turnOffPreconditions;
    }

    public boolean isTurnOffAvailable() {
        return isTurnOffAvailable;
    }

    public void setTurnOffAvailable(boolean turnOffAvailable) {
        isTurnOffAvailable = turnOffAvailable;
    }

    public int getTurnOffCost() {
        return turnOffCost;
    }

    public void setTurnOffCost(int turnOffCost) {
        this.turnOffCost = turnOffCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flaw)) return false;

        Flaw flaw = (Flaw) o;

        if (cost != flaw.cost) return false;
        if (isTurnOffAvailable != flaw.isTurnOffAvailable) return false;
        if (turnOffCost != flaw.turnOffCost) return false;
        if (description != null ? !description.equals(flaw.description) : flaw.description != null) return false;
        if (name != null ? !name.equals(flaw.name) : flaw.name != null) return false;
        if (turnOffPreconditions != null ? !turnOffPreconditions.equals(flaw.turnOffPreconditions) : flaw.turnOffPreconditions != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + cost;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (turnOffPreconditions != null ? turnOffPreconditions.hashCode() : 0);
        result = 31 * result + (isTurnOffAvailable ? 1 : 0);
        result = 31 * result + turnOffCost;
        return result;
    }
}
