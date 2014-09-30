package web.rest.dto;

import java.util.List;

/**
 * User: artemk
 * Date: 9/2/14
 * Time: 3:08 PM
 */
public class PersonageWithAllRelatedEntitiesDTO {
    private PersonageDTO personage;

    private List<PersonageHasAttachedSkillDTO> personageAttachedSkills;

    private List<PersonageHasTriggerSkillDTO> personageTriggerSkills;

    private List<PersonageHasAttributeDTO> personageAttributes;

    private List<PersonageHasFlawDTO> personageFlaws;

    private List<PersonageHasMeritDTO> personageMerits;

    private List<PersonageHasBirthMeritDTO> personageBirthMerits;

    private boolean primaryAttributeSet = false;

    private boolean secondaryAttributeSet = false;

    public PersonageWithAllRelatedEntitiesDTO() {
    }

    public PersonageWithAllRelatedEntitiesDTO(PersonageDTO personage,
                                              List<PersonageHasAttachedSkillDTO> personageAttachedSkills,
                                              List<PersonageHasTriggerSkillDTO> personageTriggerSkills,
                                              List<PersonageHasAttributeDTO> personageAttributes,
                                              List<PersonageHasFlawDTO> personageFlaws,
                                              List<PersonageHasMeritDTO> personageMerits,
                                              List<PersonageHasBirthMeritDTO> personageBirthMerits) {
        this.personage = personage;
        this.personageAttachedSkills = personageAttachedSkills;
        this.personageTriggerSkills = personageTriggerSkills;
        this.personageAttributes = personageAttributes;
        this.personageFlaws = personageFlaws;
        this.personageMerits = personageMerits;
        this.personageBirthMerits = personageBirthMerits;
    }

    public PersonageDTO getPersonage() {
        return personage;
    }

    public void setPersonage(PersonageDTO personage) {
        this.personage = personage;
    }

    public List<PersonageHasAttachedSkillDTO> getPersonageAttachedSkills() {
        return personageAttachedSkills;
    }

    public void setPersonageAttachedSkills(List<PersonageHasAttachedSkillDTO> personageAttachedSkills) {
        this.personageAttachedSkills = personageAttachedSkills;
    }

    public List<PersonageHasTriggerSkillDTO> getPersonageTriggerSkills() {
        return personageTriggerSkills;
    }

    public void setPersonageTriggerSkills(List<PersonageHasTriggerSkillDTO> personageTriggerSkills) {
        this.personageTriggerSkills = personageTriggerSkills;
    }

    public List<PersonageHasAttributeDTO> getPersonageAttributes() {
        return personageAttributes;
    }

    public void setPersonageAttributes(List<PersonageHasAttributeDTO> personageAttributes) {
        this.personageAttributes = personageAttributes;
    }

    public List<PersonageHasFlawDTO> getPersonageFlaws() {
        return personageFlaws;
    }

    public void setPersonageFlaws(List<PersonageHasFlawDTO> personageFlaws) {
        this.personageFlaws = personageFlaws;
    }

    public List<PersonageHasMeritDTO> getPersonageMerits() {
        return personageMerits;
    }

    public void setPersonageMerits(List<PersonageHasMeritDTO> personageMerits) {
        this.personageMerits = personageMerits;
    }

    public List<PersonageHasBirthMeritDTO> getPersonageBirthMerits() {
        return personageBirthMerits;
    }

    public void setPersonageBirthMerits(List<PersonageHasBirthMeritDTO> personageBirthMerits) {
        this.personageBirthMerits = personageBirthMerits;
    }

    public boolean isPrimaryAttributeSet() {
        return primaryAttributeSet;
    }

    public void setPrimaryAttributeSet(boolean isPrimaryAttributeSet) {
        this.primaryAttributeSet = isPrimaryAttributeSet;
    }

    public boolean isSecondaryAttributeSet() {
        return secondaryAttributeSet;
    }

    public void setSecondaryAttributeSet(boolean isSecondaryAttributeSet) {
        this.secondaryAttributeSet = isSecondaryAttributeSet;
    }
}
