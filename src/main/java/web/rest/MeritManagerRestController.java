package web.rest;

import entity.Merit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.MeritService;
import web.rest.dto.MeritDTO;

import java.util.ArrayList;
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
        return convert(meritService.getAllMerits());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public List<MeritDTO> deleteMerit(@PathVariable Integer id) {
        meritService.deleteMeritById(id);
        return listMerits();
    }

    private List<MeritDTO> convert(List<Merit> allMerits) {
        List<MeritDTO> result = new ArrayList<MeritDTO>(allMerits.size());
        for (Merit merit : allMerits) {
            result.add(new MeritDTO(merit.getId(), merit.getName(), merit.getCost(),
                    merit.getDescription(), merit.getPreconditions(), merit.getActionBonus()));
        }
        return result;
    }

    @RequestMapping(value = "/addMerit", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<MeritDTO> addMerit(@ModelAttribute("merit") Merit merit) {
        meritService.addMerit(merit);
        return listMerits();
    }
}
