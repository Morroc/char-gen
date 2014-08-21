package converters;

import entity.PersonageHasFlaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.PersonageHasFlawService;

/**
 * User: artemk
 * Date: 8/20/14
 * Time: 7:06 PM
 */
public class StringToPersonageHasFlawConverter implements Converter<String, PersonageHasFlaw>{
    @Autowired
    private PersonageHasFlawService personageHasFlawService;

    public PersonageHasFlaw convert(String id) {
        return personageHasFlawService.getPersonageHasFlawById(Integer.parseInt(id));
    }
}
