package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:34 PM
 */
@Entity
@Table(name = "attachedskill")
public class AttachedSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private int value;
    @Column(name = "basecost")
    private int baseCost;
    @Column(name = "default")
    private boolean isDefault;
    @Column(name = "difficult")
    private boolean isDifficult;
    @Column(name = "theoretical")
    private boolean isTheoretical;
    @Column(name = "acquiringcost")
    private int acquiringCost;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    public AttachedSkill() {
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

    public int getAcquiringCost() {
        return acquiringCost;
    }

    public void setAcquiringCost(int acquiringCost) {
        this.acquiringCost = acquiringCost;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    //    private int getNonGeneratingRiseCost(int previousValue, int wantedValue) {
//        int cost = 1;
//        return cost;
//    }

//    private int getTheoreticalMax() {
//        int cost = 1;
//        return cost;
//    }

//    private boolean isGeneratingPrice() {
//        return true;
//    }
}
