package sandbox.elements.types;

import sandbox.SandLab;
import sandbox.elements.Element;

import java.util.Random;

public interface LooseSolid extends Solid {
    Random gen = new Random();

    public static void particleFlow(int[][] grid, int row, int col) {

        int current = grid[row][col];
        int below;
        if (row + 1 < grid.length) { below = grid[row+1][col]; } else return; // set below or exit function if the particle is on the bottom already

        if (below == Element.SAND) { // can only be accessed if current is not on the bottom
            if (col - 1 >= 0 && col + 1 < grid[0].length) { // in the center
                if (SandLab.compareDensities(current,grid[row+1][col - 1]) && SandLab.compareDensities(current, grid[row+1][col + 1])) { // if both sides are less dense
                    int direction = gen.nextInt(2);
                    int newCol = (direction == 1) ? col-1 : col+1; // left or right
                    SandLab.swap(row, col, row+1, newCol, grid);
                    return;
                }
                if (SandLab.compareDensities(current,grid[row+1][col - 1]) && !SandLab.compareDensities(current, grid[row + 1][col + 1])) { // if only left is less dense
                    int newCol = col - 1;
                    SandLab.swap(row, col, row+1, newCol, grid);
                    return;
                }
                if (SandLab.compareDensities(current,grid[row+1][col + 1]) && !SandLab.compareDensities(current, grid[row + 1][col - 1])) { // if only right is less dense
                    int newCol = col + 1;
                    SandLab.swap(row, col, row + 1, newCol, grid);
                    return;
                }
            }
            // SPECIAL CASES
            // left wall and right is less dense
            if (col == 0 && SandLab.compareDensities(current, grid[row+1][col+1])) {
                int newCol = col + 1;
                SandLab.swap(row,col,row+1, newCol, grid);
                return;
            }
            // right wall and left is less dense
            if (col == grid[0].length - 1 && SandLab.compareDensities(current, grid[row+1][col-1])) {
                int newCol = col - 1;
                SandLab.swap(row, col, row+1, newCol, grid);
            }
        }
    }
}
