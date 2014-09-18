package entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @Column(name = "action_bonus")
    private String actionBonus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meritByRace")
    private Set<RaceHasMerit> raceHasMerits;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meritByPersonage")
    private Set<PersonageHasMerit> personageHasMerits;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meritByAttribute")
    private List<MeritHasAttributePrecondition> meritHasAttributePreconditions;

    public Merit() {
    }

    public Merit(int id, String name, int cost, String description, String actionBonus) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.actionBonus = actionBonus;
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

    public String getActionBonus() {
        return actionBonus;
    }

    public void setActionBonus(String actionBonus) {
        this.actionBonus = actionBonus;
    }

    public Set<RaceHasMerit> getRaceHasMerits() {
        return raceHasMerits;
    }

    public void setRaceHasMerits(Set<RaceHasMerit> raceHasMerits) {
        this.raceHasMerits = raceHasMerits;
    }

    public Set<PersonageHasMerit> getPersonageHasMerits() {
        return personageHasMerits;
    }

    public void setPersonageHasMerits(Set<PersonageHasMerit> personageHasMerits) {
        this.personageHasMerits = personageHasMerits;
    }

    public List<MeritHasAttributePrecondition> getMeritHasAttributePreconditions() {
        return meritHasAttributePreconditions;
    }

    public void setMeritHasAttributePreconditions(List<MeritHasAttributePrecondition> meritHasAttributePreconditions) {
        this.meritHasAttributePreconditions = meritHasAttributePreconditions;
    }
}
