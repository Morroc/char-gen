package converters;

import entity.RaceHasBirthMerit;
import web.rest.dto.RaceHasBirthMeritDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artemk on 9/14/14.
 */
public class RaceHasBirthMeritConverter {
    BirthMeritConverter birthMeritConverter = new BirthMeritConverter();
    RaceConverter raceConverter = new RaceConverter();

    public RaceHasBirthMerit convert(RaceHasBirthMeritDTO raceHasBirthMeritDTO) {
        return new RaceHasBirthMerit(raceHasBirthMeritDTO.getId(), birthMeritConverter.convert(raceHasBirthMeritDTO.getBirthMerit()),
                raceConverter.convert(raceHasBirthMeritDTO.getRace()), raceHasBirthMeritDTO.getProbability());
    }

    public RaceHasBirthMeritDTO convert(RaceHasBirthMerit raceHasBirthMerit) {
        return new RaceHasBirthMeritDTO(raceHasBirthMerit.getId(), birthMeritConverter.convert(raceHasBirthMerit.getBirthMeritByRace()),
                raceConverter.convert(raceHasBirthMerit.getRaceByBirthMerit()), raceHasBirthMerit.getProbability());
    }

    public List<RaceHasBirthMeritDTO> convert(List<RaceHasBirthMerit> allRaceHasBirthMerits) {
        List<RaceHasBirthMeritDTO> result = new ArrayList<RaceHasBirthMeritDTO>(allRaceHasBirthMerits.size());
        for (RaceHasBirthMerit raceHasBirthMerit : allRaceHasBirthMerits) {
            result.add(convert(raceHasBirthMerit));
        }
        return result;
    }
}
