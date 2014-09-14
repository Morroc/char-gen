package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:30 PM
 */
public class RaceHasBirthMeritDTO {
    private int id;

    private BirthMeritDTO birthMerit;

    private RaceDTO race;

    private int probability;

    public RaceHasBirthMeritDTO() {
    }

    public RaceHasBirthMeritDTO(int id, BirthMeritDTO birthMerit, RaceDTO race, int probability) {
        this.id = id;
        this.birthMerit = birthMerit;
        this.race = race;
        this.probability = probability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BirthMeritDTO getBirthMerit() {
        return birthMerit;
    }

    public void setBirthMerit(BirthMeritDTO birthMerit) {
        this.birthMerit = birthMerit;
    }

    public RaceDTO getRace() {
        return race;
    }

    public void setRace(RaceDTO race) {
        this.race = race;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }
}
