package converters;

import entity.Personage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.PersonageService;
import services.RaceService;

/**
 * User: artemk
 * Date: 8/14/14
 * Time: 2:39 PM
 */
public class StringToPersonageConverter implements Converter<String, Personage> {
    @Autowired
    private PersonageService personageService;

    public Personage convert(String id) {
        return personageService.getPersonageById(Integer.parseInt(id));
    }
}
