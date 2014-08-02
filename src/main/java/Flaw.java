import javafx.util.Pair;

import javax.persistence.*;
import java.util.List;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:51 PM
 */
@Entity
@Table(name = "flaw")
public class Flaw {
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
    @Column(name = "turnoffpreconditions")
    private String turnOffPreconditions;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    public Flaw() {
    }

    public Flaw(int id, String name, int cost, String description, String turnOffPreconditions, Character character, Race race) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.turnOffPreconditions = turnOffPreconditions;
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

    public String getTurnOffPreconditions() {
        return turnOffPreconditions;
    }

    public void setTurnOffPreconditions(String turnOffPreconditions) {
        this.turnOffPreconditions = turnOffPreconditions;
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
