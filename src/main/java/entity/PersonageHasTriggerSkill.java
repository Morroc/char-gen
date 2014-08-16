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

    @Column(name = "has_talant")
    private boolean hasTalant;

    @Column(name = "has_teacher")
    private boolean hasTeacher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHasTalant() {
        return hasTalant;
    }

    public void setHasTalant(boolean talant) {
        this.hasTalant = talant;
    }

    public boolean isHasTeacher() {
        return hasTeacher;
    }

    public void setHasTeacher(boolean hasTeacher) {
        this.hasTeacher = hasTeacher;
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
