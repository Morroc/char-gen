package web.rest;

import converters.BirthMeritConverter;
import entity.BirthMerit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.BirthMeritService;
import web.rest.dto.BirthMeritDTO;

import java.util.List;

/**
 * Created by artemk on 9/14/14.
 */
@RestController
@RequestMapping(value = "/rest/birthMerit", consumes = "application/json", produces = "application/json")
public class BirthMeritManagerRestController {
    @Autowired
    private BirthMeritService birthMeritService;

    BirthMeritConverter birthMeritConverter = new BirthMeritConverter();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<BirthMeritDTO> listBirthMerits() {
        return birthMeritConverter.convert(birthMeritService.getAllBirthMerits());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public List<BirthMeritDTO> deleteBirthMerit(@PathVariable Integer id) {
        birthMeritService.deleteBirthMeritById(id);
        return listBirthMerits();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public List<BirthMeritDTO> addBirthMerit(@RequestBody BirthMeritDTO birthMeritDTO) {
        BirthMerit birthMerit = birthMeritConverter.convert(birthMeritDTO);

        birthMeritService.addBirthMerit(birthMerit);
        return listBirthMerits();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public List<BirthMeritDTO> updateBirthMerit(@PathVariable Integer id, @RequestBody BirthMeritDTO birthMeritDTO) {
        birthMeritDTO.setId(id);
        BirthMerit birthMerit = birthMeritConverter.convert(birthMeritDTO);
        birthMeritService.updateBirthMerit(birthMerit);
        return listBirthMerits();
    }
}
