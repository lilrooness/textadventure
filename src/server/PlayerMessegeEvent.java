package server;

import game.Messege;
import game.Player;
import game.PlayerMessege;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Joe-MSI on 07/02/2015.
 */
public class PlayerMessegeEvent implements ServerEvent {

    private Player recipient;
    private String messege;

    public PlayerMessegeEvent(Player recipient, String messege) {
        this.recipient = recipient;
        this.messege = messege;
    }

    @Override
    public void execute(List<Connection> connections) {
        Map<Player, List<Connection>> collect = connections.stream().collect(Collectors.groupingBy(Connection::getPlayer));
        List<Connection> connections1 = collect.get(recipient);
        Connection connection = connections1.get(0);

        try {

            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(connection.getSocket().getOutputStream());

            objectOutputStream.writeObject(
                    new PlayerMessege(Messege.MessegeType.PLAYER_MESSEGE, messege));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Player getRecipient() {
        return recipient;
    }

    public void setRecipient(Player recipient) {
        this.recipient = recipient;
    }
}
