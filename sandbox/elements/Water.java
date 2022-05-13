package sandbox.elements;

import sandbox.SandLab;

import java.awt.*;
import java.util.Random;

public class Water extends Element {
    public static final int elementNumber = Element.WATER;
    public static final String NAME = "Water";
    public static final Color COLOR = Color.BLUE;
    public static double density = 2.0;


    private Random gen = new Random();

    @Override
    public void step(int[][] grid, int row, int col) {
        int current = grid[row][col];

        int direction;
        int newCol, newRow;

        if (row+1 != grid.length) {
            newRow = (grid[row + 1][col] == Element.AIR) ? row + 1: row;
            newCol = col;

            // if falling through air, prioritize downward movement, else only enable left or right
            // the higher downward_priority is, the less horizontal dispersion there is
            int downward_priority = 15;
            direction = (newRow == row + 1) ? gen.nextInt(downward_priority) : gen.nextInt(2);

            if (direction == 0 && col - 1 >= 0) { // left
                newCol = col - 1;
            } else if (direction == 1 && col + 1 < grid[0].length) { // right
                newCol = col + 1;
            }

            if (grid[newRow][newCol] == Element.AIR) {
                SandLab.swap(row, col, newRow, newCol, grid);
            }
        }
    }

    @Override
    public String getName() {
        return Water.NAME;
    }

    @Override
    public double getDensity() { return Water.density; }

    @Override
    public int getElementNumber() {
        return Water.elementNumber;
    }

    @Override
    public Color getColor() {
        return Water.COLOR;
    }
}
