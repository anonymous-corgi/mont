package algorithm;

public class KnuthMorrisPratt {
  
  public void KMPSearch(String source, String pattern) {
    int sLen = source.length();
    int pLen = pattern.length();

    int sIndex = 0; // index for source.
    int pIndex = 0; // index for pattern.
    
    char[] sou = source.toCharArray();
    char[] pat = pattern.toCharArray();
    // Preprocess the pattern (calculate lps[] array)
    int lps[] = getLPS(pat);
    
    while (sIndex < sLen) {
      if (pat[pIndex] == sou[sIndex]) {
        sIndex++;
        pIndex++;
        
        if (pIndex == pLen) {
          System.out.println("Found pattern at index "  + (sIndex - pIndex));
          pIndex = lps[pIndex - 1];
        }
      }
      // mismatch after j matches
      else {
        // Do not match lps[0..lps[j-1]] characters,
        // they will match anyway
        if (pIndex != 0) {
          pIndex = lps[pIndex - 1];
        } else {
          sIndex++;
        }
      }
    }
  }
  //lps[i] means the pat.subString(0, lps[i]) eauals to pat.subString(i - lps[i] + 1, i + 1);
  //For example: pat:"ABAB" lps[3] = 2; lps:[0, 0, 1, 2];
  //For example: pat:"ABCDAB" lps[5] = 2; lps:[0, 0, 0, 0, 1, 2];
  //For example: pat:"ABCDAB" lps[5] = 2;
  private int[] getLPS(char[] pat) {
    // length of the previous longest prefix suffix
    int i = 1;
    int j = 0;
    // lps[0] is always 0
    int[] lps = new int[pat.length];
    
    // the loop calculates lps[i] for i = 1 to M-1
    while (i < pat.length) {
      
      if (pat[i] == pat[j]) {
        j++;
        lps[i] = j;
        i++;
      } else {
        // This is tricky. Consider the example.
        // AAACAAAA and i = 7. The idea is similar 
        // to search step.
        if (j != 0) {
          j = lps[j - 1];
          // Also, note that we do not increment
          // i here
        } else {
          lps[i] = j;
          i++;
        }
      }
    }
    return lps;
  }

  public static void main(String[] args) {
//    String source = "AABAABAAABA";
//    String pattern = "AABA";
    
    String source = "ABABDABABCDABABCABAB";
    String pattern = "ABABCD";
    
    KnuthMorrisPratt one = new KnuthMorrisPratt();
    one.KMPSearch(source, pattern);
  }

}
