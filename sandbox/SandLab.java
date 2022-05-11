package sandbox;

import java.awt.*;
import java.util.*;
import sandbox.elements.*;

public class SandLab
{
  //do not add any more fields
  private int[][] grid;
  private SandDisplay display;
  
  public SandLab(int numRows, int numCols) {
      Element[] elements = {new Air(), new Sand(), new Water()};
      String[] names = new String[3];
      for(int i = 0; i < elements.length; i++) names[i] = elements[i].getName();
      display = new SandDisplay("Falling Sand", numRows, numCols, names);
      grid = new int[numRows][numCols];
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
   grid[row][col] = tool;
  }

  private void setDisplayColor(int row, int col) {
      int c = grid[row][col];

      switch (c) {
          case Air.elementNumber -> display.setColor(row, col, Air.color);
          case Sand.elementNumber -> display.setColor(row, col, Sand.color);
          case Water.elementNumber -> display.setColor(row, col, Water.color);
          default -> {}
      }

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
  public void step() {
       Random gen = new Random();

       int col = gen.nextInt(grid[0].length);
       int row = gen.nextInt(grid.length);
       int direction;

       int newRow, newCol;

       int rc = grid[row][col];

       // replace with density value comparisons
       // if sand.density > water.density: swap
       if (rc == Sand.elementNumber && row+1 != grid.length) {
          if (grid[row+1][col] == Air.elementNumber) {
             grid[row+1][col] = Sand.elementNumber;
             grid[row][col] = Air.elementNumber;
          }
          if (grid[row+1][col] == Water.elementNumber) {
             grid[row+1][col] = Sand.elementNumber;
             grid[row][col] = Water.elementNumber;
          }
       }

   if ((rc == Water.elementNumber) && row+1 != grid.length) {
       newCol = col;
       newRow = row;
       direction = gen.nextInt(3);

       if (direction == 0) { // down
           newRow = row + 1;
       } else if (direction == 1) { // right
           if (col + 1 != grid[0].length) {
               newCol = col + 1;
               newRow = row + 1;
           }
       } else { // left
           if (col - 1 != -1) {
               newCol = col - 1;
               newRow = row + 1;
           }
       }
       if (grid[newRow][newCol] == Air.elementNumber && grid[row][col] == Water.elementNumber) {
           grid[newRow][newCol] = Water.elementNumber;
           grid[row][col] = Air.elementNumber;
       }
   }
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
    SandLab lab = new SandLab(120, 80);
    lab.run();
  }
}
