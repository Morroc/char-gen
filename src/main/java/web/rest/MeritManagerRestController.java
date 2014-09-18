package web.rest;

import converters.MeritConverter;
import converters.MeritHasAttributePreconditionConverter;
import entity.Merit;
import entity.MeritHasAttributePrecondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.MeritHasAttributePreconditionService;
import services.MeritService;
import web.rest.dto.MeritDTO;
import web.rest.dto.MeritHasAttributePreconditionDTO;
import web.rest.dto.MeritWithPreconditionDTO;

import java.util.ArrayList;
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

    @Autowired
    private MeritHasAttributePreconditionService meritHasAttributePreconditionService;

    MeritConverter meritConverter = new MeritConverter();
    MeritHasAttributePreconditionConverter meritHasAttributePreconditionConverter = new MeritHasAttributePreconditionConverter();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<MeritWithPreconditionDTO> listMerits() {
        List<MeritWithPreconditionDTO> allMeritWithPreconditions = new ArrayList<MeritWithPreconditionDTO>();
        List<MeritDTO> allMerits = meritConverter.convert(meritService.getAllMerits());
        for (MeritDTO merit : allMerits) {
            MeritWithPreconditionDTO meritWithPrecondition = new MeritWithPreconditionDTO();
            meritWithPrecondition.setMerit(merit);
            meritWithPrecondition.setPreconditions(
                    meritHasAttributePreconditionConverter.convert(
                            meritHasAttributePreconditionService.getMeritHasAttributePreconditionsByMeritId(merit.getId())));
            allMeritWithPreconditions.add(meritWithPrecondition);
        }
        return allMeritWithPreconditions;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public List<MeritWithPreconditionDTO> deleteMerit(@PathVariable Integer id) {
        meritService.deleteMeritById(id);
        return listMerits();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public List<MeritWithPreconditionDTO> addMerit(@RequestBody MeritDTO meritDTO) {
        Merit merit = meritConverter.convert(meritDTO);

        meritService.addMerit(merit);
        return listMerits();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public List<MeritWithPreconditionDTO> updateMerit(@PathVariable Integer id, @RequestBody MeritDTO meritDTO) {
        meritDTO.setId(id);
        Merit merit = meritConverter.convert(meritDTO);
        meritService.updateMerit(merit);
        return listMerits();
    }

    @RequestMapping(value = "/precondition", method = RequestMethod.PUT)
    public List<MeritWithPreconditionDTO> addPrecondition(@RequestBody MeritHasAttributePreconditionDTO meritHasAttributePreconditionDTO) {
        MeritHasAttributePrecondition meritHasAttributePrecondition = meritHasAttributePreconditionConverter.convert(meritHasAttributePreconditionDTO);

        meritHasAttributePreconditionService.addLinkAttributeWithMerit(meritHasAttributePrecondition);
        return listMerits();
    }

    @RequestMapping(value = "/precondition/{id}", method = RequestMethod.DELETE)
    public List<MeritWithPreconditionDTO> deletePrecondition(@PathVariable Integer id) {
        meritHasAttributePreconditionService.deleteLinkAttributeWithMeritById(id);
        return listMerits();
    }
}
