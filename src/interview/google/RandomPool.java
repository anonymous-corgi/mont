package interview.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPool {
  
  private final List<Integer> list;
  
  public RandomPool(int N) {
    this.list = new ArrayList<>(N);
    for (int i = 1; i <= N; i++) {
      list.add(i);
    }
  }
  
  public int getNum() {
    if (list.isEmpty()) {
      return -1;
    }
    int size = list.size();
    int rIndex = new Random().nextInt(size--);
    int res = list.get(rIndex);
    if (rIndex != size) {
      list.set(rIndex, list.get(size));
    }
    list.remove(size);
    return res;
  }

  public static void main(String[] args) {
    int N = 10;
    RandomPool pool = new RandomPool(N);
    for (int i = 0; i < N; i++) {
      System.out.print(pool.getNum() + "\t");
    }
  }

}
