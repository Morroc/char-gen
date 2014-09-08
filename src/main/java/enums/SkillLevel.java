package enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:29 PM
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SkillLevel {
    BASIC,
    EXPERT,
    MASTER,
    POST_MASTER;

    public String getName() {
        return this.name();
    }

    @Override
    public String toString() {
        return this.name();
    }
}
