package game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Joseph Frangoudes on 04/02/2015.
 *
 * @author Joseph Frangoudes
 */
public class Game {

  private Map<Player, Queue<String>> messegeQueues;
  private List<Battle> currentBattles;

  public Game() {
    messegeQueues = new HashMap<>();
  }

  public String getPlayerMessege(Player player) {
    if(messegeQueues.containsKey(player)) {
      return messegeQueues.get(player).remove();
    }
    return null;
  }

  public void addPlayer(Player player) {
    if(!messegeQueues.containsKey(player)) {
      messegeQueues.put(player, new PriorityQueue<>());
    }
  }

  public void sendMessage(Player player, String messege) {
    if(messegeQueues.containsKey(player)) {
      messegeQueues.get(player).add(messege);
    }
  }

  public void sendAll(String messege) {
    Set<Player> players = messegeQueues.keySet();
    for (Player player : players) {
      sendMessage(player, messege);
    }
  }

  public Player getPlayerByName(String name) {
    Set<Player> players = messegeQueues.keySet();

    for (Player player : players) {
      if(name.equals(player.getName())) {
        return player;
      }
    }

    return null;

  }

  public void processMessege(Messege messege, Player player) {
    System.out.println(messege);

    switch (messege.getType()) {

      case NEW_PLAYER: {
        NewPlayer newPlayerMessege = (NewPlayer) messege;
        if(!messegeQueues.containsKey(getPlayerByName(newPlayerMessege.getPlayerName()))) {
          player.setName(newPlayerMessege.getPlayerName());
          addPlayer(player);
        }
      }break;

      case PLAYER_MESSEGE: {
        PlayerMessege playerMessege = (PlayerMessege) messege;
        sendMessage(getPlayerByName(playerMessege.getRecipient()), playerMessege.getMessege());
      }break;

      case ATTACK: {
        AttackMessege attackMessege = (AttackMessege) messege;
        Player player1 = getPlayerByName(attackMessege.getAttacker());
        Player player2 = getPlayerByName(attackMessege.getVictim());

        if(!(player1.isBattling() || player2.isBattling())) {
          currentBattles.add(new Battle(player1, player2));
        }
      }break;

      case BATTLE_TURN: {
        BattleMessege battleMessege = (BattleMessege) messege;

      }
    }
  }
}

