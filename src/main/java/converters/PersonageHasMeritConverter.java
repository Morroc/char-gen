package converters;

import entity.PersonageHasMerit;
import web.rest.dto.PersonageHasMeritDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 9/2/14
 * Time: 3:43 PM
 */
public class PersonageHasMeritConverter {
    MeritConverter meritConverter = new MeritConverter();

    public PersonageHasMeritDTO convert(PersonageHasMerit personageHasMerit) {
        return new PersonageHasMeritDTO(personageHasMerit.getId(), meritConverter.convert(personageHasMerit.getMeritByPersonage()));
    }

    public List<PersonageHasMeritDTO> convert(List<PersonageHasMerit> allPersonageHasMerits) {
        List<PersonageHasMeritDTO> result = new ArrayList<PersonageHasMeritDTO>(allPersonageHasMerits.size());
        for (PersonageHasMerit personageHasMerit : allPersonageHasMerits) {
            result.add(convert(personageHasMerit));
        }
        return result;
    }
}
