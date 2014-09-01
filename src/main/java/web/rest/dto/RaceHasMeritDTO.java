package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:32 PM
 */
public class RaceHasMeritDTO {
    private int id;

    private MeritDTO merit;

    private int raceCost;

    private boolean defaultForRace;

    public RaceHasMeritDTO() {
    }

    public RaceHasMeritDTO(int id, MeritDTO merit, int raceCost, boolean defaultForRace) {
        this.id = id;
        this.merit = merit;
        this.raceCost = raceCost;
        this.defaultForRace = defaultForRace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MeritDTO getMerit() {
        return merit;
    }

    public void setMerit(MeritDTO merit) {
        this.merit = merit;
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
