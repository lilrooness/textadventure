package game;

/**
 * Created by Joseph Frangoudes on 04/02/2015.
 *
 * @author Joseph Frangoudes
 */
public class Messege {

  private MessegeType type;

  public Messege(MessegeType type) {
    this.type = type;
  }

  public MessegeType getType() {
    return type;
  }

  public void setType(MessegeType type) {
    this.type = type;
  }

  public enum MessegeType {
    NEW_PLAYER,
    PLAYER_MESSEGE,
    ATTACK,
    BATTLE_TURN
  }
}
