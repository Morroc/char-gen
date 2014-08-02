import javax.persistence.*;
import java.util.List;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 9:09 PM
 */
@Entity
@Table(name = "character")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private String age;
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "character")
    private List<Attribute> attributes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "character")
    private List<TriggerSkill> triggerSkills;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "character")
    private List<AttachedSkill> attachedSkills;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "character")
    private List<Merit> merits;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "character")
    private List<Flaw> flaws;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "character")
    private List<BirthMerit> birthMerits;

    public Character() {
    }

    public Character(int id, String name, String age, Race race,
                     List<Attribute> attributes, List<TriggerSkill> triggerSkills,
                     List<AttachedSkill> attachedSkills, List<Merit> merits,
                     List<Flaw> flaws, List<BirthMerit> birthMerits) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.race = race;
//        this.attributes = attributes;
        this.triggerSkills = triggerSkills;
        this.attachedSkills = attachedSkills;
        this.merits = merits;
        this.flaws = flaws;
        this.birthMerits = birthMerits;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<TriggerSkill> getTriggerSkills() {
        return triggerSkills;
    }

    public void setTriggerSkills(List<TriggerSkill> triggerSkills) {
        this.triggerSkills = triggerSkills;
    }

    public List<AttachedSkill> getAttachedSkills() {
        return attachedSkills;
    }

    public void setAttachedSkills(List<AttachedSkill> attachedSkills) {
        this.attachedSkills = attachedSkills;
    }

    public List<Merit> getMerits() {
        return merits;
    }

    public void setMerits(List<Merit> merits) {
        this.merits = merits;
    }

    public List<Flaw> getFlaws() {
        return flaws;
    }

    public void setFlaws(List<Flaw> flaws) {
        this.flaws = flaws;
    }

    public List<BirthMerit> getBirthMerits() {
        return birthMerits;
    }

    public void setBirthMerits(List<BirthMerit> birthMerits) {
        this.birthMerits = birthMerits;
    }
}
