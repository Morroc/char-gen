package converters;

import entity.PersonageHasFlaw;
import web.rest.dto.PersonageHasFlawDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 9/2/14
 * Time: 3:34 PM
 */
public class PersonageHasFlawConverter {
    FlawConverter flawConverter = new FlawConverter();
    PersonageConverter personageConverter = new PersonageConverter();

    public PersonageHasFlawDTO convert(PersonageHasFlaw personageHasFlaw) {
        return new PersonageHasFlawDTO(personageHasFlaw.getId(), flawConverter.convert(personageHasFlaw.getFlawByPersonage()),
                personageConverter.convert(personageHasFlaw.getPersonageByFlaw()));
    }

    public PersonageHasFlaw convert(PersonageHasFlawDTO personageHasFlawDTO) {
        return new PersonageHasFlaw(personageHasFlawDTO.getId(), flawConverter.convert(personageHasFlawDTO.getFlaw()),
                personageConverter.convert(personageHasFlawDTO.getPersonage()));
    }

    public List<PersonageHasFlawDTO> convert(List<PersonageHasFlaw> allPersonageHasFlaws) {
        List<PersonageHasFlawDTO> result = new ArrayList<PersonageHasFlawDTO>(allPersonageHasFlaws.size());
        for (PersonageHasFlaw personageHasFlaw : allPersonageHasFlaws) {
            result.add(convert(personageHasFlaw));
        }
        return result;
    }
}
