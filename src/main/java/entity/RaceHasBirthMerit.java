package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 2:57 PM
 */
@Entity
@Table(name = "race_has_birth_merit")
public class RaceHasBirthMerit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "birth_merit_id")
    private BirthMerit birthMeritByRace;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race raceByBirthMerit;

    @Column(name = "probability")
    private double probability;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BirthMerit getBirthMeritByRace() {
        return birthMeritByRace;
    }

    public void setBirthMeritByRace(BirthMerit birthMeritByRace) {
        this.birthMeritByRace = birthMeritByRace;
    }

    public Race getRaceByBirthMerit() {
        return raceByBirthMerit;
    }

    public void setRaceByBirthMerit(Race raceByBirthMerit) {
        this.raceByBirthMerit = raceByBirthMerit;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
