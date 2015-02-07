package game;

/**
 * Created by Joseph Frangoudes on 06/02/2015.
 *
 * @author Joseph Frangoudes
 */
public class AttackMessege extends Messege {

  String attacker, victim;

  public AttackMessege(MessegeType type, String attacker, String victim) {
    super(type);

    this.attacker = attacker;
    this.victim = victim;
  }

  public String getAttacker() {
    return attacker;
  }

  public void setAttacker(String attacker) {
    this.attacker = attacker;
  }

  public String getVictim() {
    return victim;
  }

  public void setVictim(String victim) {
    this.victim = victim;
  }
}
