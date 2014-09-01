package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:31 PM
 */
public class RaceHasFlawDTO {
    private int id;

    private FlawDTO flaw;

    private boolean defaultForRace;

    public RaceHasFlawDTO() {
    }

    public RaceHasFlawDTO(int id, FlawDTO flaw, boolean defaultForRace) {
        this.id = id;
        this.flaw = flaw;
        this.defaultForRace = defaultForRace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FlawDTO getFlaw() {
        return flaw;
    }

    public void setFlaw(FlawDTO flaw) {
        this.flaw = flaw;
    }

    public boolean isDefaultForRace() {
        return defaultForRace;
    }

    public void setDefaultForRace(boolean defaultForRace) {
        this.defaultForRace = defaultForRace;
    }
}
