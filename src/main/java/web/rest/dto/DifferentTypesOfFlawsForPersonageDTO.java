package web.rest.dto;

import java.util.List;

/**
 * Created by artemk on 9/10/14.
 */
public class DifferentTypesOfFlawsForPersonageDTO {
    List<RaceHasFlawDTO> defaultForRaceFlaws;

    List<PersonageHasFlawDTO> allPersonageHasFlawsWithoutDefaultForRace;

    List<FlawDTO> allFlawsWithoutDefaultForRace;

    public DifferentTypesOfFlawsForPersonageDTO() {
    }

    public DifferentTypesOfFlawsForPersonageDTO(List<RaceHasFlawDTO> defaultForRaceFlaws,
                                                List<PersonageHasFlawDTO> allPersonageHasFlawsWithoutDefaultForRace,
                                                List<FlawDTO> allFlawsWithoutDefaultForRace) {
        this.defaultForRaceFlaws = defaultForRaceFlaws;
        this.allPersonageHasFlawsWithoutDefaultForRace = allPersonageHasFlawsWithoutDefaultForRace;
        this.allFlawsWithoutDefaultForRace = allFlawsWithoutDefaultForRace;
    }

    public List<RaceHasFlawDTO> getDefaultForRaceFlaws() {
        return defaultForRaceFlaws;
    }

    public void setDefaultForRaceFlaws(List<RaceHasFlawDTO> defaultForRaceFlaws) {
        this.defaultForRaceFlaws = defaultForRaceFlaws;
    }

    public List<PersonageHasFlawDTO> getAllPersonageHasFlawsWithoutDefaultForRace() {
        return allPersonageHasFlawsWithoutDefaultForRace;
    }

    public void setAllPersonageHasFlawsWithoutDefaultForRace(List<PersonageHasFlawDTO> allPersonageHasFlawsWithoutDefaultForRace) {
        this.allPersonageHasFlawsWithoutDefaultForRace = allPersonageHasFlawsWithoutDefaultForRace;
    }

    public List<FlawDTO> getAllFlawsWithoutDefaultForRace() {
        return allFlawsWithoutDefaultForRace;
    }

    public void setAllFlawsWithoutDefaultForRace(List<FlawDTO> allFlawsWithoutDefaultForRace) {
        this.allFlawsWithoutDefaultForRace = allFlawsWithoutDefaultForRace;
    }
}
