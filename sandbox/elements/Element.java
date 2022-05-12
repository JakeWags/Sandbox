package sandbox.elements;

import java.awt.*;

public abstract class Element {
    public static int elementNumber;
    public static String name;
    public static Color color;
    public static final int AIR = 0;
    public static final int SAND = 1;
    public static final int WATER = 2;
    public int numElements = 3;
    public static double density;

    public static String getElementName(int eNum) {
        return switch (eNum) {
            case AIR -> "Air";
            case SAND -> "Sand";
            case WATER -> "Water";
            default -> null;
        };
    }

    public static Element getElementFromNum(int n) {
        return switch (n) {
            case AIR -> new Air();
            case SAND -> new Sand();
            case WATER -> new Water();
            default -> null;
        };
    }

    public abstract void step(int[][] grid, int row, int col);
    public abstract String getName();
    public abstract double getDensity();
}
