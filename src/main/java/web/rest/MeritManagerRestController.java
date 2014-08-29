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
@RequestMapping("/rest/merit")
public class MeritManagerRestController {
    @Autowired
    private MeritService meritService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<MeritDTO> listMerits() {
        MeritConverter meritConverter = new MeritConverter();
        return meritConverter.convert(meritService.getAllMerits());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public List<MeritDTO> deleteMerit(@PathVariable Integer id) {
        meritService.deleteMeritById(id);
        return listMerits();
    }

    @RequestMapping(value = "/addMerit", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<MeritDTO> addMerit(@ModelAttribute("merit") Merit merit) {
        meritService.addMerit(merit);
        return listMerits();
    }
}
