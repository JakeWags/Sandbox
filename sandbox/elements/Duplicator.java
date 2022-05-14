package sandbox.elements;

import sandbox.SandLab;
import sandbox.elements.types.Gas;
import sandbox.elements.types.Liquid;
import sandbox.elements.types.UniqueType;
import sandbox.util.Position;

import java.awt.*;
import java.util.Random;

public class Duplicator extends Element implements UniqueType {
    public static final int elementNumber = Element.DUPLICATOR;
    public static final String NAME = "Duplicator";
    public static final Color COLOR = Color.WHITE;
    public static double density = 500; // immovable
    private final double probOfDuplication = 0.5;
    Random gen = new Random();

    @Override
    public void step(int[][] grid, int row, int col) {
        for (Element e : Element.elements) {
            if (e instanceof Liquid || e instanceof Gas) {
                Position p = SandLab.findTouching(grid, row, col, e.getElementNumber());
                if (p != null) {
                    duplicate(grid, row, col, e);
                    return;
                }
            }
        }
    }

    private void duplicate(int[][] grid, int row, int col, Element e) {
        if (gen.nextDouble() > probOfDuplication) {
            Position p = SandLab.findAnyAdjacent(grid, row, col, Element.AIR);
            if (p != null) {
                System.out.println("DUPLICATED");
                SandLab.changeElement(grid, p.row, p.col, e.getElementNumber());
            }
        } else {
            System.out.println("DID NOT DUPLICATE");
        }
    }

    @Override
    public String getName() {
        return Duplicator.NAME;
    }

    @Override
    public double getDensity() {
        return density;
    }

    @Override
    public int getElementNumber() {
        return Duplicator.elementNumber;
    }

    @Override
    public Color getColor() {
        return Duplicator.COLOR;
    }
}
