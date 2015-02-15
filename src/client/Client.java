package client;

import game.*;
import server.NewPlayer;
import server.PlayerMessege;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joseph Frangoudes on 04/02/2015.
 *
 * @author Joseph Frangoudes
 */
public class Client {

  private Socket socket;
  private ObjectInputStream in;
  private ObjectOutputStream out;

  private Map<String, Player> players;
  ClientGame clientGame;

  public Client() {

    players = new HashMap<>();

    socket = new Socket();

    String name = JOptionPane.showInputDialog("Name:");
    String host = JOptionPane.showInputDialog("Host");

    try {
      socket.connect(new InetSocketAddress(host, 7000));
      out = new ObjectOutputStream(socket.getOutputStream());

      NewPlayer connectionMessege =
              new NewPlayer(Messege.MessegeType.NEW_PLAYER, name);
      out.writeObject(connectionMessege);
    } catch (IOException e) {
      e.printStackTrace();
    }

    clientGame = new ClientGame(name);

    new Thread(new SocketListener()).start();
  }

  private class SocketListener implements Runnable {

    @Override
    public void run() {

      Messege messege;

      try {
        in = new ObjectInputStream(socket.getInputStream());

        while ((messege = (Messege) in.readObject()) != null) {
          processMessege(messege);
        }
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

  public void processMessege(Messege messege) {

    switch (messege.getType()) {
      case NEW_PLAYER: {
        NewPlayer playerConnected = (NewPlayer) messege;
        System.out.println(playerConnected.getPlayerName());
        clientGame.getPlayers().add(playerConnected.getPlayerName());
      } break;

      case PLAYER_MESSEGE: {
        PlayerMessege playerMessege = (PlayerMessege) messege;
        System.out.printf(playerMessege.getMessege());
      } break;
    }
  }

  public static void main(String[] args) {
    new Client();
  }
}
