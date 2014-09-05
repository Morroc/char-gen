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
@RequestMapping(value = "/rest/flaw", consumes = "application/json", produces = "application/json")
public class FlawManagerRestController {
    @Autowired
    private FlawService flawService;

    FlawConverter flawConverter = new FlawConverter();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<FlawDTO> listFlaws() {
        return flawConverter.convert(flawService.getAllFlaws());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public List<FlawDTO> deleteFlaw(@PathVariable Integer id) {
        flawService.deleteFlawById(id);
        return listFlaws();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public List<FlawDTO> addFlaw(@RequestBody FlawDTO flawDTO) {
        Flaw flaw = flawConverter.convert(flawDTO);

        flawService.addFlaw(flaw);
        return listFlaws();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public List<FlawDTO> updateFlaw(@PathVariable Integer id, @RequestBody FlawDTO flawDTO) {
        flawDTO.setId(id);
        Flaw flaw = flawConverter.convert(flawDTO);
        flawService.updateFlaw(flaw);
        return listFlaws();
    }
}
