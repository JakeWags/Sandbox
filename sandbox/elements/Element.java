package sandbox.elements;

import java.awt.*;
import java.util.ArrayList;

public abstract class Element {
    public static int elementNumber = -1;
    public static String name = null;

    public static final int AIR = 0;
    public static final int SAND = 1;
    public static final int WATER = 2;
    public static final int STONE = 3;
    public static final int STEEL = 4;
    public static final int SMOKE = 5;
    public static final int DUPLICATOR = 6;
    public static final int DELETER = 7;
    public static int numElements = 8;
    public static double density = -1;

    public static ArrayList<Element> elements = new ArrayList<>(numElements);

    public static void init_elements() {
        elements.add(new Air());
        elements.add(new Sand());
        elements.add(new Water());
        elements.add(new Stone());
        elements.add(new Steel());
        elements.add(new Smoke());
        elements.add(new Duplicator());
        elements.add(new Deleter());
    }


    public static String getElementName(int n) {
        return elements.get(n).getName();
    }

    public static Element getElementFromNum(int n) {
        return elements.get(n);
    }

    public abstract void step(int[][] grid, int row, int col);
    public abstract String getName();
    public abstract double getDensity();
    public abstract int getElementNumber();
    public abstract Color getColor();
}
