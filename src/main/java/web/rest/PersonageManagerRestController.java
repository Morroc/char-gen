package web.rest;

import DAO.RaceHasMeritDAO;
import constants.Constants;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.*;
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

    @Autowired
    private RaceHasAttributeService raceHasAttributeService;

    @Autowired
    private PersonageHasAttributeService personageHasAttributeService;

    @Autowired
    private RaceHasMeritService raceHasMeritService;

    @Autowired
    private PersonageHasMeritService personageHasMeritService;

    @Autowired
    private RaceHasFlawService raceHasFlawService;

    @Autowired
    private PersonageHasFlawService personageHasFlawService;

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

    @RequestMapping(value="/addPersonage", method=RequestMethod.POST, headers="Accept=application/json")
    public List<PersonageDTO> addPersonage(@ModelAttribute("personage") Personage personage) {

        //add personage
        personageService.addPersonage(personage);

        //add attributes
        int raceIdOfPersonage = personageService.getRaceByPersonageId(personage.getId());
        List<RaceHasAttribute> raceHasAttributes = raceHasAttributeService.getRaceHasAttributesByRaceId(raceIdOfPersonage);
        for (RaceHasAttribute raceHasAttribute : raceHasAttributes) {
            PersonageHasAttribute personageHasAttribute = new PersonageHasAttribute();
            personageHasAttribute.setAttributeByPersonage(raceHasAttribute.getAttributeByRace());
            personageHasAttribute.setPersonageByAttribute(personage);
            personageHasAttribute.setCurrentValue(Constants.DEFAULT_VALUE_OF_ATTRIBUTE);
            personageHasAttributeService.addLinkAttributeWithPersonage(personageHasAttribute);
        }

        //add default race merits
        List<RaceHasMerit> raceHasMerits = raceHasMeritService.getRaceHasMeritsByRaceId(raceIdOfPersonage);
        for (RaceHasMerit raceHasMerit : raceHasMerits) {
            if (raceHasMerit.isDefaultForRace()) {
                PersonageHasMerit personageHasMerit = new PersonageHasMerit();
                personageHasMerit.setMeritByPersonage(raceHasMerit.getMeritByRace());
                personageHasMerit.setPersonageByMerit(personage);
                personageHasMeritService.addLinkMeritWithPersonage(personageHasMerit);
            }
        }

        //add default race flaws
        List<RaceHasFlaw> raceHasFlaws = raceHasFlawService.getRaceHasFlawsByRaceId(raceIdOfPersonage);
        for (RaceHasFlaw raceHasFlaw : raceHasFlaws) {
            if (raceHasFlaw.isDefaultForRace()) {
                PersonageHasFlaw personageHasFlaw = new PersonageHasFlaw();
                personageHasFlaw.setFlawByPersonage(raceHasFlaw.getFlawByRace());
                personageHasFlaw.setPersonageByFlaw(personage);
                personageHasFlawService.addLinkFlawWithPersonage(personageHasFlaw);
            }
        }
        return listPersonages();
    }
}
