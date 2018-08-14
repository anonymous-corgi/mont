package interview.yelp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SummerChallenge {
  
  static class Message {
    int sender;
    int recipient;
    int conversationId;
    public Message(int sender, int recipient, int conversationId) {
      this.sender = sender;
      this.recipient = recipient;
      this.conversationId = conversationId;
    }
  }
  
  public static int businessResponsivenessRate(int bizOwnerId, List<Message> msgList) {
    if (msgList == null || msgList.size() == 0) {
      return 0;
    }
    Set<Integer> sent = new HashSet<>();
    Set<Integer> reci = new HashSet<>();
    for (int i = 0, iLen = msgList.size(); i < iLen; i++) {
      Message cursor = msgList.get(i);
      if (cursor.sender == bizOwnerId) {
        sent.add(cursor.recipient);
      } else if (cursor.recipient == bizOwnerId) {
        reci.add(cursor.sender);
      }
    }
    int sNum = sent.size();
    int rNum = reci.size();
    return rNum != 0 ? (int) ((double) sNum / rNum * 100) : 0;
  }
  

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
