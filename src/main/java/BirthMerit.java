package main.java;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:14 PM
 */
public class BirthMerit {
    private String name;
    private double probability;
    private int value;
    private int cost;
    private String description;
    private String actionBonus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BirthMerit)) return false;

        BirthMerit that = (BirthMerit) o;

        if (cost != that.cost) return false;
        if (Double.compare(that.probability, probability) != 0) return false;
        if (value != that.value) return false;
        if (actionBonus != null ? !actionBonus.equals(that.actionBonus) : that.actionBonus != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(probability);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + value;
        result = 31 * result + cost;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (actionBonus != null ? actionBonus.hashCode() : 0);
        return result;
    }
}
