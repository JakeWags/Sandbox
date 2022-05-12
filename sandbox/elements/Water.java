package sandbox.elements;

import java.awt.*;
import java.util.Random;

public class Water extends Element {
    public static final int elementNumber = 2;
    public static final String NAME = "Water";
    public static final Color COLOR = Color.BLUE;
    public static double density = 2.0;


    private Random gen = new Random();

    @Override
    public void step(int[][] grid, int row, int col) {
        int current = grid[row][col];

        int direction = gen.nextInt(2);
        int newCol, newRow;

        if ((current == Element.WATER) && row+1 != grid.length) {
            newCol = col;
            newRow = (grid[row + 1][col] == Element.AIR) ? row + 1: row;
            direction = gen.nextInt(3);

            if (direction == 1) { // left
                if (col - 1 != 0) {
                    newCol = col - 1;
                }
            }
            else if (direction == 2) { // right
                if (col + 1 != grid[0].length) {
                    newCol = col + 1;
                }
            }
            if (grid[newRow][newCol] == Element.AIR) {
                grid[newRow][newCol] = Element.WATER;
                grid[row][col] = Element.AIR;
            }
        }
    }

    @Override
    public String getName() {
        return Water.NAME;
    }

    @Override
    public double getDensity() { return Water.density; }
}
