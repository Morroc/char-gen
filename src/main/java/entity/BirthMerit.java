package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:14 PM
 */
@Entity
@Table(name = "birthmerit")
public class BirthMerit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "probability")
    private double probability;
    @Column(name = "value")
    private int value;
    @Column(name = "cost")
    private int cost;
    @Column(name = "description")
    private String description;
    @Column(name = "actionbonus")
    private String actionBonus;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    public BirthMerit() {
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

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
