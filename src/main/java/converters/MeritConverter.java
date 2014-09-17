package converters;

import entity.Merit;
import web.rest.dto.MeritDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 8/29/14
 * Time: 6:12 PM
 */
public class MeritConverter {
    public List<MeritDTO> convert(List<Merit> allMerits) {
        List<MeritDTO> result = new ArrayList<MeritDTO>(allMerits.size());
        for (Merit merit : allMerits) {
            result.add(convert(merit));
        }
        return result;
    }

    public MeritDTO convert(Merit merit) {
        return new MeritDTO(merit.getId(), merit.getName(), merit.getCost(),
                merit.getDescription(), merit.getActionBonus());
    }

    public Merit convert(MeritDTO meritDTO) {
        return new Merit(meritDTO.getId(), meritDTO.getName(), meritDTO.getCost(),
                meritDTO.getDescription(), meritDTO.getActionBonus());
    }
}
