package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 8/7/14
 * Time: 11:24 AM
 */
@Entity
@Table(name = "personage_has_merit")
public class PersonageHasMerit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "merit_id")
    private Merit meritByPersonage;

    @ManyToOne
    @JoinColumn(name = "personage_id")
    private Personage personageByMerit;

    public PersonageHasMerit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Merit getMeritByPersonage() {
        return meritByPersonage;
    }

    public void setMeritByPersonage(Merit meritByPersonage) {
        this.meritByPersonage = meritByPersonage;
    }

    public Personage getPersonageByMerit() {
        return personageByMerit;
    }

    public void setPersonageByMerit(Personage personageByMerit) {
        this.personageByMerit = personageByMerit;
    }
}
