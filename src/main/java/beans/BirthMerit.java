package beans;

import javax.persistence.*;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:14 PM
 */
@Entity
@Table(name = "birthmerit")
public class BirthMerit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "probability")
    private double probability;
    @Column(name = "value")
    private int value;
    @Column(name = "cost")
    private int cost;
    @Column(name = "description")
    private String description;
    @Column(name = "actionbonus")
    private String actionBonus;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

}
