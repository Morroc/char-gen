package entity;

import enums.AttributePriority;

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

    @Column(name = "priority", columnDefinition = "enum('BASIC','EXPERT','MASTER','POST_MASTER')")
    private AttributePriority priority;

    public PersonageHasAttribute() {
    }

    public PersonageHasAttribute(int id, Attribute attributeByPersonage, Personage personageByAttribute,
                                 int currentValue, AttributePriority priority) {
        this.id = id;
        this.attributeByPersonage = attributeByPersonage;
        this.personageByAttribute = personageByAttribute;
        this.currentValue = currentValue;
        this.priority = priority;
    }

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

    public AttributePriority getPriority() {
        return priority;
    }

    public void setPriority(AttributePriority priority) {
        this.priority = priority;
    }
}
