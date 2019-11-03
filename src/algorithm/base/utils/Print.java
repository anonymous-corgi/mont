package algorithm.base.utils;

import java.util.Iterator;
import java.util.List;

import algorithm.base.TreeNode;

public class Print {

  // For primitive array: Arrays.toString( array )
  // For primitive multi-dimension array and object[]: Arrays.deepToString( array )
  // Primitive multi-dimension array is also a kind of object[].

  // For List<>, list.toString()
  
  public static boolean printArray(int[] nums) {
    return printArray(nums, 0, nums.length);
  }
  
  public static boolean printArray(int[] nums, int start, int end) {
    if (nums == null || nums.length == 0) {
      System.out.println("<Error> The input array is null.");
      return false;
    }
    int len = nums.length;
    if (len == 0) {
      System.out.println("<Error> The input array's length is 0.");
      return false;         
    }
    end = len > end ? end : len;
    if (start >= end) {
      System.out.println("<Error> The startIndex and the endIndex are invalid.");
      return false;
    }
    System.out.println("The Array or SubArray: {length: " + (end - start) + "}");
    System.out.print("{");
    
    int breakIndex = 0;
    for (int i = start, length = end - 1; i < length; i++) {
      System.out.print(nums[i] + ",");
      breakIndex++;
      if (breakIndex == 10) {
        System.out.println();
        breakIndex = 0;
      }
    }
    System.out.println(nums[end - 1] + "}");
    return true;   
  }
  
  public static <T> boolean printArray(T[] array) {
    return printArray(array, 0, array.length);
  }
  
  public static <T> boolean printArray(T[] array, int start, int end) {
    if (array == null || array.length == 0) {
      System.out.println("<Error> The input array is null.");
      return false;
    }
    int len = array.length;
    if (len == 0) {
      System.out.println("<Error> The input array's length is 0.");
      return false;			
    }
    end = len > end ? end : len;
    if (start >= end) {
      System.out.println("<Error> The startIndex and the endIndex are invalid.");
      return false;
    }
    System.out.println("The Array or SubArray: {length: " + (end - start) + "}");
    System.out.print("{");
    
    int breakIndex = 0;
    for (int i = start, length = end - 1; i < length; i++) {
      System.out.print(array[i] + ",");
      breakIndex++;
      if (breakIndex == 10) {
        System.out.println();
        breakIndex = 0;
      }
    }
    System.out.println(array[end - 1] + "}");
    return true;   
  }
  
  public static <T> boolean printList(List<T> list) {
    return printList(list, 0, list.size());
  }
  
  public static <T> boolean printList(List<T> list, int start, int end) {
    if (list == null) {
      System.out.println("<Error> The input List<?> is null.");
      return false;
    }
    int len = list.size();
    if (len == 0) {
      System.out.println("<Error> The input list's length is 0.");
      return false;
    }
    end = len > end ? end : len;
    if (start >= end) {
      System.out.println("<Error> The startIndex and the endIndex are invalid.");
      return false;
    }
    System.out.println("The List: {length: " + (end - start) + "}");
    
    System.out.print("{");		
    int breakIndex = 0;
    for (int i = start, length = end - 1; i < length; i++) {
      System.out.print(list.get(i) + ",");
      breakIndex++;
      if (breakIndex == 10) {
        System.out.println();
        breakIndex = 0;
      }
    }
    System.out.println(list.get(end - 1) + "}");
    return true;		
  }
  
  public static <T> boolean printListList(List<List<T>> lists) {
    if (lists == null) {
      System.out.println("<Error> The input List<List<?>> is null.");
      return false;
    }
    int len = lists.size();
    if (len == 0) {
      System.out.println("<Error> The input list's length is 0.");
      return false;
    }
    boolean hasSuccess = false;
    for (int i = 0; i < len; i++) {
      List<?> list = lists.get(i);
      if (!printList(list)) {
        System.out.println("<Error> Failed to print List<?>: " + i + ".");
      } else {
        hasSuccess = true;
      }
    }
    return hasSuccess;
  }
  
  public static void printTree(TreeNode root) {
		Iterator<TreeNode> iterator = root.iterator();
		if (!iterator.hasNext()) {
		  System.out.print("\nThe tree has no root.\n");
		}
		System.out.print("\nThe tree is:\n");
		System.out.print(iterator.next());
		while (iterator.hasNext()) {        
		  System.out.print(", ");
		  System.out.print(iterator.next());
		}
	}
}
