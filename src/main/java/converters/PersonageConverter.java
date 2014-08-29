package converters;

import entity.Personage;
import web.rest.dto.PersonageDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/29/14
 * Time: 6:14 PM
 */
public class PersonageConverter {
    public List<PersonageDTO> convert(List<Personage> allPersonages) {

        List<PersonageDTO> result = new ArrayList<PersonageDTO>(allPersonages.size());
        for (Personage personage : allPersonages) {
            result.add(convert(personage));
        }
        return result;
    }

    public PersonageDTO convert(Personage personage) {
        RaceConverter raceConverter = new RaceConverter();
        return new PersonageDTO(personage.getId(), personage.getName(),
                personage.getAge(), raceConverter.convert(personage.getRace()));
    }
}
