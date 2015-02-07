package game;

/**
 * Created by Joseph Frangoudes on 06/02/2015.
 *
 * @author Joseph Frangoudes
 */
public class PlayerMessege extends Messege {

  private String recipient;
  private String messege;

  public PlayerMessege(MessegeType type, String messege) {
    super(type);

    this.messege = messege;
  }

  public String getRecipient() {
    return recipient;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public String getMessege() {
    return messege;
  }

  public void setMessege(String messege) {
    this.messege = messege;
  }
}
