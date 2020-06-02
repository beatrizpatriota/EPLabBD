package objects;

import java.io.Serializable;

public class SQLObject implements Serializable {
    private final String type, body;

    public SQLObject(String instructions) {
        this.type = instructions.substring(0, instructions.indexOf(" "));
        this.body = instructions.substring(instructions.indexOf(" "));
    }

    @Override
    public String toString() {
        return type + " " + body;
    }
}
