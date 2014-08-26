package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:28 PM
 */
public class RaceHasAttributeDTO {
    private int id;

    private int baseCost;

    private int from1To3NonGeneratingCost;

    private int from3To6NonGeneratingCost;

    private int from6To9NonGeneratingCost;

    private int from9To12NonGeneratingCost;

    private int maxValue;

    public RaceHasAttributeDTO(int id, int baseCost, int from1To3NonGeneratingCost,
                               int from3To6NonGeneratingCost, int from6To9NonGeneratingCost,
                               int from9To12NonGeneratingCost, int maxValue) {
        this.id = id;
        this.baseCost = baseCost;
        this.from1To3NonGeneratingCost = from1To3NonGeneratingCost;
        this.from3To6NonGeneratingCost = from3To6NonGeneratingCost;
        this.from6To9NonGeneratingCost = from6To9NonGeneratingCost;
        this.from9To12NonGeneratingCost = from9To12NonGeneratingCost;
        this.maxValue = maxValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public int getFrom1To3NonGeneratingCost() {
        return from1To3NonGeneratingCost;
    }

    public void setFrom1To3NonGeneratingCost(int from1To3NonGeneratingCost) {
        this.from1To3NonGeneratingCost = from1To3NonGeneratingCost;
    }

    public int getFrom3To6NonGeneratingCost() {
        return from3To6NonGeneratingCost;
    }

    public void setFrom3To6NonGeneratingCost(int from3To6NonGeneratingCost) {
        this.from3To6NonGeneratingCost = from3To6NonGeneratingCost;
    }

    public int getFrom6To9NonGeneratingCost() {
        return from6To9NonGeneratingCost;
    }

    public void setFrom6To9NonGeneratingCost(int from6To9NonGeneratingCost) {
        this.from6To9NonGeneratingCost = from6To9NonGeneratingCost;
    }

    public int getFrom9To12NonGeneratingCost() {
        return from9To12NonGeneratingCost;
    }

    public void setFrom9To12NonGeneratingCost(int from9To12NonGeneratingCost) {
        this.from9To12NonGeneratingCost = from9To12NonGeneratingCost;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
