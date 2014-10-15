package web.rest;

import constants.Constants;
import converters.*;
import entity.*;
import enums.AttributePriority;
import enums.SkillLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
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

    @Autowired
    private MeritHasAttributePreconditionService meritHasAttributePreconditionService;

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
    MeritHasAttributePreconditionConverter meritHasAttributePreconditionConverter = new MeritHasAttributePreconditionConverter();

    private int personageId;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public PersonageWithAllRelatedEntitiesDTO getPersonage(@PathVariable Integer id) {
        this.personageId = id;
        Personage personage = personageService.getPersonageById(id);
        PersonageWithAllRelatedEntitiesDTO personageWithAllRelatedEntitiesDTO = new PersonageWithAllRelatedEntitiesDTO();

        List<PersonageHasAttribute> personageHasAttributes = personageHasAttributeService.getPersonageHasAttributesByPersonageId(id);

        int secondaryAttributeCount = 0;
        for (PersonageHasAttribute personageHasAttribute : personageHasAttributes) {
            if (personageHasAttribute.getPriority().equals(AttributePriority.PRIMARY)) {
                personageWithAllRelatedEntitiesDTO.setPrimaryAttributeSet(true);
            }

            if (personageHasAttribute.getPriority().equals(AttributePriority.SECONDARY)) {
                secondaryAttributeCount++;
            }
        }

        if (secondaryAttributeCount == 2) {
            personageWithAllRelatedEntitiesDTO.setSecondaryAttributeSet(true);
        }

        personageWithAllRelatedEntitiesDTO.setPersonage(personageConverter.convert(personage));
        personageWithAllRelatedEntitiesDTO.setPersonageAttributes(
                personageHasAttributeConverter.convert(personageHasAttributes));
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

        List<MeritWithPreconditionDTO> allMeritsWithPreconditionsWithoutRacesMerits = new ArrayList<MeritWithPreconditionDTO>();
        for (MeritDTO merit : allMeritsWithoutRacesMerits) {
            MeritWithPreconditionDTO meritWithPreconditionDTO = new MeritWithPreconditionDTO();
            meritWithPreconditionDTO.setMerit(merit);
            meritWithPreconditionDTO.setPreconditions(
                    meritHasAttributePreconditionConverter.convert(
                            meritHasAttributePreconditionService.getMeritHasAttributePreconditionsByMeritId(merit.getId())));
            allMeritsWithPreconditionsWithoutRacesMerits.add(meritWithPreconditionDTO);
        }

        differentTypesOfMeritsForPersonageDTO.setAllMeritsWithoutRacesMerits(allMeritsWithPreconditionsWithoutRacesMerits);

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

    @RequestMapping(value = "/setGenerated", method = RequestMethod.GET)
    public PersonageWithAllRelatedEntitiesDTO setGenerated() {
        Personage personage = personageService.getPersonageById(personageId);

        if (personage.isGenerated()) {
            personage.setGenerated(false);
        } else {
            personage.setGenerated(true);
        }

        personageService.updatePersonage(personage);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageAttachedSkill", method = RequestMethod.PUT)
    public PersonageWithAllRelatedEntitiesDTO addPersonageHasAttachedSkill(@RequestBody PersonageHasAttachedSkillDTO personageHasAttachedSkillDTO) {
        PersonageHasAttachedSkill personageHasAttachedSkill = personageHasAttachedSkillConverter.convert(personageHasAttachedSkillDTO);
        Personage personage = personageService.getPersonageById(personageId);
        AttachedSkill attachedSkill = attachedSkillService.getAttachedSkillById(personageHasAttachedSkill.getAttachedSkillByPersonage().getId());

        int costOfAttachedSkillAdding = personageHasAttachedSkill.getCurrentValue() * attachedSkill.getBaseCost();

        if (personage.isGenerated() && !attachedSkill.isDefaultSkill()) {
            costOfAttachedSkillAdding = attachedSkill.getAcquiringCost();
        }

        if (attachedSkill.isDifficult()) {
            costOfAttachedSkillAdding = costOfAttachedSkillAdding * 2;
        }

        personage.setExperience(personage.getExperience() - costOfAttachedSkillAdding);

        personageService.updatePersonage(personage);
        personageHasAttachedSkillService.addLinkAttachedSkillWithPersonage(personageHasAttachedSkill);

        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageAttachedSkill/{id}", method = RequestMethod.POST)
    public PersonageWithAllRelatedEntitiesDTO updatePersonageHasAttachedSkill(@PathVariable Integer id,
                                                                              @RequestBody PersonageHasAttachedSkillDTO personageHasAttachedSkillDTO) {
        personageHasAttachedSkillDTO.setId(id);
        PersonageHasAttachedSkill personageHasAttachedSkill = personageHasAttachedSkillConverter.convert(personageHasAttachedSkillDTO);
        AttachedSkill attachedSkill = attachedSkillService.getAttachedSkillById(personageHasAttachedSkill.getAttachedSkillByPersonage().getId());
        int personageHasAttachedSkillCurrentValue = personageHasAttachedSkillService.getPersonageHasAttachedSkillById(id).getCurrentValue();
        int personageHasAttachedSkillNewValue = personageHasAttachedSkill.getCurrentValue();
        Personage personage = personageService.getPersonageById(personageId);


        int updateValue = personageHasAttachedSkillCurrentValue - personageHasAttachedSkillNewValue;
        int costOfUpdate = 0;

        if (personage.isGenerated()) {
            if (updateValue < 0) {
                for (int i = 0; i < updateValue * -1; i++) {
                    costOfUpdate = costOfUpdate - personageHasAttachedSkill.getCurrentValue() - i;
                }
            } else {
                for (int i = 0; i < updateValue; i++) {
                    costOfUpdate = costOfUpdate + personageHasAttachedSkill.getCurrentValue() + i;
                }
            }
        } else {
            costOfUpdate = updateValue * attachedSkill.getBaseCost();
        }

        if (attachedSkill.isDifficult()) {
            costOfUpdate = costOfUpdate * 2;
        }

        personage.setExperience(personage.getExperience() + costOfUpdate);
        personageService.updatePersonage(personage);

        personageHasAttachedSkillService.updatePersonageHasAttachedSkill(personageHasAttachedSkill);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageAttachedSkill/{id}", method = RequestMethod.DELETE)
    public PersonageWithAllRelatedEntitiesDTO deletePersonageHasAttachedSkill(@PathVariable Integer id) {
        PersonageHasAttachedSkill personageHasAttachedSkill = personageHasAttachedSkillService.getPersonageHasAttachedSkillById(id);

        Personage personage = personageService.getPersonageById(personageId);
        AttachedSkill attachedSkill = attachedSkillService.getAttachedSkillById(personageHasAttachedSkill.getAttachedSkillByPersonage().getId());

        int costOfAttachedSkillRemove = personageHasAttachedSkill.getCurrentValue() * attachedSkill.getBaseCost();


        if (attachedSkill.isDifficult()) {
            costOfAttachedSkillRemove = costOfAttachedSkillRemove * 2;
        }

        personage.setExperience(personage.getExperience() + costOfAttachedSkillRemove);

        personageService.updatePersonage(personage);

        personageHasAttachedSkillService.deleteLinkAttachedSkillWithPersonage(personageHasAttachedSkill);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageTriggerSkill", method = RequestMethod.PUT)
    public PersonageWithAllRelatedEntitiesDTO addPersonageHasTriggerSkill(@RequestBody PersonageHasTriggerSkillDTO personageHasTriggerSkillDTO) {
        PersonageHasTriggerSkill personageHasTriggerSkill = personageHasTriggerSkillConverter.convert(personageHasTriggerSkillDTO);

        Personage personage = personageService.getPersonageById(personageId);
        TriggerSkill triggerSkill = triggerSkillService.getTriggerSkillById(personageHasTriggerSkill.getTriggerSkillByPersonage().getId());

        int addingCost = triggerSkill.getBaseCost();
        int increaseLevelCost = 0;

        switch (personageHasTriggerSkill.getCurrentLevel()) {
            case EXPERT: {
                increaseLevelCost = increaseLevelCost + triggerSkill.getExpertCost();
                break;
            }
            case MASTER: {
                increaseLevelCost = increaseLevelCost + triggerSkill.getExpertCost() + triggerSkill.getMasterCost();
                break;
            }
            case POST_MASTER: {
                increaseLevelCost = increaseLevelCost + triggerSkill.getExpertCost() + triggerSkill.getMasterCost() + triggerSkill.getPostMasterCost();
                break;
            }
            default:
                break;
        }

        if (personageHasTriggerSkill.isHasTalent()) {
            increaseLevelCost = (int) Math.ceil(increaseLevelCost * Constants.TALENT_COEFFICIENT);
        }

        addingCost = addingCost + increaseLevelCost;

        personage.setExperience(personage.getExperience() - addingCost);
        personageService.updatePersonage(personage);

        personageHasTriggerSkillService.addLinkTriggerSkillWithPersonage(personageHasTriggerSkill);

        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageTriggerSkill/{id}", method = RequestMethod.POST)
    public PersonageWithAllRelatedEntitiesDTO updatePersonageHasTriggerSkill(@PathVariable Integer id,
                                                                             @RequestBody PersonageHasTriggerSkillDTO personageHasTriggerSkillDTO) {
        personageHasTriggerSkillDTO.setId(id);
        PersonageHasTriggerSkill personageHasTriggerSkill = personageHasTriggerSkillConverter.convert(personageHasTriggerSkillDTO);
        Personage personage = personageService.getPersonageById(personageId);
        TriggerSkill triggerSkill = triggerSkillService.getTriggerSkillById(personageHasTriggerSkill.getTriggerSkillByPersonage().getId());

        int increaseLevelCost = 0;

        switch (personageHasTriggerSkill.getCurrentLevel()) {
            case EXPERT:{
                increaseLevelCost = triggerSkill.getExpertCost();
                break;
            }
            case MASTER:{
                increaseLevelCost = triggerSkill.getMasterCost();
                break;
            }
            case POST_MASTER:{
                increaseLevelCost = triggerSkill.getPostMasterCost();
                break;
            }
            default: {
                break;
            }
        }

        if(personageHasTriggerSkill.isHasTalent()) {
            increaseLevelCost = (int) Math.ceil(increaseLevelCost * Constants.TALENT_COEFFICIENT);
        }

        personage.setExperience(personage.getExperience() - increaseLevelCost);
        personageService.updatePersonage(personage);

        personageHasTriggerSkillService.updatePersonageHasTriggerSkill(personageHasTriggerSkill);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageTriggerSkill/{id}", method = RequestMethod.DELETE)
    public PersonageWithAllRelatedEntitiesDTO deletePersonageHasTriggerSkill(@PathVariable Integer id) {
        PersonageHasTriggerSkill personageHasTriggerSkill = personageHasTriggerSkillService.getPersonageHasTriggerSkillById(id);

        Personage personage = personageService.getPersonageById(personageId);

        TriggerSkill triggerSkill = triggerSkillService.getTriggerSkillById(personageHasTriggerSkill.getTriggerSkillByPersonage().getId());
        personage.setExperience(personage.getExperience() + triggerSkill.getBaseCost());
        personageService.updatePersonage(personage);

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
    public DifferentTypesOfMeritsForPersonageDTO addPersonageHasMerit(@RequestBody PersonageHasMeritDTO personageHasMeritDTO) {
        PersonageHasMerit personageHasMerit = personageHasMeritConverter.convert(personageHasMeritDTO);
        Personage personage = personageService.getPersonageById(personageId);
        Merit merit = meritService.getMeritById(personageHasMerit.getMeritByPersonage().getId());

        int addingCost = merit.getCost();
        for (RaceHasMerit raceHasMerit : raceHasMeritService.getRaceHasMeritsByRaceId(personage.getRace().getId())) {
            if (raceHasMerit.getMeritByRace().getId() == merit.getId()) {
                addingCost = raceHasMerit.getRaceCost();
            }
        }

        personage.setExperience(personage.getExperience() - addingCost);

        personageService.updatePersonage(personage);
        personageHasMeritService.addLinkMeritWithPersonage(personageHasMerit);
        return differentTypesOfMeritsForPersonage(personageId);
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
        Personage personage = personageService.getPersonageById(personageId);
        Merit merit = meritService.getMeritById(personageHasMerit.getMeritByPersonage().getId());

        int addingCost = merit.getCost();
        for (RaceHasMerit raceHasMerit : raceHasMeritService.getRaceHasMeritsByRaceId(personage.getRace().getId())) {
            if (raceHasMerit.getMeritByRace().getId() == merit.getId()) {
                addingCost = raceHasMerit.getRaceCost();
            }
        }

        personage.setExperience(personage.getExperience() + addingCost);

        personageService.updatePersonage(personage);

        personageHasMeritService.deleteLinkMeritWithPersonage(personageHasMerit);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageMerit/byRaceHasMeritId/{id}", method = RequestMethod.DELETE)
    public PersonageWithAllRelatedEntitiesDTO deletePersonageHasMeritByRaceHasMeritId(@PathVariable Integer id) {
        RaceHasMerit raceHasMerit = raceHasMeritService.getRaceHasMeritById(id);
        int meritId = raceHasMerit.getMeritByRace().getId();

        PersonageHasMerit personageHasMerit = personageHasMeritService.getPersonageHasMeritByMeritIdAndPersonageId(meritId, personageId);
        Personage personage = personageService.getPersonageById(personageId);
        personage.setExperience(personage.getExperience() + raceHasMerit.getRaceCost());
        personageService.updatePersonage(personage);

        personageHasMeritService.deleteLinkMeritWithPersonage(personageHasMerit);

        return getPersonage(personageId);
    }


    @RequestMapping(value = "/personageFlaw", method = RequestMethod.PUT)
    public DifferentTypesOfFlawsForPersonageDTO addPersonageHasFlaw(@RequestBody PersonageHasFlawDTO personageHasFlawDTO) {
        PersonageHasFlaw personageHasFlaw = personageHasFlawConverter.convert(personageHasFlawDTO);

        Personage personage = personageService.getPersonageById(personageId);
        Flaw flaw = flawService.getFlawById(personageHasFlaw.getFlawByPersonage().getId());
        personage.setExperience(personage.getExperience() + flaw.getCost());
        personageService.updatePersonage(personage);

        personageHasFlawService.addLinkFlawWithPersonage(personageHasFlaw);
        return differentTypesOfFlawsForPersonage(personageId);
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

        Personage personage = personageService.getPersonageById(personageId);
        Flaw flaw = flawService.getFlawById(personageHasFlaw.getFlawByPersonage().getId());
        personage.setExperience(personage.getExperience() - flaw.getCost());
        personageService.updatePersonage(personage);

        personageHasFlawService.deleteLinkFlawWithPersonage(personageHasFlaw);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageAttribute/{id}", method = RequestMethod.POST)
    public PersonageWithAllRelatedEntitiesDTO updatePersonageHasAttribute(@PathVariable Integer id,
                                                                          @RequestBody PersonageHasAttributeDTO personageHasAttributeDTO) {
        personageHasAttributeDTO.setId(id);
        PersonageHasAttribute personageHasAttributeNew = personageHasAttributeConverter.convert(personageHasAttributeDTO);
        PersonageHasAttribute personageHasAttributeOld = personageHasAttributeService.getPersonageHasAttributeById(personageHasAttributeDTO.getId());

        Personage personage = personageService.getPersonageById(personageId);
        RaceHasAttribute raceHasAttribute = raceHasAttributeService.getRaceHasAttributeByAttributeIdAndRaceId(
                personageHasAttributeNew.getAttributeByPersonage().getId(), personage.getRace().getId());
        int newValue = personageHasAttributeNew.getCurrentValue();
        int oldValue = personageHasAttributeOld.getCurrentValue();
        int differenceBetweenValues = newValue - oldValue;
        int changeCost = 0;

        if (personage.isGenerated()) {
            if (differenceBetweenValues > 0) {
                if (newValue > 1 && newValue <= 3) {
                    changeCost = differenceBetweenValues * raceHasAttribute.getFrom1To3NonGeneratingCost();
                }
                if (newValue > 3 && newValue <= 6) {
                    changeCost = differenceBetweenValues * raceHasAttribute.getFrom3To6NonGeneratingCost();
                }
                if (newValue > 6 && newValue <= 9) {
                    changeCost = differenceBetweenValues * raceHasAttribute.getFrom6To9NonGeneratingCost();
                }
                if (newValue > 9 && newValue <= 12) {
                    changeCost = differenceBetweenValues * raceHasAttribute.getFrom9To12NonGeneratingCost();
                }
            } else {
                if (newValue >= 1 && newValue < 3) {
                    changeCost = differenceBetweenValues * raceHasAttribute.getFrom1To3NonGeneratingCost();
                }
                if (newValue >= 3 && newValue < 6) {
                    changeCost = differenceBetweenValues * raceHasAttribute.getFrom3To6NonGeneratingCost();
                }
                if (newValue >= 6 && newValue < 9) {
                    changeCost = differenceBetweenValues * raceHasAttribute.getFrom6To9NonGeneratingCost();
                }
                if (newValue >= 9 && newValue < 12) {
                    changeCost = differenceBetweenValues * raceHasAttribute.getFrom9To12NonGeneratingCost();
                }
            }
        } else {
            changeCost = differenceBetweenValues * raceHasAttribute.getBaseCost();
        }

        personage.setExperience(personage.getExperience() - changeCost);
        personageService.updatePersonage(personage);

        personageHasAttributeService.updatePersonageHasAttribute(personageHasAttributeNew);
        return getPersonage(personageId);
    }
}
