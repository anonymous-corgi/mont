package algorithm.base.utils;

public class BlobUtil {

    public static int[] parseArray1D(String array) {
        return ParserUtil.fromJson(array, int[].class);
    }

    public static int[][] parseArray2D(String array) {
        return ParserUtil.fromJson(array, int[][].class);
    }

    public static int[][][] parseArray3D(String array) {
        return ParserUtil.fromJson(array, int[][][].class);
    }
}
