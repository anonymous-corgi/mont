package algorithm.interview.google.oa;

public class RepeatedStringMatch {
	
    public int repeatedStringMatch(String a, String b) {
        String as = a;
        for (int rep = 1; rep <= b.length() / a.length() + 2; rep++, as += a)
            if (as.indexOf(b) != -1) return rep;
        return -1;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RepeatedStringMatch one = new RepeatedStringMatch();
		String a = "abc";
		String b = "abca";
		System.out.println(one.repeatedStringMatch(a, b));
	}

}
