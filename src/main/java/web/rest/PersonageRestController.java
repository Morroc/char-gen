package web.rest;

import converters.*;
import entity.Personage;
import entity.PersonageHasAttachedSkill;
import entity.PersonageHasTriggerSkill;
import enums.SkillLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.*;
import web.rest.dto.PersonageHasAttachedSkillDTO;
import web.rest.dto.PersonageHasTriggerSkillDTO;
import web.rest.dto.PersonageWithAllRelatedEntitiesDTO;

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

    PersonageConverter personageConverter = new PersonageConverter();
    PersonageHasAttributeConverter personageHasAttributeConverter = new PersonageHasAttributeConverter();
    PersonageHasBirthMeritConverter personageHasBirthMeritConverter = new PersonageHasBirthMeritConverter();
    PersonageHasMeritConverter personageHasMeritConverter = new PersonageHasMeritConverter();
    PersonageHasFlawConverter personageHasFlawConverter = new PersonageHasFlawConverter();
    PersonageHasAttachedSkillConverter personageHasAttachedSkillConverter = new PersonageHasAttachedSkillConverter();
    PersonageHasTriggerSkillConverter personageHasTriggerSkillConverter = new PersonageHasTriggerSkillConverter();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public PersonageWithAllRelatedEntitiesDTO getPersonage(@PathVariable Integer id) {
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

        return personageWithAllRelatedEntitiesDTO;
    }

    @RequestMapping(value = "/personageAttachedSkill", method = RequestMethod.PUT)
    public PersonageWithAllRelatedEntitiesDTO addPersonageHasAttachedSkill(@RequestBody PersonageHasAttachedSkillDTO personageHasAttachedSkillDTO) {
        PersonageHasAttachedSkill personageHasAttachedSkill = personageHasAttachedSkillConverter.convert(personageHasAttachedSkillDTO);
        int personageId = personageHasAttachedSkill.getPersonageByAttachedSkill().getId();

        personageHasAttachedSkillService.addLinkAttachedSkillWithPersonage(personageHasAttachedSkill);

        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageAttachedSkill/{id}", method = RequestMethod.POST)
    public PersonageWithAllRelatedEntitiesDTO updatePersonageHasAttachedSkill(@PathVariable Integer id,
                                                                              @RequestBody PersonageHasAttachedSkillDTO personageHasAttachedSkillDTO) {
        personageHasAttachedSkillDTO.setId(id);
        PersonageHasAttachedSkill personageHasAttachedSkill = personageHasAttachedSkillConverter.convert(personageHasAttachedSkillDTO);

        personageHasAttachedSkill.setAttachedSkillByPersonage(
                personageHasAttachedSkillService.getPersonageHasAttachedSkillById(id).getAttachedSkillByPersonage());
        personageHasAttachedSkill.setPersonageByAttachedSkill(
                personageHasAttachedSkillService.getPersonageHasAttachedSkillById(id).getPersonageByAttachedSkill());

        personageHasAttachedSkillService.updatePersonageHasAttachedSkill(personageHasAttachedSkill);
        return getPersonage(personageHasAttachedSkill.getPersonageByAttachedSkill().getId());
    }

    @RequestMapping(value = "/personageAttachedSkill/{id}", method = RequestMethod.DELETE)
    public PersonageWithAllRelatedEntitiesDTO deletePersonageHasAttachedSkill(@PathVariable Integer id) {
        PersonageHasAttachedSkill personageHasAttachedSkill = personageHasAttachedSkillService.getPersonageHasAttachedSkillById(id);
        int personageId = personageHasAttachedSkill.getPersonageByAttachedSkill().getId();

        personageHasAttachedSkillService.deleteLinkAttachedSkillWithPersonage(personageHasAttachedSkill);
        return getPersonage(personageId);
    }

    @RequestMapping(value = "/personageTriggerSkill", method = RequestMethod.PUT)
    public PersonageWithAllRelatedEntitiesDTO addPersonageHasTriggerSkill(@RequestBody PersonageHasTriggerSkillDTO personageHasTriggerSkillDTO) {
        PersonageHasTriggerSkill personageHasTriggerSkill = personageHasTriggerSkillConverter.convert(personageHasTriggerSkillDTO);

        int personageId = personageHasTriggerSkill.getPersonageByTriggerSkill().getId();

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
        return getPersonage(personageHasTriggerSkill.getPersonageByTriggerSkill().getId());
    }

    @RequestMapping(value = "/personageTriggerSkill/{id}", method = RequestMethod.DELETE)
    public PersonageWithAllRelatedEntitiesDTO deletePersonageHasTriggerSkill(@PathVariable Integer id) {
        PersonageHasTriggerSkill personageHasTriggerSkill = personageHasTriggerSkillService.getPersonageHasTriggerSkillById(id);
        int personageId = personageHasTriggerSkill.getPersonageByTriggerSkill().getId();

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

}
