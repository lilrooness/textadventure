package game;

import server.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Joseph Frangoudes on 04/02/2015.
 *
 * @author Joseph Frangoudes
 */
public class Game {

    private List<Battle> currentBattles;
    List<Player> players;

    public Game() {
        players = new ArrayList<>();
    }

    public ServerEvent processMessege(Messege messege, Player player) {
        System.out.println(messege);

        switch (messege.getType()) {

            case NEW_PLAYER: {
                NewPlayer newPlayer = (NewPlayer) messege;
                players.add(new Player(newPlayer.getPlayerName()));
                return new NewPlayerEvent(getPlayerByName(newPlayer.getPlayerName()));
            }

            case PLAYER_MESSEGE: {
                PlayerMessege playerMessege = (PlayerMessege) messege;
                return new PlayerMessegeEvent(player, playerMessege.getMessege());
            }
            case ATTACK: {
                AttackMessege attackMessege = (AttackMessege) messege;
                Player player1 = getPlayerByName(attackMessege.getAttacker());
                Player player2 = getPlayerByName(attackMessege.getVictim());

                if (!(player1.isBattling() || player2.isBattling())) {
                    currentBattles.add(new Battle(player1, player2));
                }
                return new EmptyEvent();
            }

            case BATTLE_TURN: {
                BattleMessege battleMessege = (BattleMessege) messege;
                return new EmptyEvent();
            }
        }
        return new EmptyEvent();
    }

    private Player getPlayerByName(String name) {
        Map<String, List<Player>> collect = players.stream()
                .collect(Collectors.groupingBy(Player::getName));

        return collect.get(name).get(0);
    }

    private class EmptyEvent implements ServerEvent {

        @Override
        public void execute(List<Connection> connections) {
            //nohting happens here
        }
    }
}

