package web.rest.dto;

import enums.SkillLevel;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:27 PM
 */
public class PersonageHasTriggerSkillDTO {
    private int id;

    private TriggerSkillDTO triggerSkill;

    private PersonageDTO personage;

    private SkillLevel currentLevel;

    private boolean hasTalent;

    private boolean hasTeacher;

    public PersonageHasTriggerSkillDTO() {
    }

    public PersonageHasTriggerSkillDTO(int id, TriggerSkillDTO triggerSkill, PersonageDTO personage,
                                       SkillLevel currentLevel, boolean hasTalent, boolean hasTeacher) {
        this.id = id;
        this.triggerSkill = triggerSkill;
        this.personage = personage;
        this.currentLevel = currentLevel;
        this.hasTalent = hasTalent;
        this.hasTeacher = hasTeacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TriggerSkillDTO getTriggerSkill() {
        return triggerSkill;
    }

    public void setTriggerSkill(TriggerSkillDTO triggerSkill) {
        this.triggerSkill = triggerSkill;
    }

    public PersonageDTO getPersonage() {
        return personage;
    }

    public void setPersonage(PersonageDTO personage) {
        this.personage = personage;
    }

    public SkillLevel getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(SkillLevel currentLevel) {
        this.currentLevel = currentLevel;
    }

    public boolean isHasTalent() {
        return hasTalent;
    }

    public void setHasTalent(boolean hasTalent) {
        this.hasTalent = hasTalent;
    }

    public boolean isHasTeacher() {
        return hasTeacher;
    }

    public void setHasTeacher(boolean hasTeacher) {
        this.hasTeacher = hasTeacher;
    }
}
