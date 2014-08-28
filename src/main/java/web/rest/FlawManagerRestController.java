package web.rest;

import entity.Flaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.FlawService;
import web.rest.dto.FlawDTO;

import java.util.ArrayList;
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

    @RequestMapping(value="/all", method= RequestMethod.GET, headers="Accept=application/json")
    public List<FlawDTO> listFlaws() {
        return convert(flawService.getAllFlaws());
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public List<FlawDTO> deleteFlaw(@PathVariable Integer id) {
        flawService.deleteFlawById(id);
        return listFlaws();
    }

    private List<FlawDTO> convert(List<Flaw> allFlaws) {
        List<FlawDTO> result = new ArrayList<FlawDTO>(allFlaws.size());
        for (Flaw flaw : allFlaws) {
            result.add(new FlawDTO(flaw.getId(), flaw.getName(), flaw.getCost(), flaw.getDescription(), flaw.getTurnOffPreconditions()));
        }
        return result;
    }

    @RequestMapping(value="/addFlaw", method=RequestMethod.POST, headers="Accept=application/json")
    public List<FlawDTO> addFlaw(@ModelAttribute("flaw") Flaw flaw) {
        flawService.addFlaw(flaw);
        return listFlaws();
    }
}
