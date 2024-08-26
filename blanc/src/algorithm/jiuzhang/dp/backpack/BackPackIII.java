package algorithm.jiuzhang.dp.backpack;

//BackPackII doesn't contain repeated object
class BackPackIII {

  //Count Max value can be stored in m capacity WITH repeated use
  interface Method {

    int backPackIII(int capacity, int[] weights, int[] values);
  }

  //Method1: Loop from items first.
  static class Method1 implements Method {

    @Override
    public int backPackIII(int capacity, int[] weights, int[] values) {
      if (weights == null || weights.length == 0) {
        return 0;
      }
      int[] maxValueAtCapacity = new int[capacity + 1];
      for (int i = 0; i < weights.length; i++) {
        for (int j = weights[i]; j <= capacity; j++) {
          maxValueAtCapacity[j] =
              Math.max(maxValueAtCapacity[j],
                  maxValueAtCapacity[j - weights[i]] + values[i]);
        }
      }
      return maxValueAtCapacity[capacity];
    }
  }

  //Method2: Loop from weight first.
  static class Method2 implements Method {

    @Override
    public int backPackIII(int capacity, int[] weights, int[] values) {
      if (weights == null || weights.length == 0) {
        return 0;
      }
      int[] maxValueAtCapacity = new int[capacity + 1];
      for (int i = 1; i <= capacity; i++) {
        for (int j = 0; j < weights.length; j++) {
          if (weights[j] <= i) {
            maxValueAtCapacity[i] =
                Math.max(maxValueAtCapacity[i],
                    maxValueAtCapacity[i - weights[j]] + values[j]);
          }
        }
      }
      return maxValueAtCapacity[capacity];
    }
  }

  private static Method getMethod() {
    int res = 0;
    switch (res) {
      case 1:
        return new Method2();
      default:
        return new Method1();
    }
  }

  public static void main(String[] args) {
    int capacity = 30;
    int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int[] values = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
    Method one = getMethod();
    System.out.println(one.backPackIII(capacity, weights, values));
  }
}
