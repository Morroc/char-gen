package web.rest;

import converters.FlawConverter;
import entity.Flaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.FlawService;
import web.rest.dto.FlawDTO;

import java.util.List;

/**
 * User: artemk
 * Date: 8/28/14
 * Time: 12:02 PM
 */
@RestController
@RequestMapping("/rest/flaw")
public class FlawManagerRestController {
    @Autowired
    private FlawService flawService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<FlawDTO> listFlaws() {
        FlawConverter flawConverter = new FlawConverter();
        return flawConverter.convert(flawService.getAllFlaws());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public List<FlawDTO> deleteFlaw(@PathVariable Integer id) {
        flawService.deleteFlawById(id);
        return listFlaws();
    }

    @RequestMapping(value = "/addFlaw", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<FlawDTO> addFlaw(@ModelAttribute("flaw") Flaw flaw) {
        flawService.addFlaw(flaw);
        return listFlaws();
    }
}
