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
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "merit_id")
    private Merit meritByRaceId;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race raceByMeritId;

    public RaceHasMerit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Merit getMeritByRaceId() {
        return meritByRaceId;
    }

    public void setMeritByRaceId(Merit meritByRaceId) {
        this.meritByRaceId = meritByRaceId;
    }

    public Race getRaceByMeritId() {
        return raceByMeritId;
    }

    public void setRaceByMeritId(Race raceByMeritId) {
        this.raceByMeritId = raceByMeritId;
    }
}
