package web.rest.dto;

import entity.AttachedSkill;
import entity.Personage;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:22 PM
 */
public class PersonageHasAttachedSkillDTO {
    private int id;

    private AttachedSkillDTO attachedSkill;

    private PersonageDTO personage;

    private int currentValue;

    public PersonageHasAttachedSkillDTO() {
    }

    public PersonageHasAttachedSkillDTO(int id, AttachedSkillDTO attachedSkill, PersonageDTO personage, int currentValue) {
        this.id = id;
        this.attachedSkill = attachedSkill;
        this.personage = personage;
        this.currentValue = currentValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AttachedSkillDTO getAttachedSkill() {
        return attachedSkill;
    }

    public void setAttachedSkill(AttachedSkillDTO attachedSkill) {
        this.attachedSkill = attachedSkill;
    }

    public PersonageDTO getPersonage() {
        return personage;
    }

    public void setPersonage(PersonageDTO personage) {
        this.personage = personage;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
