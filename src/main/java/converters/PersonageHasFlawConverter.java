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

    public PersonageHasFlawDTO convert(PersonageHasFlaw personageHasFlaw) {
        return new PersonageHasFlawDTO(personageHasFlaw.getId(), flawConverter.convert(personageHasFlaw.getFlawByPersonage()));
    }

    public List<PersonageHasFlawDTO> convert(List<PersonageHasFlaw> allPersonageHasFlaws) {
        List<PersonageHasFlawDTO> result = new ArrayList<PersonageHasFlawDTO>(allPersonageHasFlaws.size());
        for (PersonageHasFlaw personageHasFlaw : allPersonageHasFlaws) {
            result.add(convert(personageHasFlaw));
        }
        return result;
    }
}
