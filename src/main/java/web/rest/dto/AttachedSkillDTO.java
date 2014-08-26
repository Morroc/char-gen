package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 2:34 PM
 */
public class AttachedSkillDTO {
    private int id;

    private String name;

    private int baseCost;

    private boolean defaultSkill;

    private boolean difficult;

    private boolean theoretical;

    private int acquiringCost;

    public AttachedSkillDTO(int id, String name, int baseCost, boolean defaultSkill,
                            boolean difficult, boolean theoretical, int acquiringCost) {
        this.id = id;
        this.name = name;
        this.baseCost = baseCost;
        this.defaultSkill = defaultSkill;
        this.difficult = difficult;
        this.theoretical = theoretical;
        this.acquiringCost = acquiringCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public boolean isDefaultSkill() {
        return defaultSkill;
    }

    public void setDefaultSkill(boolean defaultSkill) {
        this.defaultSkill = defaultSkill;
    }

    public boolean isDifficult() {
        return difficult;
    }

    public void setDifficult(boolean difficult) {
        this.difficult = difficult;
    }

    public boolean isTheoretical() {
        return theoretical;
    }

    public void setTheoretical(boolean theoretical) {
        this.theoretical = theoretical;
    }

    public int getAcquiringCost() {
        return acquiringCost;
    }

    public void setAcquiringCost(int acquiringCost) {
        this.acquiringCost = acquiringCost;
    }
}
