package web.rest;

import constants.Constants;
import converters.*;
import entity.*;
import enums.AttributePriority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import services.*;
import web.rest.dto.*;

import java.util.List;

/**
 * User: artemk
 * Date: 8/28/14
 * Time: 6:37 PM
 */
@RestController
@RequestMapping(value = "/rest/race", consumes = "application/json", produces = "application/json")
public class RaceRestController {
    @Autowired
    private RaceService raceService;

    @Autowired
    private RaceHasAttributeService raceHasAttributeService;

    @Autowired
    private RaceHasMeritService raceHasMeritService;

    @Autowired
    private PersonageHasMeritService personageHasMeritService;

    @Autowired
    private PersonageHasAttributeService personageHasAttributeService;

    @Autowired
    private PersonageService personageService;

    @Autowired
    private RaceHasFlawService raceHasFlawService;

    @Autowired
    private PersonageHasFlawService personageHasFlawService;

    @Autowired
    private RaceHasBirthMeritService raceHasBirthMeritService;

    @Autowired
    private PersonageHasBirthMeritService personageHasBirthMeritService;

    RaceConverter raceConverter = new RaceConverter();
    RaceHasAttributeConverter raceHasAttributeConverter = new RaceHasAttributeConverter();
    RaceHasMeritConverter raceHasMeritConverter = new RaceHasMeritConverter();
    RaceHasFlawConverter raceHasFlawConverter = new RaceHasFlawConverter();
    RaceHasBirthMeritConverter raceHasBirthMeritConverter = new RaceHasBirthMeritConverter();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RaceWithAllRelatedEntitiesDTO getRace(@PathVariable Integer id) {
        Race race = raceService.getRaceById(id);
        RaceWithAllRelatedEntitiesDTO raceWithAllRelatedEntitiesDTO = new RaceWithAllRelatedEntitiesDTO();

        raceWithAllRelatedEntitiesDTO.setRace(raceConverter.convert(race));
        raceWithAllRelatedEntitiesDTO.setRaceAttributes(raceHasAttributeConverter.convert(raceHasAttributeService.getRaceHasAttributesByRaceId(id)));
        raceWithAllRelatedEntitiesDTO.setRaceMerits(raceHasMeritConverter.convert(raceHasMeritService.getRaceHasMeritsByRaceId(id)));
        raceWithAllRelatedEntitiesDTO.setRaceFlaws(raceHasFlawConverter.convert(raceHasFlawService.getRaceHasFlawsByRaceId(id)));
        raceWithAllRelatedEntitiesDTO.setRaceBirthMerits(raceHasBirthMeritConverter.convert(raceHasBirthMeritService.getRaceHasBirthMeritsByRaceId(id)));

        return raceWithAllRelatedEntitiesDTO;
    }

    @RequestMapping(value = "/raceAttribute", method = RequestMethod.PUT)
    public RaceWithAllRelatedEntitiesDTO addRaceHasAttribute(@RequestBody RaceHasAttributeDTO raceHasAttributeDTO) {
        RaceHasAttribute raceHasAttribute = raceHasAttributeConverter.convert(raceHasAttributeDTO);

        raceHasAttributeService.addLinkAttributeWithRace(raceHasAttribute);
        int raceId = raceHasAttribute.getRaceByAttribute().getId();
        List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
        for (Personage personage : personages) {
            PersonageHasAttribute personageHasAttribute = new PersonageHasAttribute();
            personageHasAttribute.setAttributeByPersonage(raceHasAttribute.getAttributeByRace());
            personageHasAttribute.setPersonageByAttribute(personage);
            personageHasAttribute.setCurrentValue(Constants.DEFAULT_VALUE_OF_ATTRIBUTE);
            personageHasAttribute.setPriority(AttributePriority.BASIC);
            personageHasAttributeService.addLinkAttributeWithPersonage(personageHasAttribute);
        }
        return getRace(raceId);
    }

    @RequestMapping(value = "/raceAttribute/{id}", method = RequestMethod.DELETE)
    public RaceWithAllRelatedEntitiesDTO deleteRaceHasAttribute(@PathVariable Integer id) {
        RaceHasAttribute raceHasAttribute = raceHasAttributeService.getRaceHasAttributeById(id);
        Integer raceId = raceHasAttribute.getRaceByAttribute().getId();

        List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
        for (Personage personage : personages) {
            PersonageHasAttribute personageHasAttribute = personageHasAttributeService.
                    getPersonageHasAttributeByAttributeIdAndPersonageId(raceHasAttribute.getAttributeByRace().getId(), personage.getId());
            if (personageHasAttribute != null) {
                personageHasAttributeService.deleteLinkAttributeWithPersonage(personageHasAttribute);
            }
        }

        raceHasAttributeService.deleteLinkAttributeWithRaceById(id);
        return getRace(raceId);
    }

    @RequestMapping(value = "/raceMerit", method = RequestMethod.PUT)
    public RaceWithAllRelatedEntitiesDTO addRaceHasMerit(@RequestBody RaceHasMeritDTO raceHasMeritDTO) {
        RaceHasMerit raceHasMerit = raceHasMeritConverter.convert(raceHasMeritDTO);

        raceHasMeritService.addLinkMeritWithRace(raceHasMerit);
        int raceId = raceHasMerit.getRaceByMerit().getId();
        if (raceHasMerit.isDefaultForRace()) {
            List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
            for (Personage personage : personages) {
                PersonageHasMerit personageHasMerit = new PersonageHasMerit();
                personageHasMerit.setMeritByPersonage(raceHasMerit.getMeritByRace());
                personageHasMerit.setPersonageByMerit(personage);
                personageHasMeritService.addLinkMeritWithPersonage(personageHasMerit);
            }
        }
        return getRace(raceId);
    }

    @RequestMapping(value = "/raceMerit/{id}", method = RequestMethod.DELETE)
    public RaceWithAllRelatedEntitiesDTO deleteRaceHasMerit(@PathVariable("id") Integer id) {
        RaceHasMerit raceHasMerit = raceHasMeritService.getRaceHasMeritById(id);
        Integer raceId = raceHasMerit.getRaceByMerit().getId();

        if (raceHasMerit.isDefaultForRace()) {
            List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
            for (Personage personage : personages) {
                PersonageHasMerit personageHasMerit = personageHasMeritService.
                        getPersonageHasMeritByMeritIdAndPersonageId(raceHasMerit.getMeritByRace().getId(), personage.getId());
                personageHasMeritService.deleteLinkMeritWithPersonage(personageHasMerit);
            }
        }

        raceHasMeritService.deleteLinkMeritWithRace(raceHasMerit);
        return getRace(raceId);
    }

    @RequestMapping(value = "/raceFlaw", method = RequestMethod.PUT)
    public RaceWithAllRelatedEntitiesDTO addRaceHasFlaw(@RequestBody RaceHasFlawDTO raceHasFlawDTO) {
        RaceHasFlaw raceHasFlaw = raceHasFlawConverter.convert(raceHasFlawDTO);

        raceHasFlaw.setDefaultForRace(true);
        raceHasFlawService.addLinkFlawWithRace(raceHasFlaw);
        int raceId = raceHasFlaw.getRaceByFlaw().getId();
        if (raceHasFlaw.isDefaultForRace()) {
            List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
            for (Personage personage : personages) {
                PersonageHasFlaw personageHasFlaw = new PersonageHasFlaw();
                personageHasFlaw.setFlawByPersonage(raceHasFlaw.getFlawByRace());
                personageHasFlaw.setPersonageByFlaw(personage);
                personageHasFlawService.addLinkFlawWithPersonage(personageHasFlaw);
            }
        }
        return getRace(raceId);
    }

    @RequestMapping(value = "/raceFlaw/{id}", method = RequestMethod.DELETE)
    public RaceWithAllRelatedEntitiesDTO deleteRaceHasFlaw(@PathVariable("id") Integer id) {
        RaceHasFlaw raceHasFlaw = raceHasFlawService.getRaceHasFlawById(id);
        Integer raceId = raceHasFlaw.getRaceByFlaw().getId();

        if (raceHasFlaw.isDefaultForRace()) {
            List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
            for (Personage personage : personages) {
                PersonageHasFlaw personageHasFlaw = personageHasFlawService.
                        getPersonageHasFlawByFlawIdAndPersonageId(raceHasFlaw.getFlawByRace().getId(), personage.getId());
                personageHasFlawService.deleteLinkFlawWithPersonage(personageHasFlaw);
            }
        }

        raceHasFlawService.deleteLinkFlawWithRace(raceHasFlaw);
        return getRace(raceId);
    }

    @RequestMapping(value = "/raceBirthMerit", method = RequestMethod.PUT)
    public RaceWithAllRelatedEntitiesDTO addRaceHasBirthMerit(@RequestBody RaceHasBirthMeritDTO raceHasBirthMeritDTO) {
        RaceHasBirthMerit raceHasBirthMerit = raceHasBirthMeritConverter.convert(raceHasBirthMeritDTO);

        int raceId = raceHasBirthMerit.getRaceByBirthMerit().getId();
        raceHasBirthMeritService.addLinkBirthMeritWithRace(raceHasBirthMerit);

        List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
        for (Personage personage : personages) {
            if(raceHasBirthMeritService.roleBirthMerit(raceHasBirthMerit.getProbability())) {
                PersonageHasBirthMerit personageHasBirthMerit = new PersonageHasBirthMerit();
                personageHasBirthMerit.setBirthMeritByPersonage(raceHasBirthMerit.getBirthMeritByRace());
                personageHasBirthMerit.setPersonageByBirthMerit(personage);
                personageHasBirthMerit.setCurrentValue(0);
                personageHasBirthMeritService.addLinkBirthMeritWithPersonage(personageHasBirthMerit);
            }
        }

        return getRace(raceId);
    }

    @RequestMapping(value = "/raceBirthMerit/{id}", method = RequestMethod.DELETE)
    public RaceWithAllRelatedEntitiesDTO deleteRaceBirthMerit(@PathVariable("id") Integer id) {
        RaceHasBirthMerit raceHasBirthMerit = raceHasBirthMeritService.getRaceHasBirthMeritById(id);
        Integer raceId = raceHasBirthMerit.getRaceByBirthMerit().getId();

        List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
        for (Personage personage : personages) {
            PersonageHasBirthMerit personageHasBirthMerit = personageHasBirthMeritService.
                    getPersonageHasBirthMeritByBirthMeritIdAndPersonageId(raceHasBirthMerit.getBirthMeritByRace().getId(), personage.getId());
            if(personageHasBirthMerit != null) {
                personageHasBirthMeritService.deleteLinkBirthMeritWithPersonage(personageHasBirthMerit);
            }
        }

        raceHasBirthMeritService.deleteLinkBirthMeritWithRace(raceHasBirthMerit);
        return getRace(raceId);
    }
}
