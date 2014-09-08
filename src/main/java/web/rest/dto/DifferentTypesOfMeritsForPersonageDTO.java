package web.rest.dto;

import java.util.List;

/**
 * Created by artemk on 9/8/14.
 */
public class DifferentTypesOfMeritsForPersonageDTO {
    List<RaceHasMeritDTO> defaultForRaceMerits;

    List<RaceHasMeritDTO> withDifferentCostForRaceMerits;

    List<PersonageHasMeritDTO> onlyForPersonageMerits;

    public DifferentTypesOfMeritsForPersonageDTO() {
    }

    public DifferentTypesOfMeritsForPersonageDTO(List<RaceHasMeritDTO> defaultForRaceMerits,
                                                 List<RaceHasMeritDTO> withDifferentCostForRaceMerits,
                                                 List<PersonageHasMeritDTO> onlyForPersonageMerits) {
        this.defaultForRaceMerits = defaultForRaceMerits;
        this.withDifferentCostForRaceMerits = withDifferentCostForRaceMerits;
        this.onlyForPersonageMerits = onlyForPersonageMerits;
    }

    public List<RaceHasMeritDTO> getDefaultForRaceMerits() {
        return defaultForRaceMerits;
    }

    public void setDefaultForRaceMerits(List<RaceHasMeritDTO> defaultForRaceMerits) {
        this.defaultForRaceMerits = defaultForRaceMerits;
    }

    public List<RaceHasMeritDTO> getWithDifferentCostForRaceMerits() {
        return withDifferentCostForRaceMerits;
    }

    public void setWithDifferentCostForRaceMerits(List<RaceHasMeritDTO> withDifferentCostForRaceMerits) {
        this.withDifferentCostForRaceMerits = withDifferentCostForRaceMerits;
    }

    public List<PersonageHasMeritDTO> getOnlyForPersonageMerits() {
        return onlyForPersonageMerits;
    }

    public void setOnlyForPersonageMerits(List<PersonageHasMeritDTO> onlyForPersonageMerits) {
        this.onlyForPersonageMerits = onlyForPersonageMerits;
    }
}
