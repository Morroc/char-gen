package entity;

import javax.persistence.*;

/**
 * Created by artemk on 9/17/14.
 */
@Entity
@Table(name = "merit_has_attribute_preconditions")
public class MeritHasAttributePrecondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "merit_id")
    private Merit meritByAttribute;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attributeByMerit;

    @Column(name = "needed_value")
    private int neededValue;

    public MeritHasAttributePrecondition() {
    }

    public MeritHasAttributePrecondition(int id, Merit meritByAttribute, Attribute attributeByMerit, int neededValue) {
        this.id = id;
        this.meritByAttribute = meritByAttribute;
        this.attributeByMerit = attributeByMerit;
        this.neededValue = neededValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Merit getMeritByAttribute() {
        return meritByAttribute;
    }

    public void setMeritByAttribute(Merit meritByAttribute) {
        this.meritByAttribute = meritByAttribute;
    }

    public Attribute getAttributeByMerit() {
        return attributeByMerit;
    }

    public void setAttributeByMerit(Attribute attributeByMerit) {
        this.attributeByMerit = attributeByMerit;
    }

    public int getNeededValue() {
        return neededValue;
    }

    public void setNeededValue(int neededValue) {
        this.neededValue = neededValue;
    }
}
