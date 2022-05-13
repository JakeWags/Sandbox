package sandbox.elements;

import sandbox.elements.types.LooseSolid;

import java.awt.*;

public class Sand extends Element implements LooseSolid {
    public static final int elementNumber = Element.SAND;
    public static final String NAME = "Sand";
    public static final Color COLOR = Color.YELLOW;
    public static double density = 3.0;

    @Override
    public void step(int[][] grid, int row, int col) {
        LooseSolid.particleFlow(grid, row, col);
    }

    @Override
    public String getName() {
        return Sand.NAME;
    }

    public double getDensity() { return Sand.density; }

    @Override
    public int getElementNumber() {
        return Sand.elementNumber;
    }

    @Override
    public Color getColor() {
        return Sand.COLOR;
    }
}
