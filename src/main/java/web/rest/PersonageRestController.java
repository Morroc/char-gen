package web.rest;

import converters.*;
import entity.*;
import enums.SkillLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.*;
import web.rest.dto.*;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 9/2/14
 * Time: 3:18 PM
 */
@RestController
@RequestMapping("/rest/personage")
public class PersonageRestController {
    @Autowired
    private PersonageService personageService;

    @Autowired
    private AttachedSkillService attachedSkillService;

    @Autowired
    private TriggerSkillService triggerSkillService;

    @Autowired
    private PersonageHasAttachedSkillService personageHasAttachedSkillService;

    @Autowired
    private PersonageHasTriggerSkillService personageHasTriggerSkillService;

    @Autowired
    private PersonageHasAttributeService personageHasAttributeService;

    @Autowired
    private PersonageHasMeritService personageHasMeritService;

    @Autowired
    private RaceHasMeritService raceHasMeritService;

    @Autowired
    private MeritService meritService;

    @Autowired
    private FlawService flawService;

    @Autowired
    private PersonageHasFlawService personageHasFlawService;

    @Autowired
    private RaceHasFlawService raceHasFlawService;

    @Autowired
    private PersonageHasBirthMeritService personageHasBirthMeritService;

    @Autowired
    private RaceHasAttributeService raceHasAttributeService;

    PersonageConverter personageConverter = new PersonageConverter();
    PersonageHasAttributeConverter personageHasAttributeConverter = new PersonageHasAttributeConverter();
    PersonageHasBirthMeritConverter personageHasBirthMeritConverter = new PersonageHasBirthMeritConverter();
    PersonageHasMeritConverter personageHasMeritConverter = new PersonageHasMeritConverter();
    PersonageHasFlawConverter personageHasFlawConverter = new PersonageHasFlawConverter();
    PersonageHasAttachedSkillConverter personageHasAttachedSkillConverter = new PersonageHasAttachedSkillConverter();
    PersonageHasTriggerSkillConverter personageHasTriggerSkillConverter = new PersonageHasTriggerSkillConverter();
    RaceHasMeritConverter raceHasMeritConverter = new RaceHasMeritConverter();
    MeritConverter meritConverter = new MeritConverter();
    RaceHasFlawConverter raceHasFlawConverter = new RaceHasFlawConverter();
    FlawConverter flawConverter = new FlawConverter();

    private int personageId;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public PersonageWithAllRelatedEntitiesDTO getPersonage(@PathVariable Integer id) {
        this.personageId = id;
        Personage personage = personageService.getPersonageById(id);
        PersonageWithAllRelatedEntitiesDTO personageWithAllRelatedEntitiesDTO = new PersonageWithAllRelatedEntitiesDTO();

        personageWithAllRelatedEntitiesDTO.setPersonage(personageConverter.convert(personage));
        personageWithAllRelatedEntitiesDTO.setPersonageAttributes(
                personageHasAttributeConverter.convert(personageHasAttributeService.getPersonageHasAttributesByPersonageId(id)));
        personageWithAllRelatedEntitiesDTO.setPersonageBirthMerits(
                personageHasBirthMeritConverter.convert(personageHasBirthMeritService.getPersonageHasBirthMeritsByPersonageId(id)));
        personageWithAllRelatedEntitiesDTO.setPersonageMerits(
                personageHasMeritConverter.convert(personageHasMeritService.getPersonageHasMeritsByPersonageId(id)));
        personageWithAllRelatedEntitiesDTO.setPersonageFlaws(
                personageHasFlawConverter.convert(personageHasFlawService.getPersonageHasFlawsByPersonageId(id)));
        personageWithAllRelatedEntitiesDTO.setPersonageAttachedSkills(
                personageHasAttachedSkillConverter.convert(personageHasAttachedSkillService.getPersonageHasAttachedSkillsByPersonageId(id)));
        personageWithAllRelatedEntitiesDTO.setPersonageTriggerSkills(
                personageHasTriggerSkillConverter.convert(personageHasTriggerSkillService.getPersonageHasTriggerSkillsByPersonageId(id)));
        personageWithAllRelatedEntitiesDTO.setPersonageBirthMerits(
                personageHasBirthMeritConverter.convert(personageHasBirthMeritService.getPersonageHasBirthMeritsByPersonageId(id)));

        return personageWithAllRelatedEntitiesDTO;
    }

    @RequestMapping(value = "/{id}/differentTypesOfMeritsForPersonage", method = RequestMethod.GET, headers = "Accept=application/json")
    public DifferentTypesOfMeritsForPersonageDTO differentTypesOfMeritsForPersonage(@PathVariable Integer id) {
        Personage personage = personageService.getPersonageById(id);

        DifferentTypesOfMeritsForPersonageDTO differentTypesOfMeritsForPersonageDTO = new DifferentTypesOfMeritsForPersonageDTO();
        List<RaceHasMeritDTO> raceHasMerits = raceHasMeritConverter.convert(raceHasMeritService.getRaceHasMeritsByRaceId(personage.getRace().getId()));
        List<MeritDTO> allMerits = meritConverter.convert(meritService.getAllMerits());
        //merits
        List<RaceHasMeritDTO> defaultForRaceMerits = new ArrayList<RaceHasMeritDTO>();
        List<RaceHasMeritDTO> withDifferentCostForRaceMerits = new ArrayList<RaceHasMeritDTO>();
        List<PersonageHasMeritDTO> onlyForPersonageMerits = new ArrayList<PersonageHasMeritDTO>();
        List<MeritDTO> allMeritsWithoutRacesMerits = new ArrayList<MeritDTO>();
        allMeritsWithoutRacesMerits.addAll(allMerits);
        List<MeritDTO> removeListForPersonageMerits = new ArrayList<MeritDTO>();
        if (raceHasMerits.isEmpty()) {
            onlyForPersonageMerits.addAll(personageHasMeritConverter.convert(personageHasMeritService.getPersonageHasMeritsByPersonageId(id)));
        } else {
            for (RaceHasMeritDTO raceHasMeritDTO : raceHasMerits) {
                if (raceHasMeritDTO.isDefaultForRace()) {
                    defaultForRaceMerits.add(raceHasMeritDTO);
                    for (MeritDTO meritDTO : allMeritsWithoutRacesMerits) {
                        if (raceHasMeritDTO.getMerit().getId() == meritDTO.getId()) {
                            removeListForPersonageMerits.add(meritDTO);
                        }
                    }
                } else if (raceHasMeritDTO.getRaceCost() != 0) {
                    for (MeritDTO meritDTO : allMeritsWithoutRacesMerits) {
                        if (raceHasMeritDTO.getMerit().getId() == meritDTO.getId()) {
                            removeListForPersonageMerits.add(meritDTO);
                        }
                    }
                    List<PersonageHasMeritDTO> personageHasMerits = personageHasMeritConverter.convert(
                            personageHasMeritService.getPersonageHasMeritsByPersonageId(id));
                    for (PersonageHasMeritDTO personageHasMeritDTO : personageHasMerits) {
                        RaceHasMerit raceHasMeritForCurrentPersonageMerit = raceHasMeritService.
                                getRaceHasMeritByMeritIdAndRaceId(personageHasMeritDTO.getMerit().getId(),
                                        personage.getRace().getId());
                        if (personageHasMeritDTO.getMerit().getId() == raceHasMeritDTO.getMerit().getId()) {
                            withDifferentCostForRaceMerits.add(raceHasMeritDTO);
                        } else if (raceHasMeritForCurrentPersonageMerit == null) {
                            onlyForPersonageMerits.add(personageHasMeritDTO);
                        } else if (!raceHasMeritForCurrentPersonageMerit.isDefaultForRace()) {
                            onlyForPersonageMerits.add(personageHasMeritDTO);
                        }
                    }
                }
            }
        }
        allMeritsWithoutRacesMerits.removeAll(removeListForPersonageMerits);

        List<RaceHasMeritDTO> raceHasMeritsWithoutDefaults = raceHasMerits;
        raceHasMeritsWithoutDefaults.removeAll(defaultForRaceMerits);

        differentTypesOfMeritsForPersonageDTO.setDefaultForRaceMerits(defaultForRaceMerits);
        differentTypesOfMeritsForPersonageDTO.setWithDifferentCostForRaceMerits(withDifferentCostForRaceMerits);
        differentTypesOfMeritsForPersonageDTO.setOnlyForPersonageMerits(onlyForPersonageMerits);

        differentTypesOfMeritsForPersonageDTO.setRaceHasMeritsWithoutDefaults(raceHasMeritsWithoutDefaults);
        differentTypesOfMeritsForPersonageDTO.setAllMeritsWithoutRacesMerits(allMeritsWithoutRacesMerits);

        return differentTypesOfMeritsForPersonageDTO;
    }

    @RequestMapping(value = "/{id}/differentTypesOfFlawsForPersonage", method = RequestMethod.GET, headers = "Accept=application/json")
    public DifferentTypesOfFlawsForPersonageDTO differentTypesOfFlawsForPersonage(@PathVariable Integer id) {
        Personage personage = personageService.getPersonageById(id);

        DifferentTypesOfFlawsForPersonageDTO differentTypesOfFlawsForPersonageDTO = new DifferentTypesOfFlawsForPersonageDTO();
        List<RaceHasFlawDTO> raceHasFlaws = raceHasFlawConverter.convert(raceHasFlawService.getRaceHasFlawsByRaceId(personage.getRace().getId()));
        List<PersonageHasFlawDTO> allPersonageHasFlaws = personageHasFlawConverter.convert(personageHasFlawService.getPersonageHasFlawsByPersonageId(personageId));

        List<RaceHasFlawDTO> defaultForRaceFlaws = new ArrayList<RaceHasFlawDTO>();

        if (!raceHasFlaws.isEmpty()) {
            for (RaceHasFlawDTO raceHasFlaw : raceHasFlaws) {
                if (raceHasFlaw.isDefaultForRace()) {
                    defaultForRaceFlaws.add(raceHasFlaw);
                }
            }
        }

        List<PersonageHasFlawDTO> allPersonageHasFlawsWithoutDefaultForRace = new ArrayList<PersonageHasFlawDTO>();

        if (!raceHasFlaws.isEmpty()) {
            for (PersonageHasFlawDTO personageHasFlaw : allPersonageHasFlaws) {
                for (RaceHasFlawDTO defaultRaceHasFlaw : defaultForRaceFlaws) {
                    if (personageHasFlaw.getFlaw().getId() != defaultRaceHasFlaw.getFlaw().getId()) {
                        allPersonageHasFlawsWithoutDefaultForRace.add(personageHasFlaw);
                    }
                }
            }
        } else {
            allPersonageHasFlawsWithoutDefaultForRace.addAll(allPersonageHasFlaws);
        }

        differentTypesOfFlawsForPersonageDTO.setDefaultForRaceFlaws(defaultForRaceFlaws);

        differentTypesOfFlawsForPersonageDTO.setAllPersonageHasFlawsWithoutDefaultForRace(allPersonageHasFlawsWithoutDefaultForRace);

        List<FlawDTO> allFlawsWithoutDefaultForRace = flawConverter.convert(flawService.getAllFlaws());
        List<FlawDTO> removeFlawsWithoutDefaultForRace = new ArrayList<FlawDTO>();
        if (!raceHasFlaws.isEmpty()) {
            for (RaceHasFlawDTO raceHasFlaw : defaultForRaceFlaws) {
                for (FlawDTO flaw : allFlawsWithoutDefaultForRace) {
                    if (raceHasFlaw.isDefaultForRace()) {
                        if (raceHasFlaw.getFlaw().getId() == flaw.getId()) {
                            removeFlawsWithoutDefaultForRace.add(flaw);
                        }
                    }
                }
            }
        }

        allFlawsWithoutDefaultForRace.removeAll(removeFlawsWithoutDefaultForRace);

        differentTypesOfFlawsForPersonageDTO.setAllFlawsWithoutDefaultForRace(allFlawsWithoutDefaultForRace);

        return differentTypesOfFlawsForPersonageDTO;
    }

    @RequestMapping(value = "/personageAttachedSkill", method = RequestMethod.PUT)
    public PersonageWithAllRelatedEntitiesDTO addPersonageHasAttachedSkill(@RequestBody PersonageHasAttachedSkillDTO personageHasAttachedSkillDTO) {
        PersonageHasAttachedSkill personageHasAttachedSkill = personageHasAttachedSkillConverter.convert(personageHasAttachedSkillDTO);

        personageHasAttachedSkillService.addLinkAttachedSkillWithPersonage(personageHasAttachedSkill);

        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageAttachedSkill/{id}", method = RequestMethod.POST)
    public PersonageWithAllRelatedEntitiesDTO updatePersonageHasAttachedSkill(@PathVariable Integer id,
                                                                              @RequestBody PersonageHasAttachedSkillDTO personageHasAttachedSkillDTO) {
        personageHasAttachedSkillDTO.setId(id);
        PersonageHasAttachedSkill personageHasAttachedSkill = personageHasAttachedSkillConverter.convert(personageHasAttachedSkillDTO);

        personageHasAttachedSkillService.updatePersonageHasAttachedSkill(personageHasAttachedSkill);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageAttachedSkill/{id}", method = RequestMethod.DELETE)
    public PersonageWithAllRelatedEntitiesDTO deletePersonageHasAttachedSkill(@PathVariable Integer id) {
        PersonageHasAttachedSkill personageHasAttachedSkill = personageHasAttachedSkillService.getPersonageHasAttachedSkillById(id);

        personageHasAttachedSkillService.deleteLinkAttachedSkillWithPersonage(personageHasAttachedSkill);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageTriggerSkill", method = RequestMethod.PUT)
    public PersonageWithAllRelatedEntitiesDTO addPersonageHasTriggerSkill(@RequestBody PersonageHasTriggerSkillDTO personageHasTriggerSkillDTO) {
        PersonageHasTriggerSkill personageHasTriggerSkill = personageHasTriggerSkillConverter.convert(personageHasTriggerSkillDTO);

        personageHasTriggerSkillService.addLinkTriggerSkillWithPersonage(personageHasTriggerSkill);

        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageTriggerSkill/{id}", method = RequestMethod.POST)
    public PersonageWithAllRelatedEntitiesDTO updatePersonageHasTriggerSkill(@PathVariable Integer id,
                                                                             @RequestBody PersonageHasTriggerSkillDTO personageHasTriggerSkillDTO) {
        personageHasTriggerSkillDTO.setId(id);
        PersonageHasTriggerSkill personageHasTriggerSkill = personageHasTriggerSkillConverter.convert(personageHasTriggerSkillDTO);

        personageHasTriggerSkill.setTriggerSkillByPersonage(
                personageHasTriggerSkillService.getPersonageHasTriggerSkillById(id).getTriggerSkillByPersonage());
        personageHasTriggerSkill.setPersonageByTriggerSkill(
                personageHasTriggerSkillService.getPersonageHasTriggerSkillById(id).getPersonageByTriggerSkill());

        personageHasTriggerSkillService.updatePersonageHasTriggerSkill(personageHasTriggerSkill);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageTriggerSkill/{id}", method = RequestMethod.DELETE)
    public PersonageWithAllRelatedEntitiesDTO deletePersonageHasTriggerSkill(@PathVariable Integer id) {
        PersonageHasTriggerSkill personageHasTriggerSkill = personageHasTriggerSkillService.getPersonageHasTriggerSkillById(id);

        personageHasTriggerSkillService.deleteLinkTriggerSkillWithPersonage(personageHasTriggerSkill);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageTriggerSkill/skillLevels", method = RequestMethod.GET)
    public List<SkillLevel> skillLevels() {
        List<SkillLevel> skillLevels = new ArrayList<SkillLevel>();
        for (SkillLevel skillLevel : SkillLevel.values()) {
            skillLevels.add(skillLevel);
        }
        return skillLevels;
    }

    @RequestMapping(value = "/personageMerit", method = RequestMethod.PUT)
    public PersonageWithAllRelatedEntitiesDTO addPersonageHasMerit(@RequestBody PersonageHasMeritDTO personageHasMeritDTO) {
        PersonageHasMerit personageHasMerit = personageHasMeritConverter.convert(personageHasMeritDTO);

        personageHasMeritService.addLinkMeritWithPersonage(personageHasMerit);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageMerit/{id}", method = RequestMethod.POST)
    public PersonageWithAllRelatedEntitiesDTO updatePersonageHasMerit(@PathVariable Integer id,
                                                                      @RequestBody PersonageHasMeritDTO personageHasMeritDTO) {
        personageHasMeritDTO.setId(id);
        PersonageHasMerit personageHasMerit = personageHasMeritConverter.convert(personageHasMeritDTO);

        personageHasMerit.setMeritByPersonage(
                personageHasMeritService.getPersonageHasMeritById(id).getMeritByPersonage());
        personageHasMerit.setPersonageByMerit(
                personageHasMeritService.getPersonageHasMeritById(id).getPersonageByMerit());

        personageHasMeritService.updatePersonageHasMerit(personageHasMerit);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageMerit/{id}", method = RequestMethod.DELETE)
    public PersonageWithAllRelatedEntitiesDTO deletePersonageHasMerit(@PathVariable Integer id) {
        PersonageHasMerit personageHasMerit = personageHasMeritService.getPersonageHasMeritById(id);

        personageHasMeritService.deleteLinkMeritWithPersonage(personageHasMerit);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageMerit/byRaceHasMeritId/{id}", method = RequestMethod.DELETE)
    public PersonageWithAllRelatedEntitiesDTO deletePersonageHasMeritByRaceHasMeritId(@PathVariable Integer id) {
        RaceHasMerit raceHasMerit = raceHasMeritService.getRaceHasMeritById(id);
        int meritId = raceHasMerit.getMeritByRace().getId();

        PersonageHasMerit personageHasMerit = personageHasMeritService.getPersonageHasMeritByMeritIdAndPersonageId(meritId, personageId);
        personageHasMeritService.deleteLinkMeritWithPersonage(personageHasMerit);

        return getPersonage(personageId);
    }


    @RequestMapping(value = "/personageFlaw", method = RequestMethod.PUT)
    public PersonageWithAllRelatedEntitiesDTO addPersonageHasFlaw(@RequestBody PersonageHasFlawDTO personageHasFlawDTO) {
        PersonageHasFlaw personageHasFlaw = personageHasFlawConverter.convert(personageHasFlawDTO);

        personageHasFlawService.addLinkFlawWithPersonage(personageHasFlaw);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageFlaw/{id}", method = RequestMethod.POST)
    public PersonageWithAllRelatedEntitiesDTO updatePersonageHasFlaw(@PathVariable Integer id,
                                                                     @RequestBody PersonageHasFlawDTO personageHasFlawDTO) {
        personageHasFlawDTO.setId(id);
        PersonageHasFlaw personageHasFlaw = personageHasFlawConverter.convert(personageHasFlawDTO);

        personageHasFlaw.setFlawByPersonage(
                personageHasFlawService.getPersonageHasFlawById(id).getFlawByPersonage());
        personageHasFlaw.setPersonageByFlaw(
                personageHasFlawService.getPersonageHasFlawById(id).getPersonageByFlaw());

        personageHasFlawService.updatePPersonageHasFlaw(personageHasFlaw);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageFlaw/{id}", method = RequestMethod.DELETE)
    public PersonageWithAllRelatedEntitiesDTO deletePersonageHasFlaw(@PathVariable Integer id) {
        PersonageHasFlaw personageHasFlaw = personageHasFlawService.getPersonageHasFlawById(id);

        personageHasFlawService.deleteLinkFlawWithPersonage(personageHasFlaw);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageAttribute/{id}", method = RequestMethod.POST)
    public PersonageWithAllRelatedEntitiesDTO updatePersonageHasAttribute(@PathVariable Integer id,
                                                                          @RequestBody PersonageHasAttributeDTO personageHasAttributeDTO) {
        personageHasAttributeDTO.setId(id);

        PersonageHasAttribute personageHasAttribute = personageHasAttributeConverter.convert(personageHasAttributeDTO);

        personageHasAttributeService.updatePersonageHasAttribute(personageHasAttribute);

        return getPersonage(personageId);
    }

}
