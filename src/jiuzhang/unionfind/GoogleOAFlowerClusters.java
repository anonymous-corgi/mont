package jiuzhang.unionfind;

public class GoogleOAFlowers {

    private int[] stub;
    private int[] stubN;
    
    public int solution(int[] A, int K, int M) {
        // write your code in Java SE 8
        if (A == null) {
            return -1;
        }
        int len = A.length;
        if (len < M * K) {
            return -1;
        }
        int result = -1;
        int bNum = 0;
        stub = new int[len];
        stubN = new int[len];
        boolean[] isF = new boolean[len];
        for (int i = 0; i < len; i++) {
            stub[i] = i;
            stubN[i] = 1;
        }
        
        for (int i = 0; i < len; i++) {
            int cursor = A[i] - 1;
            isF[cursor] = true;
            if (cursor - 1 >= 0 && isF[cursor - 1]) {
                connect(cursor - 1, cursor);
                int head = find(cursor - 1);
                if (stubN[head] == K) {
                    bNum++;
                }
            }
            if (cursor + 1 < len && isF[cursor + 1]) {
                if (stubN[find(cursor)] >= K && stubN[find(cursor + 1)] >= K) {                
                    connect(cursor, cursor + 1);
                    bNum--;
                } else {
                    connect(cursor, cursor + 1);
                    if (stubN[find(cursor)] == K) {
                        bNum++;
                    }
                }
            }
            if (bNum == M) {
                result = i + 1;
            }
        }
        return result;
    }
    
    private int find(int a) {
        return stub[a] == a ? a : (stub[a] = find(stub[a]));
    }
    
    private void connect(int a, int b) {
        int sA = find(a);
        int sB = find(b);
        stubN[sA] += stubN[sB];
        stub[sB] = sA;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {7,6,1,2,4,5,3};
		int k = 2;
		int m = 2;
		System.out.println(new GoogleOAFlowers().solution(arr, k, m));
	}

}
