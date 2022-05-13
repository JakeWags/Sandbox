package sandbox.elements;

import java.awt.*;
import java.util.Random;

public class Sand extends Element {
    public static final int elementNumber = 1;
    public static final String NAME = "Sand";
    public static final Color COLOR = Color.YELLOW;
    public static double density = 3.0;

    Random gen = new Random();

    @Override
    public void step(int[][] grid, int row, int col) {
      // add better sand flow behavior
      /*
      if element below is sand:
        if down-left is air and down-right is air:
            randomly generate left or right
            n = -1 or 1 (left or right)
            newCol = col + n
            perform air-sand swap
            exit
        if down-left is air and down-right is not air:
            newCol = col - 1
            perform air-sand swap
            exit
        if down-right is air and down-left is not air:
            newCol = col + 1
            perform air-sand swap
            exit
       */

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
}
