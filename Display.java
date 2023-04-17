package fr.draco.gol;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;

        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.white);

        int width = (int)(Main.WINDOW_SIZE.getWidth() / Main.grid.length);
        int height = (int)(Main.WINDOW_SIZE.getHeight() / Main.grid[0].length);

        for(int x = 0; x < Main.grid.length; x++)
            for (int y = 0; y < Main.grid[x].length; y++)
                if(Main.grid[x][y])
                    g.fillRect(x * width, y * height, width, height);

        g.setColor(Color.pink);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("Step " + Main.step, 20, 50);
    }
}