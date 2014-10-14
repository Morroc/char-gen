package converters;

import entity.Personage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.RaceService;
import web.rest.dto.PersonageDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/29/14
 * Time: 6:14 PM
 */
public class PersonageConverter {
    RaceConverter raceConverter = new RaceConverter();

    public List<PersonageDTO> convert(List<Personage> allPersonages) {

        List<PersonageDTO> result = new ArrayList<PersonageDTO>(allPersonages.size());
        for (Personage personage : allPersonages) {
            result.add(convert(personage));
        }
        return result;
    }

    public PersonageDTO convert(Personage personage) {
        return new PersonageDTO(personage.getId(), personage.getName(),
                personage.getAge(), raceConverter.convert(personage.getRace()), personage.isGenerated(),
                personage.getExperience());
    }

    public Personage convert(PersonageDTO personageDTO) {
        return new Personage(personageDTO.getId(), personageDTO.getName(),
                personageDTO.getAge(), raceConverter.convert(personageDTO.getRace()), personageDTO.isGenerated(),
                personageDTO.getExperience());
    }
}
