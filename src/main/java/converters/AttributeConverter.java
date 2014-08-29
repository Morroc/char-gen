package converters;

import entity.Attribute;
import web.rest.dto.AttributeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/29/14
 * Time: 5:11 PM
 */
public class AttributeConverter {
    public List<AttributeDTO> convert(List<Attribute> allAttributes) {
        List<AttributeDTO> result = new ArrayList<AttributeDTO>(allAttributes.size());
        for (Attribute attribute : allAttributes) {
            result.add(convert(attribute));
        }
        return result;
    }

    public AttributeDTO convert(Attribute attribute) {
        return new AttributeDTO(attribute.getId(), attribute.getName(), attribute.getActionLevelBonus());
    }
}
