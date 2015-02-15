package client;

import javax.swing.JFrame;
import java.awt.*;
import java.util.List;

/**
 * Created by Joe-MSI on 07/02/2015.
 */
public class Window extends JFrame {

    private int width;
    private int height;

    private String playerName;

    List<String> players;

    public Window(String name, List<String> players) {
        this.width = 640;
        this.height = 480;
        this.playerName = name;
        this.players = players;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setTitle(playerName);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.drawString("Name: " + playerName, 20, 60);
        g.drawString("Players connected", 20, 70);
        for(int i=0; i < players.size(); i++) {
            g.drawString(players.get(i) + ",", 20, 80 + (i*10));
        }
        repaint();
    }
}
