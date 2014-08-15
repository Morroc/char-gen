package entity;

import javax.persistence.*;
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
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "cost")
    private int cost;
    @Column(name = "description")
    private String description;
    @Column(name = "preconditions")
    private String preconditions;
    @Column(name = "action_bonus")
    private String actionBonus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meritByRace")
    private List<RaceHasMerit> meritsByRace;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meritByPersonage")
    private List<PersonageHasMerit> meritsByPersonage;

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

    public List<RaceHasMerit> getMeritsByRace() {
        return meritsByRace;
    }

    public void setMeritsByRace(List<RaceHasMerit> meritsByRace) {
        this.meritsByRace = meritsByRace;
    }

    public List<PersonageHasMerit> getMeritsByPersonage() {
        return meritsByPersonage;
    }

    public void setMeritsByPersonage(List<PersonageHasMerit> meritsByPersonage) {
        this.meritsByPersonage = meritsByPersonage;
    }
}
