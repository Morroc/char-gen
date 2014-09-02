package web.rest.dto;

import entity.AttachedSkill;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:22 PM
 */
public class PersonageHasAttachedSkillDTO {
    private int id;

    private AttachedSkillDTO attachedSkill;

    private int currentValue;

    public PersonageHasAttachedSkillDTO() {
    }

    public PersonageHasAttachedSkillDTO(int id, AttachedSkillDTO attachedSkill, int currentValue) {
        this.id = id;
        this.attachedSkill = attachedSkill;
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

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
