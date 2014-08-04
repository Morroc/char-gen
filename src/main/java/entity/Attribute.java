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
    private int cost;
    @Column(name = "value")
    private int value;
    @Column(name = "maxvalue")
    private int maxValue;
    @Column(name = "minvalue")
    private int minValue;
    @Column(name = "from1to3nongeneratingcost")
    private int from1To3NonGeneratingCost;
    @Column(name = "costrisecoefficient")
    private double costRiseCoefficient;
    @Column(name = "nongeneratingrisecoefficient")
    private double nonGeneratingRiseCoefficient;
    @Column(name = "actionlevelbonus")
    private String actionLevelBonus;
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    public Attribute() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getActionLevelBonus() {
        return actionLevelBonus;
    }

    public void setActionLevelBonus(String actionLevelBonus) {
        this.actionLevelBonus = actionLevelBonus;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
