package OtherAlgorithm;

import utils.test.Print;

public class Prime {
	
	public int[] getPrime1(int n) {
		if (n < 2) {
			return new int[0];
		}
		long start = System.nanoTime();
		boolean[] isEliminated = new boolean[n + 1];
		int primeCount = 0;
		int index = 2;
		while (index <= n) {
			primeCount++;
			int countIndex = index;
			int countProduct = index * countIndex;
			while (countProduct <= n) {
				isEliminated[countProduct] = true;
				countIndex++;
				countProduct = index * countIndex;
			}
			do {
				index++;
			} while (index <= n && isEliminated[index]);
		}
		int[] result = new int[primeCount];
		int resultIndex = 0;
		for (int i = 2; i < n + 1; i++) {
			if (!isEliminated[i]) {
				result[resultIndex] = i;
				resultIndex++;
			}
		}
		long end = System.nanoTime();
		System.out.println("The time consumption is: " + (end - start));
		return result;
	}
	//

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Prime one = new Prime();
		Print.printArray(one.getPrime1(31));		
	}

}
