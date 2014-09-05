package converters;

import entity.Flaw;
import web.rest.dto.FlawDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/29/14
 * Time: 5:13 PM
 */
public class FlawConverter {
    public List<FlawDTO> convert(List<Flaw> allFlaws) {
        List<FlawDTO> result = new ArrayList<FlawDTO>(allFlaws.size());
        for (Flaw flaw : allFlaws) {
            result.add(convert(flaw));
        }
        return result;
    }

    public FlawDTO convert(Flaw flaw) {
        return new FlawDTO(flaw.getId(), flaw.getName(), flaw.getCost(), flaw.getDescription(), flaw.getTurnOffPreconditions());
    }

    public Flaw convert(FlawDTO flawDTO) {
        return new Flaw(flawDTO.getId(), flawDTO.getName(), flawDTO.getCost(),
                flawDTO.getDescription(), flawDTO.getTurnOffPreconditions());
    }
}
