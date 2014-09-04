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

    RaceConverter raceConverter = new RaceConverter();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<RaceDTO> listRaces() {
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
        Race race = raceConverter.convert(raceDTO);
        raceService.updateRace(race);
        return listRaces();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public List<RaceDTO> addRace(@RequestBody RaceDTO raceDTO) {

        Race race = raceConverter.convert(raceDTO);

        raceService.addRace(race);
        return listRaces();
    }
}
