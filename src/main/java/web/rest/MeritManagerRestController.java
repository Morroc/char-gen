package web.rest;

import converters.MeritConverter;
import entity.Merit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.MeritService;
import web.rest.dto.MeritDTO;

import java.util.List;

/**
 * User: artemk
 * Date: 8/28/14
 * Time: 12:51 PM
 */
@RestController
@RequestMapping(value = "/rest/merit", consumes = "application/json", produces = "application/json")
public class MeritManagerRestController {
    @Autowired
    private MeritService meritService;

    MeritConverter meritConverter = new MeritConverter();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<MeritDTO> listMerits() {
        return meritConverter.convert(meritService.getAllMerits());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public List<MeritDTO> deleteMerit(@PathVariable Integer id) {
        meritService.deleteMeritById(id);
        return listMerits();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public List<MeritDTO> addMerit(@RequestBody MeritDTO meritDTO) {
        Merit merit = meritConverter.convert(meritDTO);

        meritService.addMerit(merit);
        return listMerits();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public List<MeritDTO> updateMerit(@PathVariable Integer id, @RequestBody MeritDTO meritDTO) {
        meritDTO.setId(id);
        Merit merit = meritConverter.convert(meritDTO);
        meritService.updateMerit(merit);
        return listMerits();
    }
}
