package interview.coursera;

public class RangeMinimumQuery {

  class SGTNode {
    int start;
    int end;
    int min;
    SGTNode left;
    SGTNode right;
    SGTNode(int start, int end, int min) {
      this.start = start;
      this.end = end;
      this.min = min;
    }
  }

  SGTNode root;

  public void buildTree(int[] nums) {
    root = buildTree(nums, 0, nums.length - 1);
  }

  private SGTNode buildTree(int[] nums, int start, int end) {
    SGTNode root = new SGTNode(start, end, nums[start]);
    if (start == end) {
      return root;
    }

    int mid = start + (end - start) / 2;
    root.left = buildTree(nums, start, mid);
    root.right = buildTree(nums, mid + 1, end);

    if (root.left != null && root.right != null) {
      root.min = Math.min(root.left.min, root.right.min);
    } else if (root.left != null) {
      root.min = root.left.min;
    } else {
      root.min = root.right.min;
    }
    return root;
  }

}
