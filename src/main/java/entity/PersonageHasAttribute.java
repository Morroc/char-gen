package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 2:37 PM
 */
@Entity
@Table(name = "personage_has_attribute")
public class PersonageHasAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attributeByPersonage;

    @ManyToOne
    @JoinColumn(name = "personage_id")
    private Personage personageByAttribute;

    @Column(name = "current_value")
    private int currentValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Attribute getAttributeByPersonage() {
        return attributeByPersonage;
    }

    public void setAttributeByPersonage(Attribute attributeByPersonage) {
        this.attributeByPersonage = attributeByPersonage;
    }

    public Personage getPersonageByAttribute() {
        return personageByAttribute;
    }

    public void setPersonageByAttribute(Personage personageByAttribute) {
        this.personageByAttribute = personageByAttribute;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
