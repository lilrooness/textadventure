package server;

import game.Messege;
import game.Player;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by Joe-MSI on 07/02/2015.
 */
public class NewPlayerEvent implements ServerEvent {

    Player player;

    public NewPlayerEvent(Player player) {
        this.player = player;
    }

    @Override
    public void execute(List<Connection> connections) {
        connections.stream().forEach(this::sendMessegeToPlayer);
    }

    public void sendMessegeToPlayer(Connection connection) {
        try {
            NewPlayer newPlayer =
                    new NewPlayer(Messege.MessegeType.NEW_PLAYER, player.getName());
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(connection.getSocket().getOutputStream());
            objectOutputStream.writeObject(newPlayer);
            System.out.println("sent new player messege");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
