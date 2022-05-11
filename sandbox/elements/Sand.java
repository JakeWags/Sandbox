package sandbox.elements;

import java.awt.*;

public class Sand extends Element {
    public static final int elementNumber = 1;
    public static final String name = "Sand";
    public static final Color color = Color.YELLOW;

    @Override
    void step(int row, int col) {

    }

    @Override
    public String getName() {
        return Sand.name;
    }
}
