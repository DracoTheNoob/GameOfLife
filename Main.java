package fr.draco.gol;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static final Dimension WINDOW_SIZE = new Dimension(1600, 1600);
    public static final int cellSize = 16;
    public static int step = 0;
    public static boolean[][] grid;

    static{
        grid = new boolean[(int)(WINDOW_SIZE.getWidth() / cellSize)][(int)(WINDOW_SIZE.getHeight() / cellSize)];

        for(int x = 0; x < grid.length; x++)
            for(int y = 0; y < grid[x].length; y++)
                grid[x][y] = Math.round((float) Math.random()) == 1;
    }

    public static int getValue(int x, int y){
        try{
            return grid[x][y] ? 1 : 0;
        }catch(ArrayIndexOutOfBoundsException e){
            return 0;
        }
    }

    public static int countNeighbours(int x, int y){
        int count = 0;

        count += getValue(x - 1, y - 1);
        count += getValue(x, y - 1);
        count += getValue(x + 1, y - 1);
        count += getValue(x - 1, y);
        count += getValue(x + 1, y);
        count += getValue(x - 1, y + 1);
        count += getValue(x, y + 1);
        count += getValue(x + 1, y + 1);

        return count;
    }

    public static boolean[][] updateGrid(){
        boolean[][] newGrid = new boolean[grid.length][grid[0].length];

        for(int x = 0; x < grid.length; x++) {
            for(int y = 0; y < grid[x].length; y++) {
                int neighbours = countNeighbours(x, y);

                if(grid[x][y])
                    newGrid[x][y] = neighbours >= 2 && neighbours <= 3;
                else
                    newGrid[x][y] = neighbours == 3;
            }
        }

        return newGrid;
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Game of Life");
        frame.setSize(WINDOW_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Display());
        frame.setVisible(true);

        long pause = 500;

        while(frame.isEnabled()){
            frame.repaint();
            grid = updateGrid();

            Thread.sleep(pause);
            if(pause > 25)
                pause -= 5;

            step++;
        }
    }
}