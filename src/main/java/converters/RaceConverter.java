package converters;

import entity.Race;
import web.rest.dto.RaceDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/29/14
 * Time: 5:13 PM
 */
public class RaceConverter {
    public RaceDTO convert(Race race) {
        return new RaceDTO(race.getId(), race.getName(), race.getMaxAge());
    }

    public Race convert(RaceDTO raceDTO) {
        return new Race(raceDTO.getId(), raceDTO.getName(), raceDTO.getMaxAge());
    }

    public List<RaceDTO> convert(List<Race> allRaces) {
        List<RaceDTO> result = new ArrayList<RaceDTO>(allRaces.size());
        for (Race race : allRaces) {
            result.add(convert(race));
        }
        return result;
    }
}
