package entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 6:54 PM
 */
@Entity
@Table(name = "race")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "max_age")
    private int maxAge;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race")
    private Set<Personage> personages;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raceByMerit")
    private Set<RaceHasMerit> racesByMerit;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raceByFlaw")
    private Set<RaceHasFlaw> raceHasFlaw;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raceByAttribute")
    private Set<RaceHasAttribute> raceHasAttributes;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raceByBirthMerit")
    private Set<RaceHasBirthMerit> raceHasBirthMerits;


    public Race() {
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

    public Set<Personage> getPersonages() {
        return personages;
    }

    public void setPersonages(Set<Personage> personages) {
        this.personages = personages;
    }

    public Set<RaceHasMerit> getRacesByMerit() {
        return racesByMerit;
    }

    public void setRacesByMerit(Set<RaceHasMerit> racesByMerit) {
        this.racesByMerit = racesByMerit;
    }

    public Set<RaceHasFlaw> getRaceHasFlaw() {
        return raceHasFlaw;
    }

    public void setRaceHasFlaw(Set<RaceHasFlaw> raceHasFlaw) {
        this.raceHasFlaw = raceHasFlaw;
    }

    public Set<RaceHasAttribute> getRaceHasAttributes() {
        return raceHasAttributes;
    }

    public void setRaceHasAttributes(Set<RaceHasAttribute> raceHasAttributes) {
        this.raceHasAttributes = raceHasAttributes;
    }

    public Set<RaceHasBirthMerit> getRaceHasBirthMerits() {
        return raceHasBirthMerits;
    }

    public void setRaceHasBirthMerits(Set<RaceHasBirthMerit> raceHasBirthMerits) {
        this.raceHasBirthMerits = raceHasBirthMerits;
    }
}
