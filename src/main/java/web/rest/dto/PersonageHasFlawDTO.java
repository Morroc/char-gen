package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:24 PM
 */
public class PersonageHasFlawDTO {
    private int id;

    private FlawDTO flaw;

    private PersonageDTO personage;

    public PersonageHasFlawDTO() {
    }

    public PersonageHasFlawDTO(int id, FlawDTO flaw, PersonageDTO personage) {
        this.id = id;
        this.flaw = flaw;
        this.personage = personage;
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

    public PersonageDTO getPersonage() {
        return personage;
    }

    public void setPersonage(PersonageDTO personage) {
        this.personage = personage;
    }
}
