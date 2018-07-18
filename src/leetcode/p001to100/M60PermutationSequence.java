package leetcode.p001to100;

import java.util.LinkedList;

public class M60PermutationSequence {
	
    public String getPermutation(int n, int k) {
        if (n < 1 || k < 1) {
            return "";
        }
        
        int pos = 1;
        LinkedList<Integer> source = new LinkedList<>();
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            pos *= i;
            source.add(i);
        }
        k--;
        int digitIndex = n;
        while (digitIndex > 0) {
            pos /= digitIndex--;
            int i = k / pos;
            k %= pos;
            str.append(source.remove(i));
        }
        
        return str.toString();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M60PermutationSequence one = new M60PermutationSequence();
		int n = 3;
		int k = 3;
		System.out.println(one.getPermutation(n, k));

	}

}
