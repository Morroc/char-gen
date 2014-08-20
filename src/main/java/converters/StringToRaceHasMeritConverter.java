package converters;

import entity.RaceHasMerit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.RaceHasMeritService;

/**
 * User: artemk
 * Date: 8/19/14
 * Time: 3:45 PM
 */
public class StringToRaceHasMeritConverter implements Converter<String, RaceHasMerit> {
    @Autowired
    private RaceHasMeritService raceHasMeritService;

    public RaceHasMerit convert(String id) {
        return raceHasMeritService.getRaceHasMeritById(Integer.parseInt(id));
    }
}
