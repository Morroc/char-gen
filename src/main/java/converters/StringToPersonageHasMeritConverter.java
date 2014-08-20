package converters;

import entity.PersonageHasMerit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.PersonageHasMeritService;

/**
 * User: artemk
 * Date: 8/20/14
 * Time: 11:19 AM
 */
public class StringToPersonageHasMeritConverter implements Converter<String, PersonageHasMerit> {
    @Autowired
    private PersonageHasMeritService personageHasMeritService;

    public PersonageHasMerit convert(String id) {
        return personageHasMeritService.getPersonageHasMeritById(Integer.parseInt(id));
    }
}
