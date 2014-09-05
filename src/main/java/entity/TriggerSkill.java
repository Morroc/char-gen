package entity;

import enums.SkillType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:19 PM
 */
@Entity
@Table(name = "trigger_skill")
public class TriggerSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "type", columnDefinition = "enum('SIMPLE','BASIC','DIFFICULT')")
    @Enumerated(EnumType.STRING)
    private SkillType type;

    @Column(name = "base_cost")
    private int baseCost;

    @Column(name = "expert_cost")
    private int expertCost;

    @Column(name = "master_cost")
    private int masterCost;

    @Column(name = "post_master_cost")
    private int postMasterCost;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "triggerSkillByPersonage")
    private Set<PersonageHasTriggerSkill> triggerSkillsByPersonage;

    public TriggerSkill() {
    }

    public TriggerSkill(int id, String name, SkillType type, int baseCost, int expertCost, int masterCost, int postMasterCost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.baseCost = baseCost;
        this.expertCost = expertCost;
        this.masterCost = masterCost;
        this.postMasterCost = postMasterCost;
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

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public int getExpertCost() {
        return expertCost;
    }

    public void setExpertCost(int expertCost) {
        this.expertCost = expertCost;
    }

    public int getMasterCost() {
        return masterCost;
    }

    public void setMasterCost(int masterCost) {
        this.masterCost = masterCost;
    }

    public int getPostMasterCost() {
        return postMasterCost;
    }

    public void setPostMasterCost(int postMasterCost) {
        this.postMasterCost = postMasterCost;
    }

    public Set<PersonageHasTriggerSkill> getTriggerSkillsByPersonage() {
        return triggerSkillsByPersonage;
    }

    public void setTriggerSkillsByPersonage(Set<PersonageHasTriggerSkill> triggerSkillsByPersonage) {
        this.triggerSkillsByPersonage = triggerSkillsByPersonage;
    }
}
