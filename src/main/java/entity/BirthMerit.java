package entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:14 PM
 */
@Entity
@Table(name = "birth_merit")
public class BirthMerit {
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raceByBirthMerit")
    private Set<RaceHasBirthMerit> raceHasBirthMerits;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByBirthMerit")
    private Set<PersonageHasBirthMerit> personageHasBirthMerits;

    public BirthMerit() {
    }

    public BirthMerit(int id, String name, int cost, String description, String actionBonus) {
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

    public Set<RaceHasBirthMerit> getRaceHasBirthMerits() {
        return raceHasBirthMerits;
    }

    public void setRaceHasBirthMerits(Set<RaceHasBirthMerit> raceHasBirthMerits) {
        this.raceHasBirthMerits = raceHasBirthMerits;
    }

    public Set<PersonageHasBirthMerit> getPersonageHasBirthMerits() {
        return personageHasBirthMerits;
    }

    public void setPersonageHasBirthMerits(Set<PersonageHasBirthMerit> personageHasBirthMerits) {
        this.personageHasBirthMerits = personageHasBirthMerits;
    }
}
