package entity;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 2:59 PM
 */
@Entity
@Table(name = "race_has_flaw")
public class RaceHasFlaw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "flaw_id")
    private Flaw flawByRace;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race raceByFlaw;

    @Column(name = "default_for_race")
    private boolean defaultForRace;

    public RaceHasFlaw() {
    }

    public RaceHasFlaw(int id, Flaw flawByRace, Race raceByFlaw, boolean defaultForRace) {
        this.id = id;
        this.flawByRace = flawByRace;
        this.raceByFlaw = raceByFlaw;
        this.defaultForRace = defaultForRace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Flaw getFlawByRace() {
        return flawByRace;
    }

    public void setFlawByRace(Flaw flawByRace) {
        this.flawByRace = flawByRace;
    }

    public Race getRaceByFlaw() {
        return raceByFlaw;
    }

    public void setRaceByFlaw(Race raceByFlaw) {
        this.raceByFlaw = raceByFlaw;
    }

    public boolean isDefaultForRace() {
        return defaultForRace;
    }

    public void setDefaultForRace(boolean defaultForRace) {
        this.defaultForRace = defaultForRace;
    }
}
