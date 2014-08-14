package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 8/14/14
 * Time: 12:05 PM
 */
@Entity
@Table(name = "personage_has_attached_skill")
public class PersonageHasAttachedSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "attached_skill_id")
    private Merit attachedSkillByPersonage;

    @ManyToOne
    @JoinColumn(name = "personage_id")
    private Personage personageByAttachedSkill;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Merit getAttachedSkillByPersonage() {
        return attachedSkillByPersonage;
    }

    public void setAttachedSkillByPersonage(Merit attachedSkillByPersonage) {
        this.attachedSkillByPersonage = attachedSkillByPersonage;
    }

    public Personage getPersonageByAttachedSkill() {
        return personageByAttachedSkill;
    }

    public void setPersonageByAttachedSkill(Personage personageByAttachedSkill) {
        this.personageByAttachedSkill = personageByAttachedSkill;
    }
}
