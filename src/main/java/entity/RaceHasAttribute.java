package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 2:42 PM
 */
@Entity
@Table(name = "race_has_attribute")
public class RaceHasAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attributeByRace;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race raceByAttribute;

    @Column(name = "base_cost")
    private int baseCost;

    @Column(name = "from_1_to_3_non_generating_cost")
    private int from1To3NonGeneratingCost;

    @Column(name = "from_3_to_6_non_generating_cost")
    private int from3To6NonGeneratingCost;

    @Column(name = "from_6_to_9_non_generating_cost")
    private int from6To9NonGeneratingCost;

    @Column(name = "from_9_to_12_non_generating_cost")
    private int from9To12NonGeneratingCost;

    @Column(name = "max_value")
    private int maxValue;

    public RaceHasAttribute() {
    }

    public RaceHasAttribute(int id, Attribute attributeByRace, Race raceByAttribute, int baseCost,
                            int from1To3NonGeneratingCost, int from3To6NonGeneratingCost,
                            int from6To9NonGeneratingCost, int from9To12NonGeneratingCost, int maxValue) {
        this.id = id;
        this.attributeByRace = attributeByRace;
        this.raceByAttribute = raceByAttribute;
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

    public Attribute getAttributeByRace() {
        return attributeByRace;
    }

    public void setAttributeByRace(Attribute attributeByRace) {
        this.attributeByRace = attributeByRace;
    }

    public Race getRaceByAttribute() {
        return raceByAttribute;
    }

    public void setRaceByAttribute(Race raceByAttribute) {
        this.raceByAttribute = raceByAttribute;
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
