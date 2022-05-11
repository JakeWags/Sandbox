package sandbox.elements;

import java.awt.*;

public abstract class Element {
    public static int elementNumber;
    public static String name;
    public static Color color;

    abstract void step(int row, int col);
    public abstract String getName();
}
