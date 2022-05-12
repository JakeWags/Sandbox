package sandbox.elements;

import java.awt.*;
import java.util.Random;

public class Sand extends Element {
    public static final int elementNumber = 1;
    public static final String NAME = "Sand";
    public static final Color COLOR = Color.YELLOW;
    public static double density = 3.0;

    Random gen = new Random();

    @Override
    public void step(int[][] grid, int row, int col) {
      // add better sand flow behavior
    }

    @Override
    public String getName() {
        return Sand.NAME;
    }

    public double getDensity() { return Sand.density; }
}
