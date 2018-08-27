package interview.google;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StockPrice {
  private int timeStamp;
  private Map<Integer, Integer> timePrice;
  private TreeMap<Integer, Integer> prices;
  
  public StockPrice() {
    this.timeStamp = -1;
    this.timePrice = new HashMap<>();
    this.prices = new TreeMap<>();
  }
  
  public void update(int time, int price) {
    timeStamp = time; 
    timePrice.put(time, price);
    prices.put(price, prices.getOrDefault(price, 0) + 1);
  }
  
  public int getMaxHistory() {
    return prices.lastKey();
  }
  
  public int getCurrentPrice() {
    return timePrice.get(timeStamp);
  }
  
  public void correct(int time, int price) {
    if (!timePrice.containsKey(time)) {
      return;
    }
    int prePrice = timePrice.get(time);
    timePrice.put(time, price);
    int freq = prices.get(prePrice);
    if (freq == 1) {
      prices.remove(prePrice);
    } else {
      prices.put(prePrice, prices.get(prePrice) - 1);
    }
  }

  public static void main(String[] args) {

  }

}
