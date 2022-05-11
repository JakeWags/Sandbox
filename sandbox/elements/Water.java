package sandbox.elements;

import java.awt.*;

public class Water extends Element {
    public static final int elementNumber = 2;
    public static final String name = "Water";
    public static final Color color = Color.BLUE;

    @Override
    void step(int row, int col) {

    }

    @Override
    public String getName() {
        return Water.name;
    }
}
