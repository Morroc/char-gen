package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 2/19/14
 * Time: 1:01 PM
 */
@Entity
@Table(name = "attribute")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "cost")
    private int value;
    @Column(name = "max_value")
    private int maxValue;
    @Column(name = "min_value")
    private int minValue;
    @Column(name = "from_1_to_3_non_generating_cost")
    private int from1To3NonGeneratingCost;
    @Column(name = "cost_rise_coefficient")
    private double costRiseCoefficient;
    @Column(name = "non_generating_rise_coefficient")
    private double nonGeneratingRiseCoefficient;
    @Column(name = "action_level_bonus")
    private String actionLevelBonus;

    public Attribute() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getActionLevelBonus() {
        return actionLevelBonus;
    }

    public void setActionLevelBonus(String actionLevelBonus) {
        this.actionLevelBonus = actionLevelBonus;
    }
}
