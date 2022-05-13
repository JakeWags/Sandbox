package sandbox.elements;

import sandbox.elements.types.LooseSolid;

import java.awt.*;

public class Sand extends Element implements LooseSolid {
    public static final int elementNumber = 1;
    public static final String NAME = "Sand";
    public static final Color COLOR = Color.YELLOW;
    public static double density = 3.0;

    @Override
    public void step(int[][] grid, int row, int col) {
      // add better sand flow behavior
      /*
      if element below is sand and current is not on bottom:
        if not on left wall or right wall:
            if down-left is air and down-right is air:
                randomly generate left or right
                n = -1 or 1 (left or right)
                newCol = col + n
                perform air-sand swap
                exit
        if not on left wall:
            if down-left is air and down-right is not air:
                newCol = col - 1
                perform air-sand swap
                exit
        if not on right wall:
            if down-right is air and down-left is not air:
                newCol = col + 1
                perform air-sand swap
                exit
       */
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
}
