package Jiuzhang.C8.HeapPriorityQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class M518SuperUglyNumber {
	
    public int nthSuperUglyNumber(int n, int[] primes) {
        // write your code here
        if (primes == null || primes.length == 0 || n < 1) {
            return -1;
        } 
        Arrays.sort(primes);
        List<Integer> result = new ArrayList<>();
        result.add(1);
        Queue<Pair> record = new PriorityQueue<>(primes.length, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.lastNum - b.lastNum;
            }
        });
        for (int i = 0; i < primes.length; i++) {
            record.add(new Pair(primes[i], 0,primes[i]));
        }
        
        while (result.size() < n) {
            Pair current = record.poll();
            if (current.lastNum > result.get(result.size() - 1)) {
            	result.add(current.lastNum);
            }
            do {
                current.lastNum = result.get(++current.lastIndex) * current.prime;
            } while(current.lastNum <= result.get(result.size() - 1));
            
            record.offer(current);
        }
        return result.get(n - 1);
    }
    
    private class Pair {
        int lastNum;
        int lastIndex;
        int prime;
        Pair(int n, int i, int p) {
            lastNum = n;
            lastIndex = i;
            prime = p;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		M518SuperUglyNumber one = new M518SuperUglyNumber();
		int n = 11;
		int[] arr = {2,3,5};
		one.nthSuperUglyNumber(n, arr);

	}

}
