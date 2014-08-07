package entity;

import javax.persistence.*;
import java.lang.*;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meritByRaceId")
    private List<Merit> meritsByRace;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meritByPersonageId")
    private List<Merit> meritsByPersonage;

    public Merit() {
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

    public List<Merit> getMeritsByRace() {
        return meritsByRace;
    }

    public void setMeritsByRace(List<Merit> meritsByRace) {
        this.meritsByRace = meritsByRace;
    }

    public List<Merit> getMeritsByPersonage() {
        return meritsByPersonage;
    }

    public void setMeritsByPersonage(List<Merit> meritsByPersonage) {
        this.meritsByPersonage = meritsByPersonage;
    }
}
