package entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 9:09 PM
 */
@Entity
@Table(name = "personage")
public class Personage
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByMerit")
    private Set<PersonageHasMerit> personagesByMerit;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByTriggerSkill")
    private Set<PersonageHasTriggerSkill> triggerSkills;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByAttachedSkill")
    private Set<PersonageHasAttachedSkill> attachedSkills;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByAttribute")
    private Set<PersonageHasAttribute> personageHasAttributes;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByBirthMerit")
    private Set<PersonageHasBirthMerit> personageHasBirthMerits;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByFlaw")
    private Set<PersonageHasFlaw> personageHasFlaws;


    public Personage() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Set<PersonageHasMerit> getPersonagesByMerit() {
        return personagesByMerit;
    }

    public void setPersonagesByMerit(Set<PersonageHasMerit> personagesByMerit) {
        this.personagesByMerit = personagesByMerit;
    }

    public Set<PersonageHasTriggerSkill> getTriggerSkills() {
        return triggerSkills;
    }

    public void setTriggerSkills(Set<PersonageHasTriggerSkill> triggerSkills) {
        this.triggerSkills = triggerSkills;
    }

    public Set<PersonageHasAttachedSkill> getAttachedSkills() {
        return attachedSkills;
    }

    public void setAttachedSkills(Set<PersonageHasAttachedSkill> attachedSkills) {
        this.attachedSkills = attachedSkills;
    }

    public Set<PersonageHasAttribute> getPersonageHasAttributes() {
        return personageHasAttributes;
    }

    public void setPersonageHasAttributes(Set<PersonageHasAttribute> personageHasAttributes) {
        this.personageHasAttributes = personageHasAttributes;
    }

    public Set<PersonageHasBirthMerit> getPersonageHasBirthMerits() {
        return personageHasBirthMerits;
    }

    public void setPersonageHasBirthMerits(Set<PersonageHasBirthMerit> personageHasBirthMerits) {
        this.personageHasBirthMerits = personageHasBirthMerits;
    }

    public Set<PersonageHasFlaw> getPersonageHasFlaws() {
        return personageHasFlaws;
    }

    public void setPersonageHasFlaws(Set<PersonageHasFlaw> personageHasFlaws) {
        this.personageHasFlaws = personageHasFlaws;
    }
}
