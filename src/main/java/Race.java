package main.java;

import java.util.List;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 6:54 PM
 */
public class Race {
    private String name;
    private int maxAge;
    private List<Attribute> attributes;
    private List<Merit> defaultRaceMerits;
    private List<Flaw> defaultRaceFlaws;

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

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Merit> getDefaultRaceMerits() {
        return defaultRaceMerits;
    }

    public void setDefaultRaceMerits(List<Merit> defaultRaceMerits) {
        this.defaultRaceMerits = defaultRaceMerits;
    }

    public List<Flaw> getDefaultRaceFlaws() {
        return defaultRaceFlaws;
    }

    public void setDefaultRaceFlaws(List<Flaw> defaultRaceFlaws) {
        this.defaultRaceFlaws = defaultRaceFlaws;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Race)) return false;

        Race race = (Race) o;

        if (maxAge != race.maxAge) return false;
        if (attributes != null ? !attributes.equals(race.attributes) : race.attributes != null) return false;
        if (defaultRaceFlaws != null ? !defaultRaceFlaws.equals(race.defaultRaceFlaws) : race.defaultRaceFlaws != null)
            return false;
        if (defaultRaceMerits != null ? !defaultRaceMerits.equals(race.defaultRaceMerits) : race.defaultRaceMerits != null)
            return false;
        if (name != null ? !name.equals(race.name) : race.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + maxAge;
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (defaultRaceMerits != null ? defaultRaceMerits.hashCode() : 0);
        result = 31 * result + (defaultRaceFlaws != null ? defaultRaceFlaws.hashCode() : 0);
        return result;
    }
}
