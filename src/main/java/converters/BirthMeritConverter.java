package converters;

import entity.BirthMerit;
import web.rest.dto.BirthMeritDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: artemk
 * Date: 9/2/14
 * Time: 3:51 PM
 */
public class BirthMeritConverter {
    public List<BirthMeritDTO> convert(List<BirthMerit> allBirthMerits) {
        List<BirthMeritDTO> result = new ArrayList<BirthMeritDTO>(allBirthMerits.size());
        for (BirthMerit birthMerit : allBirthMerits) {
            result.add(convert(birthMerit));
        }
        return result;
    }

    public BirthMeritDTO convert(BirthMerit birthMerit) {
        return new BirthMeritDTO(birthMerit.getId(), birthMerit.getName(), birthMerit.getCost(),
                birthMerit.getDescription(), birthMerit.getActionBonus());
    }

    public BirthMerit convert(BirthMeritDTO birthMeritDTO) {
        return new BirthMerit(birthMeritDTO.getId(), birthMeritDTO.getName(), birthMeritDTO.getCost(),
                birthMeritDTO.getDescription(), birthMeritDTO.getActionBonus());
    }
}
