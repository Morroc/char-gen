package web.rest;

import converters.*;
import entity.Personage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.*;
import web.rest.dto.PersonageWithAllRelatedEntitiesDTO;

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
}
