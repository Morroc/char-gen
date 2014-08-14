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
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "trigger_skill_id")
    private TriggerSkill triggerSkillByPersonage;

    @ManyToOne
    @JoinColumn(name = "personage_id")
    private Personage personageByTriggerSkill;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
