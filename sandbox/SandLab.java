package sandbox;

import java.awt.*;
import java.util.*;
import sandbox.elements.*;
import sandbox.elements.types.RigidSolid;
import sandbox.util.Position;

public class SandLab
{

  private final int[][] grid;
  private final SandDisplay display;
  private static final ArrayList<Position> updated_positions = new ArrayList<>();

  public SandLab(int numRows, int numCols) {
      Element.init_elements();

      String[] names = new String[Element.numElements];
      for(int i = 0; i < Element.numElements; i++) {
          names[i] = Element.elements.get(i).getName();
      }
      display = new SandDisplay("Falling Sand", numRows, numCols, names);
      grid = new int[numRows][numCols];
  }

    /* ===================================================================================================================== */
    // STATIC METHODS
    // swaps two pixel number values
    public static void swap(int[][] grid, int row, int col, int newRow, int newCol) {
        int one = grid[row][col];
        int two = grid[newRow][newCol];

        grid[newRow][newCol] = one;
        grid[row][col] = two;

        SandLab.updated_positions.add(new Position(row, col));
        SandLab.updated_positions.add(new Position(newRow, newCol));
    }

    // change the given row, col position to the given element and update the cell
    public static void changeElement(int[][] grid, int row, int col, int eNumReplace) {
        grid[row][col] = eNumReplace;
        SandLab.updated_positions.add(new Position(row, col));
    }

    // compares the densities of two elements from the elementNumbers.
    // RETURNS:
    //      true - element one density is greater
    //      false - element two density is greater
    public static boolean compareDensities(int one, int two) {
        Element elementOne = Element.getElementFromNum(one);
        Element elementTwo = Element.getElementFromNum(two);

        // if "immovable"
        if ((elementOne instanceof RigidSolid || elementTwo instanceof RigidSolid) || (elementOne.getDensity() > 499 || elementTwo.getDensity() > 499)) { return false; }

        return (elementOne.getDensity() > elementTwo.getDensity());
    }

    // Searches the 8 adjacent cells for the given element
    //
    //      If searching for 0: (C notates the given cell)
    //         [0][3][3]
    //         [1][C][1]
    //         [1][1][1]
    //
    //      Returns the position of the top-left cell
    public static Position findAnyAdjacent(int[][] grid, int row, int col, int eNumToFind) {
      Position p;
      int tempCol;
      int tempRow;

      for (int i = -1; i <= 1; i++) {
          tempCol = col + i;
          for (int j = -1; j <= 1; j++) {
            tempRow = row + j;
            if (grid[tempRow][tempCol] == eNumToFind) return new Position(tempRow, tempCol);
         }
      }
      return null;
    }

    // Searches the 4 adjacent cells (no diagonals) for the given element
    //
    //      If searching for 0: (C notates the given cell)
    //         [0][3][3]
    //         [1][C][1]
    //         [1][1][1]
    //
    //      Returns null as there are no touching 0 element
    //
    //      If searching for 3: (C notates the given cell)
    //         [0][3][3]
    //         [1][C][1]
    //         [1][1][1]
    //
    //      Returns the position of the cell directly above the center cell.
    public static Position findTouching(int[][] grid, int row, int col, int eNumToFind) {
        int tempRow;
        Random gen = new Random();

        for (int i = -1; i <= 1; i++) {
            tempRow = row + i;
            if (i == 0) {
                if (grid[tempRow][col-1] == eNumToFind && grid[tempRow][col+1] == eNumToFind) {
                    int d = gen.nextInt(2);
                    return (d == 1) ? new Position(tempRow, col - 1) : new Position(tempRow, col + 1);
                }
                if (grid[tempRow][col - 1] == eNumToFind && grid[tempRow][col+1] != eNumToFind) {
                    return new Position(tempRow,col-1);
                }
                if (grid[tempRow][col + 1] == eNumToFind && grid[tempRow][col-1] != eNumToFind) {
                    return new Position(tempRow, col-1);
                }
            } else {
                if (grid[tempRow][col] == eNumToFind) {
                    return new Position(tempRow, col);
                }
            }
        }
        return null;
    }

    /* ===================================================================================================================== */

  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
   grid[row][col] = tool;
   updated_positions.add(new Position(row, col));
  }

  private void setDisplayColor(int row, int col) {
      int c = grid[row][col];

      display.setColor(row, col, Element.getElementFromNum(c).getColor());
  }

  // Swaps the given row,col element with the one below it
  private void swapBelow(int row, int col) {
      int one = grid[row][col];
      int two = grid[row+1][col];

      grid[row + 1][col] = one;
      grid[row][col] = two;

      SandLab.updated_positions.add(new Position(row +1, col));
      SandLab.updated_positions.add(new Position(row, col));
  }


  // Compares the density of the given row and col element with the one directly below it
  private boolean compareDensityBelow(int row, int col) {
      if (row + 1 < grid.length) {
          int current = grid[row][col];
          int below = grid[row+1][col];
          return compareDensities(current, below);
      }
      return false;
  }

  /** copies each element of grid into the display */
  public void updateDisplay()
  {
    int col;
    int row;

      for (Position p : updated_positions) {
          row = p.row;
          col = p.col;

          setDisplayColor(row, col);
      }
      updated_positions.clear();
  }

  /** called repeatedly.
   *  causes one random particle to maybe do something. */
  // this will go to individual class functions
  public void step() {
       Random gen = new Random();

       int col = gen.nextInt(grid[0].length);
       int row = gen.nextInt(grid.length);

       int rc = grid[row][col];
           for (Element e : Element.elements) {
               if (rc == e.getElementNumber()) {
                   e.step(grid, row, col);
               }
           }

      if (compareDensityBelow(row,col)) swapBelow(row, col); // if currentDensity is greater than below density
  }

  public void run()
  {
    while (true)
    {
      for (int i = 0; i < display.getSpeed(); i++)
        step();
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
    }
  }

 public static void main(String[] args)
  {
      int gridWidth = 100;
      int gridHeight = 200;
    SandLab lab = new SandLab(gridHeight, gridWidth);
    lab.run();
  }
}
