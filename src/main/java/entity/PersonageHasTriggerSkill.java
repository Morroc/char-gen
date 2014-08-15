package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 8/14/14
 * Time: 12:15 PM
 */
@Entity
@Table(name = "personage_has_trigger_skill")
public class PersonageHasTriggerSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "trigger_skill_id")
    private TriggerSkill triggerSkillByPersonage;

    @ManyToOne
    @JoinColumn(name = "personage_id")
    private Personage personageByTriggerSkill;

    @Column(name = "expert_cost")
    private int expertCost;

    @Column(name = "master_cost")
    private int masterCost;

    @Column(name = "post_master_cost")
    private int postMasterCost;

    @Column(name = "talant")
    private boolean talant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isTalant() {
        return talant;
    }

    public void setTalant(boolean talant) {
        this.talant = talant;
    }

    public TriggerSkill getTriggerSkillByPersonage() {
        return triggerSkillByPersonage;
    }

    public void setTriggerSkillByPersonage(TriggerSkill triggerSkillByPersonage) {
        this.triggerSkillByPersonage = triggerSkillByPersonage;
    }

    public Personage getPersonageByTriggerSkill() {
        return personageByTriggerSkill;
    }

    public void setPersonageByTriggerSkill(Personage personageByTriggerSkill) {
        this.personageByTriggerSkill = personageByTriggerSkill;
    }
}
