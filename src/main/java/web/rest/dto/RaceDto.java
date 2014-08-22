package web.rest.dto;

/**
 * @author abykovsky
 * @since 8/22/14
 */
public class RaceDto {

    private int id;

    private String name;

    private int maxAge;

    public RaceDto() {
    }

    public RaceDto(int id, String name, int maxAge) {
        this.id = id;
        this.name = name;
        this.maxAge = maxAge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}
