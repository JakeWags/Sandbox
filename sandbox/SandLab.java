package sandbox;

import java.awt.*;
import java.util.*;
import sandbox.elements.*;

public class SandLab
{

  private int[][] grid;
  private SandDisplay display;

  private Element[] elements = {new Air(), new Sand(), new Water()};

  public SandLab(int numRows, int numCols) {
      String[] names = new String[3];
      for(int i = 0; i < elements.length; i++) names[i] = elements[i].getName();
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
    }

    // compares the densities of two elements from the elementNumbers.
    // RETURNS:
    //      true - element one density is greater
    //      false - element two density is greater
    public static boolean compareDensities(int one, int two) {
        Element elementOne = Element.getElementFromNum(one);
        Element elementTwo = Element.getElementFromNum(two);

        return (elementOne.getDensity() > elementTwo.getDensity());
    }


  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
   grid[row][col] = tool;
  }

  private void setDisplayColor(int row, int col) {
      int c = grid[row][col];

      switch (c) {
          case Element.AIR -> display.setColor(row, col, Air.COLOR);
          case Element.SAND -> display.setColor(row, col, Sand.COLOR);
          case Element.WATER -> display.setColor(row, col, Water.COLOR);
          default -> {}
      }

  }

  /* Swaps the given row,col element with the one below it */
  private void swapBelow(int row, int col) {
      int one = grid[row][col];
      int two = grid[row+1][col];

      grid[row + 1][col] = one;
      grid[row][col] = two;
  }


  /* compares densities */
  private boolean compareDensityBelow(int row, int col) {
      if (row + 1 < grid.length) {
          int currentNum = grid[row][col];
          Element current = Element.getElementFromNum(currentNum);
          Element below = Element.getElementFromNum(grid[row + 1][col]);
          if (current == null || below == null) return false;

          double currentDensity = current.getDensity();
          double belowDensity = below.getDensity();

          return (currentDensity > belowDensity);
      }
      return false;
  }

  /** copies each element of grid into the display */
  public void updateDisplay()
  {
   int col;
   int row;
   
   for (int i = 0; i<grid[0].length*grid.length; i++) {
      col = i%grid[0].length;
      row = i/grid[0].length;

       setDisplayColor(row, col);
    }
  }

  /** called repeatedly.
   *  causes one random particle to maybe do something. */
  // this will go to individual class functions
  public void step() {
       Random gen = new Random();

       int col = gen.nextInt(grid[0].length);
       int row = gen.nextInt(grid.length);

       int rc = grid[row][col];

      for (Element e : elements) {
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
      int windowWidth = 80;
      int windowHeight = 120;
    SandLab lab = new SandLab(windowHeight, windowWidth);
    lab.run();
  }
}
