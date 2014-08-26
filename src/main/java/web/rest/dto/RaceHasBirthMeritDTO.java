package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:30 PM
 */
public class RaceHasBirthMeritDTO {
    private int id;

    private double probability;

    public RaceHasBirthMeritDTO() {
    }

    public RaceHasBirthMeritDTO(int id, double probability) {
        this.id = id;
        this.probability = probability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
