package enums;


import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * User: artemk
 * Date: 8/1/14
 * Time: 7:22 PM
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SkillType {
    SIMPLE,
    BASIC,
    DIFFICULT;

    public String getName() {
        return this.name();
    }

    @Override
    public String toString() {
        return this.name();
    }
}
