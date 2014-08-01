package main.java.enums;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:29 PM
 */
public enum SkillLevel {
    EXPERT (10),
    MASTER (15),
    MAGISTER (20),
    GRAND_MAGISTER (30);

    private int baseCost;

    public int getBaseCost() {
        return baseCost;
    }

    private SkillLevel(int baseCost) {
        this.baseCost = baseCost;
    }
}
