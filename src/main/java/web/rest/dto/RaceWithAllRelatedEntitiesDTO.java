package web.rest.dto;

import java.util.List;

/**
 * User: artemk
 * Date: 8/29/14
 * Time: 4:26 PM
 */
public class RaceWithAllRelatedEntitiesDTO {
    private RaceDTO race;

    private List<RaceHasAttributeDTO> raceAttributes;

    private List<RaceHasMeritDTO> raceMerits;

    private List<RaceHasFlawDTO> raceFlaws;

    private List<RaceHasBirthMeritDTO> raceBirthMerits;

    public RaceWithAllRelatedEntitiesDTO() {
    }

    public RaceWithAllRelatedEntitiesDTO(RaceDTO race, List<RaceHasAttributeDTO> raceAttributes,
                                         List<RaceHasMeritDTO> raceMerits,
                                         List<RaceHasFlawDTO> raceFlaws,
                                         List<RaceHasBirthMeritDTO> raceBirthMerits) {
        this.race = race;
        this.raceAttributes = raceAttributes;
        this.raceMerits = raceMerits;
        this.raceFlaws = raceFlaws;
        this.raceBirthMerits = raceBirthMerits;
    }

    public RaceDTO getRace() {
        return race;
    }

    public void setRace(RaceDTO race) {
        this.race = race;
    }

    public List<RaceHasAttributeDTO> getRaceAttributes() {
        return raceAttributes;
    }

    public void setRaceAttributes(List<RaceHasAttributeDTO> raceAttributes) {
        this.raceAttributes = raceAttributes;
    }

    public List<RaceHasMeritDTO> getRaceMerits() {
        return raceMerits;
    }

    public void setRaceMerits(List<RaceHasMeritDTO> raceMerits) {
        this.raceMerits = raceMerits;
    }

    public List<RaceHasFlawDTO> getRaceFlaws() {
        return raceFlaws;
    }

    public void setRaceFlaws(List<RaceHasFlawDTO> raceFlaws) {
        this.raceFlaws = raceFlaws;
    }

    public List<RaceHasBirthMeritDTO> getRaceBirthMerits() {
        return raceBirthMerits;
    }

    public void setRaceBirthMerits(List<RaceHasBirthMeritDTO> raceBirthMerits) {
        this.raceBirthMerits = raceBirthMerits;
    }
}
