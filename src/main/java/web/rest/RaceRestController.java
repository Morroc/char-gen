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

    @RequestMapping(value = "/unlink/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public RaceWithAllRelatedEntitiesDTO deleteRaceHasAttribute(@PathVariable Integer id) {
        Integer raceId = raceHasAttributeService.getRaceHasAttributeById(id).getRaceByAttribute().getId();
        raceHasAttributeService.deleteLinkAttributeWithRaceById(id);
        return getRace(raceId);
    }
}
