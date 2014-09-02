package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:25 PM
 */
public class PersonageHasMeritDTO {
    private int id;

    private MeritDTO merit;

    public PersonageHasMeritDTO() {
    }

    public PersonageHasMeritDTO(int id, MeritDTO merit) {
        this.id = id;
        this.merit = merit;
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
}
