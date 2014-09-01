package converters;

import entity.RaceHasMerit;
import web.rest.dto.RaceHasMeritDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/29/14
 * Time: 6:40 PM
 */
public class RaceHasMeritConverter {
    MeritConverter meritConverter = new MeritConverter();

    public RaceHasMeritDTO convert(RaceHasMerit raceHasMerit) {
        return new RaceHasMeritDTO(raceHasMerit.getId(), meritConverter.convert(raceHasMerit.getMeritByRace()),
                raceHasMerit.getRaceCost(), raceHasMerit.isDefaultForRace());
    }

    public List<RaceHasMeritDTO> convert(List<RaceHasMerit> allRaceHasMerits) {
        List<RaceHasMeritDTO> result = new ArrayList<RaceHasMeritDTO>(allRaceHasMerits.size());
        for (RaceHasMerit raceHasMerit : allRaceHasMerits) {
            result.add(convert(raceHasMerit));
        }
        return result;
    }
}
