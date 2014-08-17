package converters;

import entity.RaceHasAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.RaceHasAttributeService;

/**
 * User: artemk
 * Date: 8/17/14
 * Time: 10:09 AM
 */
public class StringToRaceHasAttributeConverter implements Converter<String, RaceHasAttribute> {
    @Autowired
    private RaceHasAttributeService raceHasAttributeService;

    public RaceHasAttribute convert(String id) {
        return raceHasAttributeService.getRaceHasAttributeById(Integer.parseInt(id));
    }
}
