package web.rest.dto;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 3:23 PM
 */
public class PersonageHasBirthMeritDTO {
    private int id;

    private BirthMeritDTO birthMerit;

    private PersonageDTO personage;

    private int currentValue;

    public PersonageHasBirthMeritDTO() {
    }

    public PersonageHasBirthMeritDTO(int id, BirthMeritDTO birthMerit, PersonageDTO personage, int currentValue) {
        this.id = id;
        this.birthMerit = birthMerit;
        this.personage = personage;
        this.currentValue = currentValue;
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

    public PersonageDTO getPersonage() {
        return personage;
    }

    public void setPersonage(PersonageDTO personage) {
        this.personage = personage;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
