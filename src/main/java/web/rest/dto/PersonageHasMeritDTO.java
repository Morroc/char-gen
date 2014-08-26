package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:25 PM
 */
public class PersonageHasMeritDTO {
    private int id;

    public PersonageHasMeritDTO() {
    }

    public PersonageHasMeritDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
