package beans;

import javax.persistence.*;
import java.lang.*;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 8:18 PM
 */
@Entity
@Table(name = "merit")
public class Merit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private int cost;
    @Column(name = "description")
    private String description;
    @Column(name = "preconditions")
    private String preconditions;
    @Column(name = "actionbonus")
    private String actionBonus;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    public Merit() {
    }

    public Merit(int id, String name, int cost, String description, String preconditions,
                 String actionBonus, Character character, Race race) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.preconditions = preconditions;
        this.actionBonus = actionBonus;
        this.character = character;
        this.race = race;
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

    public String getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(String preconditions) {
        this.preconditions = preconditions;
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
