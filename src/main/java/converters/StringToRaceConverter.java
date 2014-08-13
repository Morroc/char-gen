package converters;

import entity.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.RaceService;

/**
 * User: artemk
 * Date: 8/12/14
 * Time: 12:16 PM
 */
public class StringToRaceConverter implements Converter<String, Race> {
    @Autowired
    private RaceService raceService;

    public Race convert(String id) {
        return raceService.getRaceById(Integer.parseInt(id));
    }
}
