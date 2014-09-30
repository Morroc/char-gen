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
    PersonageConverter personageConverter = new PersonageConverter();

    public PersonageHasAttribute convert(PersonageHasAttributeDTO personageHasAttributeDTO) {
        return new PersonageHasAttribute(personageHasAttributeDTO.getId(),
                attributeConverter.convert(personageHasAttributeDTO.getAttribute()),
                personageConverter.convert(personageHasAttributeDTO.getPersonage()),
                personageHasAttributeDTO.getCurrentValue(), personageHasAttributeDTO.getPriority());
    }

    public PersonageHasAttributeDTO convert(PersonageHasAttribute personageHasAttribute) {
        return new PersonageHasAttributeDTO(personageHasAttribute.getId(),
                attributeConverter.convert(personageHasAttribute.getAttributeByPersonage()),
                personageConverter.convert(personageHasAttribute.getPersonageByAttribute()),
                personageHasAttribute.getCurrentValue(), personageHasAttribute.getPriority());
    }

    public List<PersonageHasAttributeDTO> convert(List<PersonageHasAttribute> allPersonageHasAttributes) {
        List<PersonageHasAttributeDTO> result = new ArrayList<PersonageHasAttributeDTO>(allPersonageHasAttributes.size());
        for (PersonageHasAttribute personageHasAttribute : allPersonageHasAttributes) {
            result.add(convert(personageHasAttribute));
        }
        return result;
    }
}
