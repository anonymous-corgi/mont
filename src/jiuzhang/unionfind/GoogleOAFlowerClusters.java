package jiuzhang.unionfind;

public class GoogleOAFlowerClusters {

  //Storing the first position of this bloomed cluster.
  private int[] ufp;
  //Storing number of flowers bloomed in this cluster.
  private int[] ufv;
  
  public int solution(int[] A, int K, int M) {
    // write your code in Java SE 8
    if (A == null || A.length < M * K) {
      return -1;
    }
    int len = A.length;
    int result = -1;
    int clusterNum = 0;
    ufp = new int[len];
    ufv = new int[len];
    boolean[] isBloom = new boolean[len];
    for (int i = 0; i < len; i++) {
      ufp[i] = i;
      ufv[i] = 1;
    }
    for (int i = 0; i < len; i++) {
      int cursor = A[i] - 1;
      isBloom[cursor] = true;
      if (cursor > 0 && isBloom[cursor - 1]) {
        connect(cursor - 1, cursor);
        if (ufv[find(cursor - 1)] == K) {
          clusterNum++;
        }
      }
      if (cursor + 1 < len && isBloom[cursor + 1]) {
        //Detect whether there are two clusters that both have
        //qualified number of flowers going to connect. 
        //If so, the number of qualified clusters will decrease
        if (ufv[find(cursor)] >= K && ufv[find(cursor + 1)] >= K) {                
          connect(cursor, cursor + 1);
          clusterNum--;
        } else {
          connect(cursor, cursor + 1);
          if (ufv[find(cursor)] == K) {
            clusterNum++;
          }
        }
      }
      if (clusterNum == M) {
        //If return the last qualified day, record the result.
        result = i + 1;
        //If return the first qualified day, just return the result.
        //return i + 1;
      }
    }
    return result;
  }
  
  private int find(int num) {
    return ufp[num] == num ? num : (ufp[num] = find(ufp[num]));
  }
  
  private void connect(int a, int b) {
    int sA = find(a);
    int sB = find(b);
    ufv[sA] += ufv[sB];
    ufp[sB] = sA;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    //Correct result. Earliest day is 4. Last day is 8.
    int[] arr = {9,8,1,2,7,4,5,6,3};
    int k = 2;
    int m = 2;
    System.out.println(new GoogleOAFlowerClusters().solution(arr, k, m));
  }

}
