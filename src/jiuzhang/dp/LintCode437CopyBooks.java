package jiuzhang.dp;

public class LintCode437CopyBooks {
	
    int[][] f;
    int[] pages;
    int len, k;
    
    void calc(int k, int len) {
        if (f[k][len] != -1) {
            return;
        }
        
        if (k == 0) {
            f[k][len] = len == 0 ? 0 : Integer.MAX_VALUE;
            return;
        }
        
        if (len == 0) {
            f[k][len] = 0;
            return;
        }
        
        f[k][len] = Integer.MAX_VALUE;
        int s = 0;
        for (int j = len; j >= 0; --j) {
            calc(k - 1, j);
            f[k][len] = Math.min(Math.max(s, f[k - 1][j]), f[k][len]);
            if (j > 0) {
                s += pages[j - 1];
            }
        }
    }
    
    public int copyBooks(int[] pages, int k) {
        this.pages = pages;
        len = pages.length;
        this.k = k;
        
        if (len == 0) {
            return 0;
        }
        
        if (k > len) {
            k = len;
        }
        
        f = new int[k + 1][len + 1];
        int i, j;
        for (i = 0; i <= k; ++i) {
            for (j = 0; j <= len; ++j) {
                f[i][j] = -1;
            }
        }
        
        calc(k, len);
        return f[k][len];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
