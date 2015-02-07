package client;

import game.Messege;
import game.NewPlayer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Joseph Frangoudes on 04/02/2015.
 *
 * @author Joseph Frangoudes
 */
public class GameClient {

  private Socket socket;
  private ObjectInputStream in;
  private ObjectOutputStream out;

  public GameClient() {
    socket = new Socket();

    try {
      socket.connect(new InetSocketAddress("localhost", 7000));
      out = new ObjectOutputStream(socket.getOutputStream());

      new NewPlayer(Messege.MessegeType.NEW_PLAYER, "Lilroo");
      out.writeObject(new String("Hello there!\n"));
    } catch (IOException e) {
      e.printStackTrace();
    }

//    new Thread(new SocketListener()).start();
  }

  private class SocketListener implements Runnable {

    @Override
    public void run() {

      Messege messege;


      try {
        in = new ObjectInputStream(socket.getInputStream());

        while ((messege = (Messege) in.readObject()) != null) {

        }
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    new GameClient();
  }
}
