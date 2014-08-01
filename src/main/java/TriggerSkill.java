package main.java;

import main.java.enums.SkillLevel;
import main.java.enums.SkillType;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:19 PM
 */
public class TriggerSkill {
    private String name;
    private boolean isGeneratingPrice;
    private SkillType type;
    private int nonGeneratingCostCoefficient;
    private boolean isTalented;
    private SkillLevel level;
    private boolean isTeacherPresent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGeneratingPrice() {
        return isGeneratingPrice;
    }

    public void setGeneratingPrice(boolean generatingPrice) {
        isGeneratingPrice = generatingPrice;
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

    public boolean isTalented() {
        return isTalented;
    }

    public void setTalented(boolean talented) {
        isTalented = talented;
    }

    public SkillLevel getLevel() {
        return level;
    }

    public void setLevel(SkillLevel level) {
        this.level = level;
    }

    public boolean isTeacherPresent() {
        return isTeacherPresent;
    }

    public void setTeacherPresent(boolean teacherPresent) {
        isTeacherPresent = teacherPresent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TriggerSkill)) return false;

        TriggerSkill that = (TriggerSkill) o;

        if (isGeneratingPrice != that.isGeneratingPrice) return false;
        if (isTalented != that.isTalented) return false;
        if (isTeacherPresent != that.isTeacherPresent) return false;
        if (nonGeneratingCostCoefficient != that.nonGeneratingCostCoefficient) return false;
        if (level != that.level) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (isGeneratingPrice ? 1 : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + nonGeneratingCostCoefficient;
        result = 31 * result + (isTalented ? 1 : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (isTeacherPresent ? 1 : 0);
        return result;
    }
}
