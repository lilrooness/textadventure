package client;

import javax.swing.JFrame;
import java.awt.*;

/**
 * Created by Joe-MSI on 07/02/2015.
 */
public class Window extends JFrame {

    private int width;
    private int height;

    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(0, 0, width, height);
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
