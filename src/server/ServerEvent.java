package server;

import game.Game;

import java.util.List;

/**
 * Created by Joe-MSI on 07/02/2015.
 */
public interface ServerEvent {
    public void execute(List<Connection> connections);
}
