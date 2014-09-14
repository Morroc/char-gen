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
    private int probability;

    public RaceHasBirthMerit() {
    }

    public RaceHasBirthMerit(int id, BirthMerit birthMeritByRace, Race raceByBirthMerit, int probability) {
        this.id = id;
        this.birthMeritByRace = birthMeritByRace;
        this.raceByBirthMerit = raceByBirthMerit;
        this.probability = probability;
    }

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

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }
}
