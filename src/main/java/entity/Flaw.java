package entity;

import javax.persistence.*;
import java.lang.*;

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
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "cost")
    private int cost;
    @Column(name = "description")
    private String description;
    @Column(name = "turn_off_preconditions")
    private String turnOffPreconditions;

    public Flaw() {
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
}
