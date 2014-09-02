package web.rest;

import constants.Constants;
import converters.*;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import services.*;
import web.rest.dto.*;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/28/14
 * Time: 6:37 PM
 */
@RestController
@RequestMapping("/rest/race")
public class RaceRestController {
    @Autowired
    private RaceService raceService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private RaceHasAttributeService raceHasAttributeService;

    @Autowired
    private MeritService meritService;

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
    private FlawService flawService;

    RaceConverter raceConverter = new RaceConverter();
    RaceHasAttributeConverter raceHasAttributeConverter = new RaceHasAttributeConverter();
    RaceHasMeritConverter raceHasMeritConverter = new RaceHasMeritConverter();
    RaceHasFlawConverter raceHasFlawConverter = new RaceHasFlawConverter();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public RaceWithAllRelatedEntitiesDTO getRace(@PathVariable Integer id) {
        Race race = raceService.getRaceById(id);
        RaceWithAllRelatedEntitiesDTO raceWithAllRelatedEntitiesDTO = new RaceWithAllRelatedEntitiesDTO();

        raceWithAllRelatedEntitiesDTO.setRace(raceConverter.convert(race));
        raceWithAllRelatedEntitiesDTO.setRaceAttributes(raceHasAttributeConverter.convert(raceHasAttributeService.getRaceHasAttributesByRaceId(id)));
        raceWithAllRelatedEntitiesDTO.setRaceMerits(raceHasMeritConverter.convert(raceHasMeritService.getRaceHasMeritsByRaceId(id)));
        raceWithAllRelatedEntitiesDTO.setRaceFlaws(raceHasFlawConverter.convert(raceHasFlawService.getRaceHasFlawsByRaceId(id)));

        return raceWithAllRelatedEntitiesDTO;
    }

    @RequestMapping(value = "/linkAttributeToRace", method = RequestMethod.POST)
    public RaceWithAllRelatedEntitiesDTO linkAttributeToRace(@Validated @ModelAttribute("raceHasAttribute") RaceHasAttribute raceHasAttribute) {

        raceHasAttributeService.addLinkAttributeWithRace(raceHasAttribute);
        int raceId = raceHasAttribute.getRaceByAttribute().getId();
        List<Personage> personages = personageService.getPersonagesByRaceId(raceId);
        for (Personage personage : personages) {
            PersonageHasAttribute personageHasAttribute = new PersonageHasAttribute();
            personageHasAttribute.setAttributeByPersonage(raceHasAttribute.getAttributeByRace());
            personageHasAttribute.setPersonageByAttribute(personage);
            personageHasAttribute.setCurrentValue(Constants.DEFAULT_VALUE_OF_ATTRIBUTE);
            personageHasAttributeService.addLinkAttributeWithPersonage(personageHasAttribute);
        }
        return getRace(raceId);
    }

    @RequestMapping(value = "/unlinkAttributeFromRace/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public RaceWithAllRelatedEntitiesDTO deleteRaceHasAttribute(@PathVariable Integer id) {
        Integer raceId = raceHasAttributeService.getRaceHasAttributeById(id).getRaceByAttribute().getId();
        raceHasAttributeService.deleteLinkAttributeWithRaceById(id);
        return getRace(raceId);
    }

    @RequestMapping(value = "/linkMeritToRace", method = RequestMethod.POST)
    public RaceWithAllRelatedEntitiesDTO linkMeritToRace(@Validated @ModelAttribute("raceHasMerit") RaceHasMerit raceHasMerit) {

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

    @RequestMapping(value = "/unlinkMeritFromRace/{id}")
    public RaceWithAllRelatedEntitiesDTO unlinkMeritFromRace(@PathVariable("id") Integer id) {
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

    @RequestMapping(value = "/linkFlawToRace", method = RequestMethod.POST)
    public RaceWithAllRelatedEntitiesDTO linkFlawToRace(@Validated @ModelAttribute("raceHasFlaw") RaceHasFlaw raceHasFlaw) {

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

    @RequestMapping(value = "/unlinkFlawFromRace/{id}")
    public RaceWithAllRelatedEntitiesDTO unlinkFlawFromRace(@PathVariable("id") Integer id) {
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
}
