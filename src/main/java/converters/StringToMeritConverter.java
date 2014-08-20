package converters;

import entity.Merit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.MeritService;

/**
 * User: artemk
 * Date: 8/19/14
 * Time: 3:43 PM
 */
public class StringToMeritConverter implements Converter<String, Merit> {
    @Autowired
    private MeritService meritService;

    public Merit convert(String id) {
        return meritService.getMeritById(Integer.parseInt(id));
    }
}
