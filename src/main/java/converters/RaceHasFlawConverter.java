package converters;

import entity.RaceHasFlaw;
import web.rest.dto.RaceHasFlawDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/29/14
 * Time: 6:29 PM
 */
public class RaceHasFlawConverter {
    FlawConverter flawConverter = new FlawConverter();

    public RaceHasFlawDTO convert(RaceHasFlaw raceHasFlaw) {
        return new RaceHasFlawDTO(raceHasFlaw.getId(), flawConverter.convert(raceHasFlaw.getFlawByRace()), raceHasFlaw.isDefaultForRace());
    }

    public List<RaceHasFlawDTO> convert(List<RaceHasFlaw> allRaceHasFlaws) {
        List<RaceHasFlawDTO> result = new ArrayList<RaceHasFlawDTO>(allRaceHasFlaws.size());
        for (RaceHasFlaw raceHasFlaw : allRaceHasFlaws) {
            result.add(convert(raceHasFlaw));
        }
        return result;
    }
}
