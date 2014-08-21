package converters;

import entity.RaceHasFlaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.RaceHasFlawService;

/**
 * User: artemk
 * Date: 8/20/14
 * Time: 5:47 PM
 */
public class StringToRaceHasFlawConverter implements Converter<String, RaceHasFlaw>{
    @Autowired
    private RaceHasFlawService raceHasFlawService;

    public RaceHasFlaw convert(String id) {
        return raceHasFlawService.getRaceHasFlawById(Integer.parseInt(id));
    }
}
