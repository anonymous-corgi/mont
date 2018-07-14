package InterView.Google.OA;

public class KEmptySlots {

	private static final int FLOWER = 1;
	private static final int EMPTY = 0;
	private static final int INVALID = -1;
    private int[] dpL;
    private int[] dpR;
    private int len;
     
    public int kEmptySlots(int[] flowers, int k) {
        // Write your code here
        if(flowers == null) {
            return -1;
        }
        len = flowers.length;
        dpL = new int[len];
        dpR = new int[len];
        if (len == 0) {
            return -1;
        }
        
        mark(flowers[0] - 1, k);
        for (int i = 1; i < len; i++) {
            int cursor = flowers[i] - 1;
            int next = cursor + k + 1;
            if (next < len && dpR[cursor] != INVALID && dpR[next] == FLOWER) {
                return i + 1;
            }
            next = cursor - k - 1;
            if (next >= 0 && dpL[cursor] != INVALID  && dpL[next] == FLOWER) {
                return i + 1;
            }
            mark(cursor, k);
        }
        return -1;
    }
    
    private void mark(int position, int k) {
        dpL[position] = FLOWER;
        dpR[position] = FLOWER;
        int cursor = position - 1;
        int left = position - k;
        left = left < 0 ? 0 : left;
        while (left <= cursor) {
            dpR[cursor] = INVALID;
            cursor--;
        }
        
        cursor = position + 1;
        int right = position + k;        
        right = right < len ? right : len - 1;
        while (right >= cursor) {
            dpL[cursor] = INVALID;
            cursor++;
        }  
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KEmptySlots one = new KEmptySlots();
//		int[] flowers = {23,36,49,20,9,75,11,96,38,91,78,43,58,98,47,32,18,46,69,71,66,16,87,10,82,86,59,34,73,15,79,8,90,42,19,45,27,37,6,31,53,22,100,85,26,54,70,63,80,81,7,5,52,68,3,17,74,1,94,99,35,83,93,62,55,64,56,21,84,40,41,33,89,51,72,60,88,48,39,4,12,65,44,29,24,13,28,77,76,25,97,57,30,2,92,14,61,50,95,67};
//		int[] flowers = {1,3,6,4,2,5};
		int[] flowers = {6,5,8,9,7,1,10,2,3,4};
		int k = 2;
		System.out.println(one.kEmptySlots(flowers, k));

	}

}
