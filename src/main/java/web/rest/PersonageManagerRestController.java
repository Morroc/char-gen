package web.rest;

import constants.Constants;
import converters.PersonageConverter;
import entity.*;
import enums.AttributePriority;
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

    @Autowired
    private RaceHasBirthMeritService raceHasBirthMeritService;

    @Autowired
    private PersonageHasBirthMeritService personageHasBirthMeritService;

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

        //delete birth merits
        List<PersonageHasBirthMerit> personageHasBirthMerits = personageHasBirthMeritService.getPersonageHasBirthMeritsByPersonageId(id);
        for (PersonageHasBirthMerit personageHasBirthMerit : personageHasBirthMerits) {
            personageHasBirthMeritService.deleteLinkBirthMeritWithPersonage(personageHasBirthMerit);
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
            personageHasAttribute.setPriority(AttributePriority.BASIC);
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

        //role birth merits and add they
        List<RaceHasBirthMerit> raceHasBirthMerits = raceHasBirthMeritService.getRaceHasBirthMeritsByRaceId(raceIdOfPersonage);
        for (RaceHasBirthMerit raceHasBirthMerit : raceHasBirthMerits) {
            if (raceHasBirthMeritService.roleBirthMerit(raceHasBirthMerit.getProbability())) {
                PersonageHasBirthMerit personageHasBirthMerit = new PersonageHasBirthMerit();
                personageHasBirthMerit.setBirthMeritByPersonage(raceHasBirthMerit.getBirthMeritByRace());
                personageHasBirthMerit.setPersonageByBirthMerit(personage);
                personageHasBirthMerit.setCurrentValue(0);
                personageHasBirthMeritService.addLinkBirthMeritWithPersonage(personageHasBirthMerit);
            }
        }

        return listPersonages();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public List<PersonageDTO> updatePersonage(@PathVariable Integer id, @RequestBody PersonageDTO personageDTO) {
        personageDTO.setId(id);
        Personage personage = personageConverter.convert(personageDTO);
        personage.setRace(personageService.getPersonageById(id).getRace());
        personage.setExperience(personageService.getPersonageById(id).getExperience());
        personageService.updatePersonage(personage);
        return listPersonages();
    }
}
