package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 2:18 PM
 */
public class PersonageDTO {
    private int id;

    private String name;

    private int age;

    private RaceDTO race;

    public PersonageDTO() {
    }

    public PersonageDTO(int id, String name, int age, RaceDTO race) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.race = race;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public RaceDTO getRace() {
        return race;
    }

    public void setRace(RaceDTO race) {
        this.race = race;
    }
}
