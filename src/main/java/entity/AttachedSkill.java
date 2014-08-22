package entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:34 PM
 */
@Entity
@Table(name = "attached_skill")
public class AttachedSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "base_cost")
    private int baseCost;

    @Column(name = "default_skill")
    private boolean defaultSkill;

    @Column(name = "difficult")
    private boolean difficult;

    @Column(name = "theoretical")
    private boolean theoretical;

    @Column(name = "acquiring_cost")
    private int acquiringCost;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attachedSkillByPersonage")
    private Set<PersonageHasAttachedSkill> attachedSkillsByPersonage;

    public AttachedSkill() {
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

    public int getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public int getAcquiringCost() {
        return acquiringCost;
    }

    public void setAcquiringCost(int acquiringCost) {
        this.acquiringCost = acquiringCost;
    }

    public boolean isDefaultSkill() {
        return defaultSkill;
    }

    public void setDefaultSkill(boolean defaultSkill) {
        this.defaultSkill = defaultSkill;
    }

    public boolean isDifficult() {
        return difficult;
    }

    public void setDifficult(boolean difficult) {
        this.difficult = difficult;
    }

    public boolean isTheoretical() {
        return theoretical;
    }

    public void setTheoretical(boolean theoretical) {
        this.theoretical = theoretical;
    }

    public Set<PersonageHasAttachedSkill> getAttachedSkillsByPersonage() {
        return attachedSkillsByPersonage;
    }

    public void setAttachedSkillsByPersonage(Set<PersonageHasAttachedSkill> attachedSkillsByPersonage) {
        this.attachedSkillsByPersonage = attachedSkillsByPersonage;
    }
}
