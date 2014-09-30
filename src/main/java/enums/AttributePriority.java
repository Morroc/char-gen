package enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by artemk on 9/25/14.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AttributePriority {
    BASIC(2),
    SECONDARY(3),
    PRIMARY(4);

    private int value;

    AttributePriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return this.name();
    }

    @Override
    public String toString() {
        return this.name();
    }
}
