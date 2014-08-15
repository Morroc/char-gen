package entity;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByMerit")
    private List<PersonageHasMerit> personagesByMerit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByTriggerSkill")
    private List<PersonageHasTriggerSkill> triggerSkills;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByAttachedSkill")
    private List<PersonageHasAttachedSkill> attachedSkills;


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

    public List<PersonageHasTriggerSkill> getTriggerSkills() {
        return triggerSkills;
    }

    public void setTriggerSkills(List<PersonageHasTriggerSkill> triggerSkills) {
        this.triggerSkills = triggerSkills;
    }

    public List<PersonageHasAttachedSkill> getAttachedSkills() {
        return attachedSkills;
    }

    public void setAttachedSkills(List<PersonageHasAttachedSkill> attachedSkills) {
        this.attachedSkills = attachedSkills;
    }

    public List<PersonageHasMerit> getPersonagesByMerit() {
        return personagesByMerit;
    }

    public void setPersonagesByMerit(List<PersonageHasMerit> personagesByMerit) {
        this.personagesByMerit = personagesByMerit;
    }
}
