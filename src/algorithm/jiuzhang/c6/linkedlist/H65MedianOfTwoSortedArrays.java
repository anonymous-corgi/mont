package algorithm.jiuzhang.c6.linkedlist;

public class H65MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A == null || B == null) {
            return -1;
        }
        double result;
        int size = A.length + B.length;
        double median1 = 0;
        double median2 = 0;

        int indexA = 0;
        int indexB = 0;
        int index = 1;
        while (index < size + 1 && index <= (size + 1) / 2 + 1) {
            int current;
            if (indexB > B.length - 1 || (indexA < A.length && A[indexA] < B[indexB])) {
                current = A[indexA++];
            } else {
                current = B[indexB++];
            }
            if (index == (size + 1) / 2) {
                median1 = (double) current;
            }
            if (index == (size + 1) / 2 + 1) {
                median2 = (double) current;
            }
            index++;
        }
        if (size % 2 == 1) {
            result = median1;
        } else {
            result = (median1 + median2) / 2;
        }
        return result;
    }

    public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr1 = {1,2,3,4,5,6};
		int[] arr2 = {1,2};
		H65MedianOfTwoSortedArrays one = new H65MedianOfTwoSortedArrays();
		System.out.println(one.findMedianSortedArrays(arr1, arr2));
	}
}
