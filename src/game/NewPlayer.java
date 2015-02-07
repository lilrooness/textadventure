package game;

/**
 * Created by Joseph Frangoudes on 06/02/2015.
 *
 * @author Joseph Frangoudes
 */
public class NewPlayer extends Messege {

  private String playerName;

  public NewPlayer(MessegeType type, String playerName) {
    super(type);
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }
}
