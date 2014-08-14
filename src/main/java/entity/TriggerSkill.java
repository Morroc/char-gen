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
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private SkillType type;
    @Column(name = "non_generating_cost_coefficient")
    private int nonGeneratingCostCoefficient;
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

    public int getNonGeneratingCostCoefficient() {
        return nonGeneratingCostCoefficient;
    }

    public void setNonGeneratingCostCoefficient(int nonGeneratingCostCoefficient) {
        this.nonGeneratingCostCoefficient = nonGeneratingCostCoefficient;
    }

    public List<PersonageHasTriggerSkill> getTriggerSkillsByPersonage() {
        return triggerSkillsByPersonage;
    }

    public void setTriggerSkillsByPersonage(List<PersonageHasTriggerSkill> triggerSkillsByPersonage) {
        this.triggerSkillsByPersonage = triggerSkillsByPersonage;
    }
}
