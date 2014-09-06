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
    RaceConverter raceConverter = new RaceConverter();

    public RaceHasAttributeDTO convert(RaceHasAttribute raceHasAttribute) {
        return new RaceHasAttributeDTO(raceHasAttribute.getId(),
                attributeConverter.convert(raceHasAttribute.getAttributeByRace()),
                raceConverter.convert(raceHasAttribute.getRaceByAttribute()),
                raceHasAttribute.getBaseCost(), raceHasAttribute.getFrom1To3NonGeneratingCost(),
                raceHasAttribute.getFrom3To6NonGeneratingCost(), raceHasAttribute.getFrom6To9NonGeneratingCost(),
                raceHasAttribute.getFrom9To12NonGeneratingCost(), raceHasAttribute.getMaxValue());
    }

    public RaceHasAttribute convert(RaceHasAttributeDTO raceHasAttributeDTO) {
        return new RaceHasAttribute(raceHasAttributeDTO.getId(),
                attributeConverter.convert(raceHasAttributeDTO.getAttribute()),
                raceConverter.convert(raceHasAttributeDTO.getRace()),
                raceHasAttributeDTO.getBaseCost(), raceHasAttributeDTO.getFrom1To3NonGeneratingCost(),
                raceHasAttributeDTO.getFrom3To6NonGeneratingCost(), raceHasAttributeDTO.getFrom6To9NonGeneratingCost(),
                raceHasAttributeDTO.getFrom9To12NonGeneratingCost(), raceHasAttributeDTO.getMaxValue());
    }

    public List<RaceHasAttributeDTO> convert(List<RaceHasAttribute> allRaceHasAttributes) {
        List<RaceHasAttributeDTO> result = new ArrayList<RaceHasAttributeDTO>(allRaceHasAttributes.size());
        for (RaceHasAttribute raceHasAttribute : allRaceHasAttributes) {
            result.add(convert(raceHasAttribute));
        }
        return result;
    }
}
