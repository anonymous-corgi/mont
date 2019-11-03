package algorithm.base.utils;

public class FileName {

    public static void main(String[] args) {
        String nameWithNumber = "";
        getBothDir(nameWithNumber);
    }

    private static final String LEETCODE_DIR = "https://github.com/RaychHuang/Algorithm/blob/master/src/leetcode/p";
    private static final String LINTCODE_DIR = "https://github.com/RaychHuang/Algorithm/blob/master/src/lintcode/p";
    private static final String LEETCODE = "LeetCode";
    private static final String LINTCODE = "LintCode";

    private static void getBothDir(String numberandname) {
        if (numberandname != null && numberandname.length() > 0) {
            String[] nnns = numberandname.split("\\.");
            getBothDir(Integer.parseInt(nnns[0]), nnns[1]);
        }
    }

    private static void getBothDir(int number, String name) {
        getLeetCodeDir(number, name);
        System.out.println();
        getLintCodeDir(number, name);
    }

    private static String getLeetCodeDir(int number, String name) {
        return getCodeDir(LEETCODE_DIR, number, name);
    }

    private static String getLintCodeDir(int number, String name) {
        return getCodeDir(LINTCODE_DIR, number, name);
    }

    private static String getCodeDir(String dir, int number, String name) {
        int start;
        int end = 50;
        while (number > end) {
            end += 50;
        }
        start = end - 49;
        String fileName = dir == LEETCODE_DIR ? getName(LEETCODE, number, name)
                : getName(LINTCODE, number, name);
        System.out.println(fileName);
        String done = dir + formateNumber(start) + "to"
                + formateNumber(end) + "/" + fileName + ".java";
        System.out.println(done);
        return done;
    }

//  private static String getLeetCodeName(int number, String name) {
//    return getName(LEETCODE, number, name);
//  }
//  
//  private static String getLintCodeName(int number, String name) {
//    return getName(LINTCODE, number, name);
//  }

    private static String getName(String WebSite, int number, String name) {
        return WebSite + formateNumber(number) + compressName(name);
    }

    private static String compressName(String name) {
        name = name.toLowerCase();
        String[] names = name.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            if (names[i] == null || names[i].length() == 0) {
                continue;
            }
            char[] cs = names[i].toCharArray();
            cs[0] -= 32;
            sb.append(cs);
        }
        return sb.toString();
    }

    private static final String formateNumber(int number) {
        if (number < 10) {
            return "00" + number;
        } else if (number < 100) {
            return "0" + number;
        } else {
            return "" + number;
        }
    }
}
