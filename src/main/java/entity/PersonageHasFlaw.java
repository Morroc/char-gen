package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 2:51 PM
 */
@Entity
@Table(name = "personage_has_flaw")
public class PersonageHasFlaw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "flaw_id")
    private Flaw flawByPersonage;

    @ManyToOne
    @JoinColumn(name = "personage_id")
    private Personage personageByFlaw;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Flaw getFlawByPersonage() {
        return flawByPersonage;
    }

    public void setFlawByPersonage(Flaw flawByPersonage) {
        this.flawByPersonage = flawByPersonage;
    }

    public Personage getPersonageByFlaw() {
        return personageByFlaw;
    }

    public void setPersonageByFlaw(Personage personageByFlaw) {
        this.personageByFlaw = personageByFlaw;
    }
}
