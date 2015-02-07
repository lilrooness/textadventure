package game;

/**
 * Created by Joseph Frangoudes on 06/02/2015.
 *
 * @author Joseph Frangoudes
 */
public class Battle {

  Player player1, player2;

  Player lastTurn;

  public Battle(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;

    player1.setBattling(true);
    player2.setBattling(true);
  }

  public void turn() {
    if(lastTurn.equals(player1)) {
      player2Turn();
    } else {
      player1Turn();
    }
  }

  public boolean battleOver() {
    if(player1.getHealth() < 1 || player2.getHealth() < 1) {
      return true;
    }

    return false;
  }

  public Player getWinner() {
    if(battleOver()) {
      return player1.getHealth() < player2.getHealth() ?player2 : player1;
    }

    return null;
  }

  public void end() {
    player1.setBattling(false);
    player2.setBattling(false);
  }

  public void player1Turn() {
    player2.setHealth(player2.getHealth() - player1.getStrength());
  }

  public void player2Turn() {
    player1.setHealth(player1.getHealth() - player2.getStrength());
  }

  public Player getPlayer1() {
    return player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public void processMessege(BattleMessege messege) {
    turn();
  }
}
