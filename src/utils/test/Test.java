package utils.test;

public class Test {
	
	public static boolean assertEquals(String expected, String actual) {
		if (expected == null) {
			System.out.println("Exception: expected is null");
			return false;
		}
		if (actual == null) {
			System.out.println("Exception: actual is null");
			return false;
		}
		return printResult(expected, actual, actual.equals(expected));
	}
	
	private static boolean printResult(String expected, String actual, boolean isPassed) {
		if (isPassed) {
			System.out.println("TestCase: " + expected + ": passed.");
			return true;
		} else {
			System.out.println("TestCase: " + expected + ": didn't pass. (Actual returned: "  + actual + ")");
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
