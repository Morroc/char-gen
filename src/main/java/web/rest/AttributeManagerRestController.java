package web.rest;

import converters.AttributeConverter;
import entity.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.AttributeService;
import web.rest.dto.AttributeDTO;

import java.util.List;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 6:38 PM
 */
@RestController
@RequestMapping(value = "/rest/attribute", consumes = "application/json", produces = "application/json")
public class AttributeManagerRestController {
    @Autowired
    private AttributeService attributeService;

    AttributeConverter attributeConverter = new AttributeConverter();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<AttributeDTO> listAttributes() {
        return attributeConverter.convert(attributeService.getAllAttributes());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public List<AttributeDTO> deleteAttribute(@PathVariable Integer id) {
        attributeService.deleteAttributeById(id);
        return listAttributes();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public List<AttributeDTO> addAttribute(@RequestBody AttributeDTO attributeDTO) {
        Attribute attribute = attributeConverter.convert(attributeDTO);

        attributeService.addAttribute(attribute);
        return listAttributes();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public List<AttributeDTO> updateAttribute(@PathVariable Integer id, @RequestBody AttributeDTO attributeDTO) {
        attributeDTO.setId(id);
        Attribute attribute = attributeConverter.convert(attributeDTO);
        attributeService.updateAttribute(attribute);
        return listAttributes();
    }
}
