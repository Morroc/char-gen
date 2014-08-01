package main.java;

import java.util.Map;

/**
 * User: artemk
 * Date: 2/19/14
 * Time: 1:01 PM
 */
public class Attribute {
    private int cost;
    private int value;
    private int maxValue;
    private int minValue;
    private int from1To3NonGeneratingCost;
    private double costRiseCoefficient;
    private double nonGeneratingRiseCoefficient;
    private boolean isGeneratingPrice;
    private String actionLevelBonus;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getFrom1To3NonGeneratingCost() {
        return from1To3NonGeneratingCost;
    }

    public void setFrom1To3NonGeneratingCost(int from1To3NonGeneratingCost) {
        this.from1To3NonGeneratingCost = from1To3NonGeneratingCost;
    }

    public double getCostRiseCoefficient() {
        return costRiseCoefficient;
    }

    public void setCostRiseCoefficient(double costRiseCoefficient) {
        this.costRiseCoefficient = costRiseCoefficient;
    }

    public double getNonGeneratingRiseCoefficient() {
        return nonGeneratingRiseCoefficient;
    }

    public void setNonGeneratingRiseCoefficient(double nonGeneratingRiseCoefficient) {
        this.nonGeneratingRiseCoefficient = nonGeneratingRiseCoefficient;
    }

    public boolean isGeneratingPrice() {
        return isGeneratingPrice;
    }

    public void setGeneratingPrice(boolean generatingPrice) {
        isGeneratingPrice = generatingPrice;
    }

    public String getActionLevelBonus() {
        return actionLevelBonus;
    }

    public void setActionLevelBonus(String actionLevelBonus) {
        this.actionLevelBonus = actionLevelBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;

        Attribute attribute = (Attribute) o;

        if (cost != attribute.cost) return false;
        if (Double.compare(attribute.costRiseCoefficient, costRiseCoefficient) != 0) return false;
        if (from1To3NonGeneratingCost != attribute.from1To3NonGeneratingCost) return false;
        if (isGeneratingPrice != attribute.isGeneratingPrice) return false;
        if (maxValue != attribute.maxValue) return false;
        if (minValue != attribute.minValue) return false;
        if (Double.compare(attribute.nonGeneratingRiseCoefficient, nonGeneratingRiseCoefficient) != 0) return false;
        if (value != attribute.value) return false;
        if (actionLevelBonus != null ? !actionLevelBonus.equals(attribute.actionLevelBonus) : attribute.actionLevelBonus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = cost;
        result = 31 * result + value;
        result = 31 * result + maxValue;
        result = 31 * result + minValue;
        result = 31 * result + from1To3NonGeneratingCost;
        temp = Double.doubleToLongBits(costRiseCoefficient);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(nonGeneratingRiseCoefficient);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isGeneratingPrice ? 1 : 0);
        result = 31 * result + (actionLevelBonus != null ? actionLevelBonus.hashCode() : 0);
        return result;
    }
}
