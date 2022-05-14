package sandbox.elements;

import sandbox.elements.types.RigidSolid;

import java.awt.*;

public class Steel extends Element implements RigidSolid {
    public static final int elementNumber = Element.STEEL;
    public static final String NAME = "Steel";
    public static final Color COLOR = Color.LIGHT_GRAY;
    public static double density = 500; // very high density means no gravitational movement

    @Override
    public void step(int[][] grid, int row, int col) {

    }

    @Override
    public String getName() {
        return Steel.NAME;
    }

    @Override
    public double getDensity() {
        return density;
    }

    @Override
    public int getElementNumber() {
        return Steel.elementNumber;
    }

    @Override
    public Color getColor() {
        return Steel.COLOR;
    }

    @Override
    public boolean isMoveable() { return moveable; }
}
