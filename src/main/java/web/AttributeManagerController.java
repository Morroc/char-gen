package web;

import entity.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.AttributeService;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 7:45 PM
 */
@Controller
public class AttributeManagerController {
    @Autowired
    private AttributeService attributeService;

    @RequestMapping("/attributesManager")
    public String pageModel(Model model) {

        model.addAttribute("attribute", new Attribute());
        model.addAttribute("attributesList", attributeService.getAllAttributes());

        return "attribute_manager";
    }

    @RequestMapping(value = "/addAttribute", method = RequestMethod.POST)
    public String addAttribute(@ModelAttribute("attribute") Attribute attribute,
                                   BindingResult result) {

        attributeService.addAttribute(attribute);

        return "redirect:/attributesManager";
    }

    @RequestMapping("/deleteAttribute/{attributeId}")
    public String deleteAttribute(@PathVariable("attributeId") Integer attributeId) {

        attributeService.deleteAttributeById(attributeId);

        return "redirect:/attributesManager";
    }
}
