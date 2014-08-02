package beans;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "maxAge")
    private int maxAge;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race")
    private List<Attribute> attributes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race")
    private List<Merit> raceMerits;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race")
    private List<Flaw> raceFlaws;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race")
    private List<BirthMerit> raceBirthMerits;

    public Race() {
    }

    public Race(int id, String name, int maxAge, List<Attribute> attributes,
                List<Merit> raceMerits, List<Flaw> raceFlaws, List<BirthMerit> raceBirthMerits) {
        this.id = id;
        this.name = name;
        this.maxAge = maxAge;
        this.attributes = attributes;
        this.raceMerits = raceMerits;
        this.raceFlaws = raceFlaws;
        this.raceBirthMerits = raceBirthMerits;
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

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Merit> getRaceMerits() {
        return raceMerits;
    }

    public void setRaceMerits(List<Merit> raceMerits) {
        this.raceMerits = raceMerits;
    }

    public List<Flaw> getRaceFlaws() {
        return raceFlaws;
    }

    public void setRaceFlaws(List<Flaw> raceFlaws) {
        this.raceFlaws = raceFlaws;
    }

    public List<BirthMerit> getRaceBirthMerits() {
        return raceBirthMerits;
    }

    public void setRaceBirthMerits(List<BirthMerit> raceBirthMerits) {
        this.raceBirthMerits = raceBirthMerits;
    }
}
