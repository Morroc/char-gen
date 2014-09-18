package converters;

import entity.MeritHasAttributePrecondition;
import web.rest.dto.MeritHasAttributePreconditionDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artemk on 9/17/14.
 */
public class MeritHasAttributePreconditionConverter {
    MeritConverter meritConverter = new MeritConverter();
    AttributeConverter attributeConverter = new AttributeConverter();

    public MeritHasAttributePrecondition convert(MeritHasAttributePreconditionDTO meritHasAttributePreconditionDTO) {
        return new MeritHasAttributePrecondition(meritHasAttributePreconditionDTO.getId(),
                meritConverter.convert(meritHasAttributePreconditionDTO.getMerit()),
                attributeConverter.convert(meritHasAttributePreconditionDTO.getAttribute()),
                meritHasAttributePreconditionDTO.getNeededValue());
    }

    public MeritHasAttributePreconditionDTO convert(MeritHasAttributePrecondition meritHasAttributePrecondition) {
        return new MeritHasAttributePreconditionDTO(meritHasAttributePrecondition.getId(),
                meritConverter.convert(meritHasAttributePrecondition.getMeritByAttribute()),
                attributeConverter.convert(meritHasAttributePrecondition.getAttributeByMerit()),
                meritHasAttributePrecondition.getNeededValue());
    }

    public List<MeritHasAttributePreconditionDTO> convert(List<MeritHasAttributePrecondition> allMeritHasAttributePreconditions) {
        List<MeritHasAttributePreconditionDTO> result = new ArrayList<MeritHasAttributePreconditionDTO>(allMeritHasAttributePreconditions.size());
        for (MeritHasAttributePrecondition meritHasAttributePrecondition : allMeritHasAttributePreconditions) {
            result.add(convert(meritHasAttributePrecondition));
        }
        return result;
    }
}
