package enums;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:29 PM
 */
public enum SkillLevel {
    EXPERT (10),
    MASTER (15),
    POST_MAGISTER (20);

    private int baseCost;

    public int getBaseCost() {
        return baseCost;
    }

    private SkillLevel(int baseCost) {
        this.baseCost = baseCost;
    }
}
