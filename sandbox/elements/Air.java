package sandbox.elements;

import java.awt.*;

public class Air extends Element {
    public static final int elementNumber = 0;
    public static final String NAME = "Air";
    public static final Color COLOR = Color.BLACK;
    public static double density = 1.0;

    @Override
    public void step(int[][] grid, int row, int col) {

    }

    @Override
    public String getName() {
        return Air.NAME;
    }

    @Override
    public double getDensity() { return Air.density; }

    @Override
    public int getElementNumber() {
        return Air.elementNumber;
    }

    @Override
    public Color getColor() {
        return Air.COLOR;
    }
}
