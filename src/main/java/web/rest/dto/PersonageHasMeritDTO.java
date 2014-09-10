package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:25 PM
 */
public class PersonageHasMeritDTO {
    private int id;

    private MeritDTO merit;

    private PersonageDTO personage;

    public PersonageHasMeritDTO() {
    }

    public PersonageHasMeritDTO(int id, MeritDTO merit, PersonageDTO personage) {
        this.id = id;
        this.merit = merit;
        this.personage = personage;
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

    public PersonageDTO getPersonage() {
        return personage;
    }

    public void setPersonage(PersonageDTO personage) {
        this.personage = personage;
    }
}
