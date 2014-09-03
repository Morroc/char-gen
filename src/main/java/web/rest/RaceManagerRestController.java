package web.rest;

import converters.RaceConverter;
import entity.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.RaceService;
import web.rest.dto.RaceDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/21/14
 * Time: 4:45 PM
 */
@RestController
@RequestMapping(value = "/rest/race", consumes = "application/json", produces = "application/json")
public class RaceManagerRestController {
    @Autowired
    private RaceService raceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<RaceDTO> listRaces() {
        RaceConverter raceConverter = new RaceConverter();
        return raceConverter.convert(raceService.getAllRaces());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public List<RaceDTO> deleteRace(@PathVariable Integer id) {
        raceService.deleteRaceById(id);
        return listRaces();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public List<RaceDTO> updateRace(@PathVariable Integer id, @RequestBody RaceDTO raceDTO) {
        raceDTO.setId(id);
        raceService.updateRace(raceDTO);
        return listRaces();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public List<RaceDTO> addRace(@RequestBody RaceDTO raceDTO) {

        Race race = new Race();
        race.setMaxAge(raceDTO.getMaxAge());
        race.setName(raceDTO.getName());

        raceService.addRace(race);
        return listRaces();
    }
}
