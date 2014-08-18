package entity;

import enums.SkillLevel;
import enums.SkillType;

import javax.persistence.*;
import java.lang.*;
import java.util.List;

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
    private List<PersonageHasTriggerSkill> triggerSkillsByPersonage;

    public TriggerSkill() {
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

    public List<PersonageHasTriggerSkill> getTriggerSkillsByPersonage() {
        return triggerSkillsByPersonage;
    }

    public void setTriggerSkillsByPersonage(List<PersonageHasTriggerSkill> triggerSkillsByPersonage) {
        this.triggerSkillsByPersonage = triggerSkillsByPersonage;
    }
}
