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

    // STATIC METHODS
    // swaps two pixel number values
    public static void swap(int row, int col, int newRow, int newCol, int[][] grid) {
        int one = grid[row][col];
        int two = grid[newRow][newCol];

        grid[newRow][newCol] = one;
        grid[row][col] = two;

        SandLab.updated_positions.add(new Position(row, col));
        SandLab.updated_positions.add(new Position(newRow, newCol));
    }

    // compares the densities of two elements from the elementNumbers.
    // RETURNS:
    //      true - element one density is greater
    //      false - element two density is greater
    public static boolean compareDensities(int one, int two) {
        Element elementOne = Element.getElementFromNum(one);
        Element elementTwo = Element.getElementFromNum(two);

        if (elementOne instanceof RigidSolid || elementTwo instanceof RigidSolid) { return false; }

        return (elementOne.getDensity() > elementTwo.getDensity());
    }


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

  /* Swaps the given row,col element with the one below it */
  private void swapBelow(int row, int col) {
      int one = grid[row][col];
      int two = grid[row+1][col];

      grid[row + 1][col] = one;
      grid[row][col] = two;

      SandLab.updated_positions.add(new Position(row +1, col));
      SandLab.updated_positions.add(new Position(row, col));
  }


  /* compares densities */
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
