package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 2/19/14
 * Time: 1:01 PM
 */
@Entity
@Table(name = "attribute")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private int name;
    @Column(name = "action_level_bonus")
    private String actionLevelBonus;

    public Attribute() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getActionLevelBonus() {
        return actionLevelBonus;
    }

    public void setActionLevelBonus(String actionLevelBonus) {
        this.actionLevelBonus = actionLevelBonus;
    }
}
