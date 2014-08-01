package main.java;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:34 PM
 */
public class AttachedSkill {
    private String name;
    private int value;
    private int baseCost;
    private boolean isGeneratingPrice;
    private boolean isDefault;
    private boolean isDifficult;
    private boolean isTheoretical;
    private double wisdomBonus;
    private int acquiringCost;

    private int getNonGeneratingRiseCost(int previousValue, int wantedValue) {
        int cost = 1;
        return cost;
    }

    private int getTheoreticalMax() {
        int cost = 1;
        return cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public boolean isGeneratingPrice() {
        return isGeneratingPrice;
    }

    public void setGeneratingPrice(boolean generatingPrice) {
        isGeneratingPrice = generatingPrice;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isDifficult() {
        return isDifficult;
    }

    public void setDifficult(boolean difficult) {
        isDifficult = difficult;
    }

    public boolean isTheoretical() {
        return isTheoretical;
    }

    public void setTheoretical(boolean theoretical) {
        isTheoretical = theoretical;
    }

    public double getWisdomBonus() {
        return wisdomBonus;
    }

    public void setWisdomBonus(double wisdomBonus) {
        this.wisdomBonus = wisdomBonus;
    }

    public int getAcquiringCost() {
        return acquiringCost;
    }

    public void setAcquiringCost(int acquiringCost) {
        this.acquiringCost = acquiringCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttachedSkill)) return false;

        AttachedSkill that = (AttachedSkill) o;

        if (acquiringCost != that.acquiringCost) return false;
        if (baseCost != that.baseCost) return false;
        if (isDefault != that.isDefault) return false;
        if (isDifficult != that.isDifficult) return false;
        if (isGeneratingPrice != that.isGeneratingPrice) return false;
        if (isTheoretical != that.isTheoretical) return false;
        if (value != that.value) return false;
        if (Double.compare(that.wisdomBonus, wisdomBonus) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + value;
        result = 31 * result + baseCost;
        result = 31 * result + (isGeneratingPrice ? 1 : 0);
        result = 31 * result + (isDefault ? 1 : 0);
        result = 31 * result + (isDifficult ? 1 : 0);
        result = 31 * result + (isTheoretical ? 1 : 0);
        temp = Double.doubleToLongBits(wisdomBonus);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + acquiringCost;
        return result;
    }
}
