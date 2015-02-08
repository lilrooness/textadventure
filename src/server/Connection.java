package server;

import game.Messege;
import game.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Observable;

/**
 * Created by Joe-MSI on 07/02/2015.
 */
public class Connection extends Observable implements Runnable {

    private Player player;
    private Socket socket;

    private boolean nameNotSet = true;

    public Connection(int id, Socket socket) {
        this.socket = socket;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Messege messege;
            while ((messege = (Messege) in.readObject()) != null) {
                this.setChanged();
                this.notifyObservers(messege);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isNameNotSet() {
        return nameNotSet;
    }

    public void setNameNotSet(boolean nameNotSet) {
        this.nameNotSet = nameNotSet;
    }


}
