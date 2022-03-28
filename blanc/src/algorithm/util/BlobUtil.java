package algorithm.util;

public class BlobUtil {

    public static int[] parseArray1D(String json) {
        return ParserUtil.fromJson(json, int[].class);
    }

    public static int[][] parseArray2D(String json) {
        return ParserUtil.fromJson(json, int[][].class);
    }

    public static int[][][] parseArray3D(String json) {
        return ParserUtil.fromJson(json, int[][][].class);
    }
}
