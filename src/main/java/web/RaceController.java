package web;

import entity.RaceHasAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import services.AttributeService;
import services.RaceHasAttributeService;
import services.RaceService;

/**
 * User: artemk
 * Date: 8/17/14
 * Time: 8:05 AM
 */
@Controller
@SessionAttributes
public class RaceController {
    @Autowired
    private RaceService raceService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private RaceHasAttributeService raceHasAttributeService;

    @RequestMapping("/race/{raceId}")
    public String pageModel(@PathVariable("raceId") Integer raceId, Model model) {

        model.addAttribute("raceHasAttribute", new RaceHasAttribute());
        model.addAttribute("raceHasAttributeByRace", raceHasAttributeService.getRaceHasAttributesByRaceId(raceId));
        model.addAttribute("race", raceService.getRaceById(raceId));
        model.addAttribute("allAttributesList", attributeService.getAllAttributes());

        return "race";
    }

    @RequestMapping(value = "/race/linkAttributeToRace", method = RequestMethod.POST)
    public String addRaceHasAttribute(@Validated @ModelAttribute("raceHasAttribute") RaceHasAttribute raceHasAttribute,
                                               BindingResult result) {

        raceHasAttributeService.addLinkAttributeWithRace(raceHasAttribute);
        int raceId = raceHasAttribute.getRaceByAttribute().getId();

        return "redirect:/race/" + raceId;
    }

    @RequestMapping(value = "/race/unlinkAttributeFromRace/{raceHasAttributeId}")
    public String unlinkAttributeFromRace(@PathVariable("raceHasAttributeId") RaceHasAttribute raceHasAttribute,
                                      @RequestParam("raceId") Integer raceId) {

        raceHasAttributeService.deleteLinkAttributeWithRace(raceHasAttribute);

        return "redirect:/race/" + raceId;
    }
}
