package interview.fb;

import java.util.Random;

public class RandomMaxIndex {

    private interface Method {
        int findMaxIndex(int[] nums);
    }

    private static final class MaxPicker implements Method {

        private static final Random numGenerator = new Random();

        public int findMaxIndex(int[] nums) {
            int maxIndex = 0;
            int maxCount = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[maxIndex]) {
                    maxCount++;
                    int randomIndex = numGenerator.nextInt(maxCount);
                    if (randomIndex == 0) {
                        maxIndex = i;
                    }
                } else if (nums[i] > nums[maxIndex]) {
                    maxIndex = i;
                    maxCount = 1;
                }
            }
            return maxIndex;
        }
    }
}
