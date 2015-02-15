package client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe-MSI on 14/02/2015.
 */
public class ClientGame {

    private List<String> players;

    private Window window;

    private String name;

    public ClientGame(String name) {
        players = new ArrayList<>();
        window = new Window(name, players);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }
}
