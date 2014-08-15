package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 8/7/14
 * Time: 11:09 AM
 */
@Entity
@Table(name = "race_has_merit")
public class RaceHasMerit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "merit_id")
    private Merit meritByRace;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race raceByMerit;

    @Column(name = "race_cost")
    private int raceCost;

    @Column(name = "default_for_race")
    private boolean defaultForRace;

    public RaceHasMerit() {
    }

    public RaceHasMerit(Merit meritByRace, Race raceByMerit) {
        this.meritByRace = meritByRace;
        this.raceByMerit = raceByMerit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Merit getMeritByRace() {
        return meritByRace;
    }

    public void setMeritByRace(Merit meritByRace) {
        this.meritByRace = meritByRace;
    }

    public Race getRaceByMerit() {
        return raceByMerit;
    }

    public void setRaceByMerit(Race raceByMerit) {
        this.raceByMerit = raceByMerit;
    }

    public int getRaceCost() {
        return raceCost;
    }

    public void setRaceCost(int raceCost) {
        this.raceCost = raceCost;
    }

    public boolean isDefaultForRace() {
        return defaultForRace;
    }

    public void setDefaultForRace(boolean defaultForRace) {
        this.defaultForRace = defaultForRace;
    }
}
