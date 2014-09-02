package converters;

import entity.PersonageHasAttribute;
import web.rest.dto.PersonageHasAttributeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 9/2/14
 * Time: 3:22 PM
 */
public class PersonageHasAttributeConverter {
    AttributeConverter attributeConverter = new AttributeConverter();

    public PersonageHasAttributeDTO convert(PersonageHasAttribute personageHasAttribute) {
        return new PersonageHasAttributeDTO(personageHasAttribute.getId(),
                attributeConverter.convert(personageHasAttribute.getAttributeByPersonage()),
                personageHasAttribute.getCurrentValue());
    }

    public List<PersonageHasAttributeDTO> convert(List<PersonageHasAttribute> allPersonageHasAttributes) {
        List<PersonageHasAttributeDTO> result = new ArrayList<PersonageHasAttributeDTO>(allPersonageHasAttributes.size());
        for (PersonageHasAttribute personageHasAttribute : allPersonageHasAttributes) {
            result.add(convert(personageHasAttribute));
        }
        return result;
    }
}
