package web.rest;

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
@RequestMapping("/rest/race")
public class RaceManagerRestController {
    @Autowired
    private RaceService raceService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<RaceDTO> listRaces() {
        return convert(raceService.getAllRaces());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public List<RaceDTO> deleteRace(@PathVariable Integer id) {
        raceService.deleteRaceById(id);
        return listRaces();
    }

    private List<RaceDTO> convert(List<Race> allRaces) {
        List<RaceDTO> result = new ArrayList<RaceDTO>(allRaces.size());
        for (Race race : allRaces) {
            result.add(new RaceDTO(race.getId(), race.getName(), race.getMaxAge()));
        }
        return result;
    }

    @RequestMapping(value = "/addRace", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<RaceDTO> addRace(@ModelAttribute("race") Race race) {
        raceService.addRace(race);
        return listRaces();
    }
}
