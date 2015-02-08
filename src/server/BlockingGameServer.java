package server;

import game.Game;
import game.Messege;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Joe-MSI on 07/02/2015.
 */
public class BlockingGameServer implements Runnable, Observer {

    ServerSocket server;

    Game game;

    List<Connection> connections;

    public BlockingGameServer() {

        connections = new ArrayList<>();

        game = new Game();

        try {
            server = new ServerSocket();
            server.bind(new InetSocketAddress(7000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Socket socket;
        try {
            while ((socket = server.accept()) != null) {
                Connection connection = new Connection(connections.size(), socket);
                connection.addObserver(this);
                connections.add(connection);
                new Thread(connection).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object messege) {
        Connection connection = (Connection) o;
        ServerEvent serverEvent = game.processMessege((Messege) messege, connection.getPlayer());
        serverEvent.execute(connections);
    }

    public static void main(String[] args) {
        new Thread(new BlockingGameServer()).start();
    }
}
