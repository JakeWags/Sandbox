package sandbox.elements;

import sandbox.elements.types.Gas;

import java.awt.*;

public class Smoke extends Element implements Gas {
    public static final int elementNumber = Element.SMOKE;
    public static final String NAME = "Smoke";
    public static final Color COLOR = Color.DARK_GRAY;
    public static double density = -0.5; // negative density indicates upward vertical movement

    @Override
    public void step(int[][] grid, int row, int col) {
        // add behavior similar to water
    }

    @Override
    public String getName() {
        return Smoke.NAME;
    }

    @Override
    public double getDensity() {
        return density;
    }

    @Override
    public int getElementNumber() {
        return Smoke.elementNumber;
    }

    @Override
    public Color getColor() {
        return Smoke.COLOR;
    }
}
