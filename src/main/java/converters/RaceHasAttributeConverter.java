package converters;

import entity.RaceHasAttribute;
import web.rest.dto.RaceHasAttributeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/29/14
 * Time: 6:22 PM
 */
public class RaceHasAttributeConverter {
    AttributeConverter attributeConverter = new AttributeConverter();

    public RaceHasAttributeDTO convert(RaceHasAttribute raceHasAttribute) {
        return new RaceHasAttributeDTO(raceHasAttribute.getId(), attributeConverter.convert(raceHasAttribute.getAttributeByRace()), raceHasAttribute.getBaseCost(), raceHasAttribute.getFrom1To3NonGeneratingCost(),
                raceHasAttribute.getFrom3To6NonGeneratingCost(), raceHasAttribute.getFrom6To9NonGeneratingCost(),
                raceHasAttribute.getFrom9To12NonGeneratingCost(), raceHasAttribute.getMaxValue());
    }

    public List<RaceHasAttributeDTO> convert(List<RaceHasAttribute> allRaceHasAttributes) {
        List<RaceHasAttributeDTO> result = new ArrayList<RaceHasAttributeDTO>(allRaceHasAttributes.size());
        for (RaceHasAttribute raceHasAttribute : allRaceHasAttributes) {
            result.add(convert(raceHasAttribute));
        }
        return result;
    }
}
