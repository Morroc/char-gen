package entity;

import javax.persistence.*;
import java.util.List;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 6:54 PM
 */
@Entity
@Table(name = "race")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "maxage")
    private int maxAge;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raceByMerit")
    private List<RaceHasMerit> racesByMerit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race")
    private List<Personage> personages;

    public Race() {
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

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public List<RaceHasMerit> getRacesByMerit() {
        return racesByMerit;
    }

    public void setRacesByMerit(List<RaceHasMerit> racesByMerit) {
        this.racesByMerit = racesByMerit;
    }

    public List<Personage> getPersonages() {
        return personages;
    }

    public void setPersonages(List<Personage> personages) {
        this.personages = personages;
    }
}
