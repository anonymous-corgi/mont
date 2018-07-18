package jiuzhang.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class LockUseAnalyzer {
	
	public static String A = "ACQUIRE ";
	public static String R = "RELEASE ";
	
	public static int lockUseCheck(List<String> list) {
		if (list == null) {
			return 0;
		}
		int step = 0;
		int len = list.size();
		Stack<Integer> stack = new Stack<>();
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < len; i++) {
			step++;
			String cursorStr = list.get(i);
			int cursorLock = Integer.parseInt(cursorStr.substring(8));
			if (cursorStr.charAt(0) == 'A') {
				if (set.add(cursorLock)) {
					stack.push(cursorLock);
				} else {
					return step;
				}				
			} else {
				if (set.remove(cursorLock)) {
					if (stack.pop() != cursorLock) {
						return step;
					}
				} else {
					return step;
				}
			}
		}
		return stack.size() == 0 ? 0 : ++step;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<>();
//		list.add(A + 123);
//		list.add(A + 364);
//		list.add(A + 84);
//		list.add(R + 84);
//		list.add(R + 364);
//		list.add(A + 789);
//		list.add(R + 456);
//		list.add(R + 123);
		

		list.add(A + 364);
		list.add(A + 84);
		list.add(A + 364);
		list.add(R + 364);
		System.out.println(lockUseCheck(list));

	}

}
