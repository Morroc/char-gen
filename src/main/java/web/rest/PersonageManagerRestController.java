package web.rest;

import entity.Personage;
import entity.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.PersonageService;
import web.rest.dto.PersonageDTO;
import web.rest.dto.RaceDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 5:15 PM
 */
@RestController
@RequestMapping("/rest/personage")
public class PersonageManagerRestController {
    @Autowired
    private PersonageService personageService;

    @RequestMapping(value="/all", method= RequestMethod.GET, headers="Accept=application/json")
    public List<PersonageDTO> listPersonages() {
        return convert(personageService.getAllPersonages());
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    public List<PersonageDTO> deletePersonage(@PathVariable Integer id) {
        personageService.deletePersonageById(id);
        return listPersonages();
    }

    private List<PersonageDTO> convert(List<Personage> allPersonages) {
        List<PersonageDTO> result = new ArrayList<PersonageDTO>(allPersonages.size());
        for (Personage personage : allPersonages) {
            result.add(new PersonageDTO(personage.getId(), personage.getName(), personage.getAge(), convert(personage.getRace())));
        }
        return result;
    }

    private RaceDTO convert(Race race) {
        return new RaceDTO(race.getId(), race.getName(), race.getMaxAge());
    }
}
