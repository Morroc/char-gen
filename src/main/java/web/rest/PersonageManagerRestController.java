package web.rest;

import constants.Constants;
import converters.PersonageConverter;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.*;
import web.rest.dto.PersonageDTO;

import java.util.List;

/**
 * User: artemk
 * Date: 8/26/14
 * Time: 5:15 PM
 */
@RestController
@RequestMapping(value = "/rest/personage", consumes = "application/json", produces = "application/json")
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
    PersonageConverter personageConverter = new PersonageConverter();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<PersonageDTO> listPersonages() {
        return personageConverter.convert(personageService.getAllPersonages());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public List<PersonageDTO> deletePersonage(@PathVariable Integer id) {

        //delete attributes
        List<PersonageHasAttribute> personageHasAttributes = personageHasAttributeService.getPersonageHasAttributesByPersonageId(id);
        for (PersonageHasAttribute personageHasAttribute : personageHasAttributes) {
            personageHasAttributeService.deleteLinkAttributeWithPersonage(personageHasAttribute);
        }

        //delete default race merits
        List<PersonageHasMerit> personageHasMerits = personageHasMeritService.getPersonageHasMeritsByPersonageId(id);
        for (PersonageHasMerit personageHasMerit : personageHasMerits) {
            personageHasMeritService.deleteLinkMeritWithPersonage(personageHasMerit);
        }

        //delete default race flaws
        List<PersonageHasFlaw> personageHasFlaws = personageHasFlawService.getPersonageHasFlawsByPersonageId(id);
        for (PersonageHasFlaw personageHasFlaw : personageHasFlaws) {
            personageHasFlawService.deleteLinkFlawWithPersonage(personageHasFlaw);
        }

        //delete personage
        personageService.deletePersonageById(id);
        return listPersonages();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public List<PersonageDTO> addPersonage(@RequestBody PersonageDTO personageDTO) {
        Personage personage = personageConverter.convert(personageDTO);

        //add personage
        personageService.addPersonage(personage);

        //add attributes
        int raceIdOfPersonage = personageService.getRaceIdByPersonageId(personage.getId());
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

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public List<PersonageDTO> updatePersonage(@PathVariable Integer id, @RequestBody PersonageDTO personageDTO) {
        personageDTO.setId(id);
        Personage personage = personageConverter.convert(personageDTO);
        personage.setRace(personageService.getPersonageById(id).getRace());
        personageService.updatePersonage(personage);
        return listPersonages();
    }
}
