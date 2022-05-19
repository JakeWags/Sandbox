package sandbox.elements;

import sandbox.SandLab;
import sandbox.elements.types.Gas;
import sandbox.elements.types.Liquid;
import sandbox.elements.types.UniqueType;
import sandbox.util.Position;

import java.awt.*;
import java.util.Random;

public class Deleter extends Element implements UniqueType {
    public static final int elementNumber = Element.DELETER;
    public static final String NAME = "Deleter";
    public static final Color COLOR = Color.RED;
    public static double density = 500;

    Random gen = new Random();

    @Override
    public void step(int[][] grid, int row, int col) {
        for (Element e : Element.elements) {
            if ((e instanceof Liquid || e instanceof Gas) && e.getElementNumber() != Element.AIR) {
                Position p = SandLab.findTouching(grid, row, col, e.getElementNumber());
                if (p != null) {
                    delete(grid, p.row, p.col, e);
                    return;
                }
            }
        }
    }

    @Override
    public String getName() {
        return Deleter.NAME;
    }

    @Override
    public double getDensity() {
        return density;
    }

    @Override
    public int getElementNumber() {
        return Deleter.elementNumber;
    }

    @Override
    public Color getColor() {
        return Deleter.COLOR;
    }

    private void delete(int[][] grid, int row, int col, Element e) {
        SandLab.changeElement(grid, row, col, Element.AIR);
    }
}
