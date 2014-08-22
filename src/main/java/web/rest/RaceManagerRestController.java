package web.rest;

import entity.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.RaceService;
import web.rest.dto.RaceDto;

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

    @RequestMapping(value="/all", method=RequestMethod.GET, headers="Accept=application/json")
    public List<RaceDto> listRaces() {
        return convert(raceService.getAllRaces());
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, headers="Accept=application/json")
    //@ResponseStatus(value = HttpStatus.NO_CONTENT)
    public List<RaceDto> deleteRace(@PathVariable Integer id) {
        raceService.deleteRaceById(id);
        return listRaces();
    }

    private List<RaceDto> convert(List<Race> allRaces) {
        List<RaceDto> result = new ArrayList<RaceDto>(allRaces.size());
        for (Race race : allRaces) {
            result.add(new RaceDto(race.getId(), race.getName(), race.getMaxAge()));
        }
        return result;
    }
}
