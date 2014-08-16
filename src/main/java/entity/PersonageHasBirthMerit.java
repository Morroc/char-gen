package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 2:47 PM
 */
@Entity
@Table(name = "personage_has_birth_merit")
public class PersonageHasBirthMerit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "birth_merit_id")
    private BirthMerit birthMeritByPersonage;

    @ManyToOne
    @JoinColumn(name = "personage_id")
    private Personage personageByBirthMerit;

    @Column(name = "current_value")
    private int currentValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BirthMerit getBirthMeritByPersonage() {
        return birthMeritByPersonage;
    }

    public void setBirthMeritByPersonage(BirthMerit birthMeritByPersonage) {
        this.birthMeritByPersonage = birthMeritByPersonage;
    }

    public Personage getPersonageByBirthMerit() {
        return personageByBirthMerit;
    }

    public void setPersonageByBirthMerit(Personage personageByBirthMerit) {
        this.personageByBirthMerit = personageByBirthMerit;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
