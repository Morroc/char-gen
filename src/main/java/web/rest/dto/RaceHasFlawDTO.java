package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:31 PM
 */
public class RaceHasFlawDTO {
    private int id;

    private boolean defaultForRace;

    public RaceHasFlawDTO() {
    }

    public RaceHasFlawDTO(int id, boolean defaultForRace) {
        this.id = id;
        this.defaultForRace = defaultForRace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDefaultForRace() {
        return defaultForRace;
    }

    public void setDefaultForRace(boolean defaultForRace) {
        this.defaultForRace = defaultForRace;
    }
}
