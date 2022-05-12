package sandbox.elements;

import java.awt.*;

public class Sand extends Element {
    public static final int elementNumber = 1;
    public static final String NAME = "Sand";
    public static final Color COLOR = Color.YELLOW;
    public static double density = 3.0;

    @Override
    public void step(int[][] grid, int row, int col) {

    }

    @Override
    public String getName() {
        return Sand.NAME;
    }

    public double getDensity() { return Sand.density; }
}
