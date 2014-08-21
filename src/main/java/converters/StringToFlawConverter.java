package converters;

import entity.Flaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.FlawService;

/**
 * User: artemk
 * Date: 8/20/14
 * Time: 5:50 PM
 */
public class StringToFlawConverter implements Converter<String, Flaw> {
    @Autowired
    private FlawService flawService;

    public Flaw convert(String id) {
        return flawService.getFlawById(Integer.parseInt(id));
    }
}
