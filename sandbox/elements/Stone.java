package sandbox.elements;

import sandbox.elements.types.RigidSolid;

import java.awt.*;

public class Stone extends Element implements RigidSolid {
    public static final int elementNumber = Element.STONE;
    public static final String NAME = "Stone";
    public static final Color COLOR = Color.GRAY;
    public static double density = -1.0; // negative density means no gravitational movement

    @Override
    public void step(int[][] grid, int row, int col) {

    }

    @Override
    public String getName() {
        return Stone.NAME;
    }

    @Override
    public double getDensity() {
        return density;
    }

    @Override
    public int getElementNumber() {
        return Stone.elementNumber;
    }

    @Override
    public Color getColor() {
        return Stone.COLOR;
    }
}
