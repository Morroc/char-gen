package entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

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
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "action_level_bonus")
    private String actionLevelBonus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raceByAttribute")
    private Set<RaceHasAttribute> racesByAttributes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByAttribute")
    private Set<PersonageHasAttribute> personageHasAttributes;

    public Attribute() {
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

    public String getActionLevelBonus() {
        return actionLevelBonus;
    }

    public void setActionLevelBonus(String actionLevelBonus) {
        this.actionLevelBonus = actionLevelBonus;
    }

    public Set<RaceHasAttribute> getRacesByAttributes() {
        return racesByAttributes;
    }

    public void setRacesByAttributes(Set<RaceHasAttribute> racesByAttributes) {
        this.racesByAttributes = racesByAttributes;
    }

    public Set<PersonageHasAttribute> getPersonageHasAttributes() {
        return personageHasAttributes;
    }

    public void setPersonageHasAttributes(Set<PersonageHasAttribute> personageHasAttributes) {
        this.personageHasAttributes = personageHasAttributes;
    }
}
