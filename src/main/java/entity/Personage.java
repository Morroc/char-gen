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
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personageByMeritId")
    private List<Merit> personagesByMerit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personage")
    private List<TriggerSkill> triggerSkills;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personage")
    private List<AttachedSkill> attachedSkills;


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

    public List<TriggerSkill> getTriggerSkills() {
        return triggerSkills;
    }

    public void setTriggerSkills(List<TriggerSkill> triggerSkills) {
        this.triggerSkills = triggerSkills;
    }

    public List<AttachedSkill> getAttachedSkills() {
        return attachedSkills;
    }

    public void setAttachedSkills(List<AttachedSkill> attachedSkills) {
        this.attachedSkills = attachedSkills;
    }
}
