package entity;

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

    @Column(name = "generated")
    private boolean generated;

    @Column(name = "experience")
    private int experience;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByMerit")
    private Set<PersonageHasMerit> personagesByMerit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByTriggerSkill")
    private Set<PersonageHasTriggerSkill> triggerSkills;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByAttachedSkill")
    private Set<PersonageHasAttachedSkill> attachedSkills;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByAttribute")
    private Set<PersonageHasAttribute> personageHasAttributes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByBirthMerit")
    private Set<PersonageHasBirthMerit> personageHasBirthMerits;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByFlaw")
    private Set<PersonageHasFlaw> personageHasFlaws;


    public Personage() {
    }

    public Personage(int id, String name, int age, Race race, boolean generated, int experience) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.race = race;
        this.generated = generated;
        this.experience = experience;
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

    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
