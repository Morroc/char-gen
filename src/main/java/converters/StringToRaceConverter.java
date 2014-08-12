package converters;

import DAO.RaceDAO;
import entity.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * User: artemk
 * Date: 8/12/14
 * Time: 12:16 PM
 */
@Component
public class StringToRaceConverter implements Converter<String, Race> {
    @Autowired
    private RaceDAO raceDAO;

    public Race convert(String id) {
        return raceDAO.getRaceById(Integer.parseInt(id));
    }
}
