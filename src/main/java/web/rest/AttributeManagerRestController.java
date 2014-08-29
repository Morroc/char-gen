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
@RequestMapping("/rest/attribute")
public class AttributeManagerRestController {
    @Autowired
    private AttributeService attributeService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<AttributeDTO> listAttributes() {
        AttributeConverter attributeConverter = new AttributeConverter();
        return attributeConverter.convert(attributeService.getAllAttributes());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public List<AttributeDTO> deleteAttribute(@PathVariable Integer id) {
        attributeService.deleteAttributeById(id);
        return listAttributes();
    }

    @RequestMapping(value = "/addAttribute", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<AttributeDTO> addAttribute(@ModelAttribute("attribute") Attribute attribute) {
        attributeService.addAttribute(attribute);
        return listAttributes();
    }
}
