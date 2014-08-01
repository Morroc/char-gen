package main.java;

import java.util.List;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 9:09 PM
 */
public class Character {
    private String name;
    private String age;
    private Race race;
    private List<Attribute> attributes;
    private List<TriggerSkill> triggerSkills;
    private List<AttachedSkill> attachedSkills;
    private List<Merit> merits;
    private List<Flaw> flaws;
    private List<BirthMerit> birthMerits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<TriggerSkill> getTriggerSkills() {
        return triggerSkills;
    }

    public void setTriggerSkills(List<TriggerSkill> triggerSkills) {
        this.triggerSkills = triggerSkills;
    }

    public List<AttachedSkill> getAttachedSkills() {
        return attachedSkills;
    }

    public void setAttachedSkills(List<AttachedSkill> attachedSkills) {
        this.attachedSkills = attachedSkills;
    }

    public List<Merit> getMerits() {
        return merits;
    }

    public void setMerits(List<Merit> merits) {
        this.merits = merits;
    }

    public List<Flaw> getFlaws() {
        return flaws;
    }

    public void setFlaws(List<Flaw> flaws) {
        this.flaws = flaws;
    }

    public List<BirthMerit> getBirthMerits() {
        return birthMerits;
    }

    public void setBirthMerits(List<BirthMerit> birthMerits) {
        this.birthMerits = birthMerits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character)) return false;

        Character character = (Character) o;

        if (age != null ? !age.equals(character.age) : character.age != null) return false;
        if (attachedSkills != null ? !attachedSkills.equals(character.attachedSkills) : character.attachedSkills != null)
            return false;
        if (attributes != null ? !attributes.equals(character.attributes) : character.attributes != null) return false;
        if (birthMerits != null ? !birthMerits.equals(character.birthMerits) : character.birthMerits != null)
            return false;
        if (flaws != null ? !flaws.equals(character.flaws) : character.flaws != null) return false;
        if (merits != null ? !merits.equals(character.merits) : character.merits != null) return false;
        if (name != null ? !name.equals(character.name) : character.name != null) return false;
        if (race != null ? !race.equals(character.race) : character.race != null) return false;
        if (triggerSkills != null ? !triggerSkills.equals(character.triggerSkills) : character.triggerSkills != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (race != null ? race.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (triggerSkills != null ? triggerSkills.hashCode() : 0);
        result = 31 * result + (attachedSkills != null ? attachedSkills.hashCode() : 0);
        result = 31 * result + (merits != null ? merits.hashCode() : 0);
        result = 31 * result + (flaws != null ? flaws.hashCode() : 0);
        result = 31 * result + (birthMerits != null ? birthMerits.hashCode() : 0);
        return result;
    }
}
