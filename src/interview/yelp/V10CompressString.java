package interview.yelp;

public class V10CompressString {
  
  public String CompressString(String s){
    if (s == null || s.length() == 0) {
      return ""; 
    }
    char prev = 0;
    int count = 0;
    char[] sArray = s.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < sArray.length; i++) {
      if (sArray[i] != prev) {
        add(sb, count, prev);
        prev = sArray[i];
        count = 1;
      } else {
        count++;
      }
    }
    add(sb, count, prev);
    return sb.toString();
  }
  
  private void add(StringBuilder sb, int count, char prev) {
    if (count == 1) {
      sb.append(prev);
    } else if (count > 1) {
      sb.append("" + count + prev);
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String s = "caaaaaadddvv";
    V10CompressString one = new V10CompressString();
    System.out.println(one.CompressString(s));
  }

}
