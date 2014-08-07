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
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "merit_id")
    private Merit meritByPersonageId;

    @ManyToOne
    @JoinColumn(name = "personage_id")
    private Personage personageByMeritId;

    public PersonageHasMerit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Merit getMeritByPersonageId() {
        return meritByPersonageId;
    }

    public void setMeritByPersonageId(Merit meritByPersonageId) {
        this.meritByPersonageId = meritByPersonageId;
    }

    public Personage getPersonageByMeritId() {
        return personageByMeritId;
    }

    public void setPersonageByMeritId(Personage personageByMeritId) {
        this.personageByMeritId = personageByMeritId;
    }
}
