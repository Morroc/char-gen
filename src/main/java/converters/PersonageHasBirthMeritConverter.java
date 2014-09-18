package converters;

import entity.PersonageHasBirthMerit;
import web.rest.dto.PersonageHasBirthMeritDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 9/2/14
 * Time: 3:50 PM
 */
public class PersonageHasBirthMeritConverter {
    BirthMeritConverter birthMeritConverter = new BirthMeritConverter();
    PersonageConverter personageConverter = new PersonageConverter();

    public PersonageHasBirthMeritDTO convert(PersonageHasBirthMerit personageHasBirthMerit) {
        return new PersonageHasBirthMeritDTO(personageHasBirthMerit.getId(),
                birthMeritConverter.convert(personageHasBirthMerit.getBirthMeritByPersonage()),
                personageConverter.convert(personageHasBirthMerit.getPersonageByBirthMerit()),
                personageHasBirthMerit.getCurrentValue());
    }

    public PersonageHasBirthMerit convert(PersonageHasBirthMeritDTO personageHasBirthMeritDTO) {
        return new PersonageHasBirthMerit(personageHasBirthMeritDTO.getId(),
                birthMeritConverter.convert(personageHasBirthMeritDTO.getBirthMerit()),
                personageConverter.convert(personageHasBirthMeritDTO.getPersonage()),
                personageHasBirthMeritDTO.getCurrentValue());
    }

    public List<PersonageHasBirthMeritDTO> convert(List<PersonageHasBirthMerit> allPersonageHasBirthMerits) {
        List<PersonageHasBirthMeritDTO> result = new ArrayList<PersonageHasBirthMeritDTO>(allPersonageHasBirthMerits.size());
        for (PersonageHasBirthMerit personageHasBirthMerit : allPersonageHasBirthMerits) {
            result.add(convert(personageHasBirthMerit));
        }
        return result;
    }
}
