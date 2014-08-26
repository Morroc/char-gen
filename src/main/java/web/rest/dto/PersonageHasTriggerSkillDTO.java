package web.rest.dto;

import enums.SkillLevel;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:27 PM
 */
public class PersonageHasTriggerSkillDTO {
    private int id;

    private SkillLevel currentLevel;

    private boolean hasTalent;

    private boolean hasTeacher;

    public PersonageHasTriggerSkillDTO(int id, SkillLevel currentLevel, boolean hasTalent, boolean hasTeacher) {
        this.id = id;
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
