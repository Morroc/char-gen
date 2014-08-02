package enums;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:22 PM
 */
public enum SkillType {
    SIMPLE (3),
    BASIC (5),
    DIFFICULT (5);

    private int baseCost;

    public int getBaseCost() {
        return baseCost;
    }

    private SkillType(int baseCost) {
        this.baseCost = baseCost;
    }
}
