package game;

/**
 * Created by Joseph Frangoudes on 04/02/2015.
 *
 * @author Joseph Frangoudes
 */
public class Player {

  String name;

  private Map.Location location;

  private boolean isBattling;

  private int agility;
  private int strength;
  private int intelect;

  private int health;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map.Location getLocation() {
    return location;
  }

  public void setLocation(Map.Location location) {
    this.location = location;
  }

  public boolean isBattling() {
    return isBattling;
  }

  public void setBattling(boolean isBattling) {
    this.isBattling = isBattling;
  }

  public int getAgility() {
    return agility;
  }

  public void setAgility(int agility) {
    this.agility = agility;
  }

  public int getStrength() {
    return strength;
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

  public int getIntelect() {
    return intelect;
  }

  public void setIntelect(int intelect) {
    this.intelect = intelect;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }
}
