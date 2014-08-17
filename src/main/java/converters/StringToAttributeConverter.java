package converters;

import entity.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import services.AttributeService;

/**
 * User: artemk
 * Date: 8/17/14
 * Time: 10:37 AM
 */
public class StringToAttributeConverter implements Converter<String, Attribute> {
    @Autowired
    private AttributeService attributeService;

    public Attribute convert(String id) {
        return attributeService.getAttributeById(Integer.parseInt(id));
    }
}
