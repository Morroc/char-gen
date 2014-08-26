package web.rest.dto;

import enums.SkillType;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:33 PM
 */
public class TriggerSkillDTO {
    private int id;

    private String name;

    private SkillType type;

    private int baseCost;

    private int expertCost;

    private int masterCost;

    private int postMasterCost;

    public TriggerSkillDTO(int id, String name, SkillType type, int baseCost, int expertCost, int masterCost, int postMasterCost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.baseCost = baseCost;
        this.expertCost = expertCost;
        this.masterCost = masterCost;
        this.postMasterCost = postMasterCost;
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

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public int getExpertCost() {
        return expertCost;
    }

    public void setExpertCost(int expertCost) {
        this.expertCost = expertCost;
    }

    public int getMasterCost() {
        return masterCost;
    }

    public void setMasterCost(int masterCost) {
        this.masterCost = masterCost;
    }

    public int getPostMasterCost() {
        return postMasterCost;
    }

    public void setPostMasterCost(int postMasterCost) {
        this.postMasterCost = postMasterCost;
    }
}
