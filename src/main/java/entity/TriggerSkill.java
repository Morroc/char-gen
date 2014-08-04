package entity;

import enums.SkillLevel;
import enums.SkillType;

import javax.persistence.*;
import java.lang.*;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:19 PM
 */
@Entity
@Table(name = "triggerskill")
public class TriggerSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private SkillType type;
    @Column(name = "nongeneratingcostcoefficient")
    private int nonGeneratingCostCoefficient;
    @Column(name = "level")
    private SkillLevel level;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    public TriggerSkill() {
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

    public int getNonGeneratingCostCoefficient() {
        return nonGeneratingCostCoefficient;
    }

    public void setNonGeneratingCostCoefficient(int nonGeneratingCostCoefficient) {
        this.nonGeneratingCostCoefficient = nonGeneratingCostCoefficient;
    }

    public SkillLevel getLevel() {
        return level;
    }

    public void setLevel(SkillLevel level) {
        this.level = level;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
