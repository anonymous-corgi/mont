package algorithm.interview.google;

public class CardsGame {
  //ÿ�ſ�Ƭ����һ��ֵ������һ�ѿ�Ƭ��һͷ�ã�ÿ�ο�����һ�����ţ�
  //���������ã�����ߵ÷֡����ǿ�ƬֵΪ���������
  public int getMaxScore(int[] cards) {
    if (cards == null || cards.length == 0) {
      return 0;
    }
    int[] sum = new int[cards.length + 1];
    int[] dp = new int[cards.length + 3];
    for (int i = cards.length - 1; i >= 0; i--) {
      sum[i] += sum[i + 1] + cards[i]; 
    }
    for (int i = cards.length - 1; i >= 0; i--) {
      for (int j = 1; j <= 3; j++) { 
        dp[i] = Math.max(dp[i], sum[i] - dp[i + j]);
      }
    }
    //It return the highest score of one of the players.
    return Math.max(dp[0], sum[0] - dp[0]);
  }

  public static void main(String[] args) {
    int[] cards = {3,-9,5,8};
    CardsGame one = new CardsGame();
    System.out.print(one.getMaxScore(cards));
  }

}
