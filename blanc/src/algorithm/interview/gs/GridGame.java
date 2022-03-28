package algorithm.interview.gs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridGame {

    private static int[][] DIRS = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    private static int[][] gridGame(int[][] grid, int k, String[] rules) {
        int[] newState = new int[rules.length];
        int[][] nextGrid = new int[grid.length][grid[0].length];
        for (int i = 0; i < rules.length; i++) {
            newState[i] = rules[i].equals("alive") ? 1 : 0;
        }
        for (int g = 0; g < k; g++) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    nextGrid[i][j] = 0;
                    for (int d = 0; d < 8; d++) {
                        int x = i + DIRS[d][0];
                        int y = j + DIRS[d][1];
                        if (0 <= x && x < grid.length && 0 <= y && y < grid[x].length && grid[x][y] == 1) {
                            nextGrid[i][j]++;
                        }
                    }
                    nextGrid[i][j] = newState[nextGrid[i][j]];
                }
            }
            int[][] temp = grid;
            grid = nextGrid;
            nextGrid = temp;
        }
        return grid;
    }

    public static List<List<Integer>> gridGame(List<List<Integer>> grid, int k, List<String> rules) {
        int[] newState = new int[rules.size()];
        List<List<Integer>> nextGrid = new ArrayList<>(grid.size());
        for (int i = 0; i < rules.size(); i++) {
            newState[i] = rules.get(i).equals("alive") ? 1 : 0;
        }
        for (int i = 0; i < grid.size(); i++) {
            List<Integer> list = new ArrayList<>(grid.get(i).size());
            for (int j = 0; j < grid.get(i).size(); j++) {
                list.add(0);
            }
            nextGrid.add(list);
        }
        for (int g = 0; g < k; g++) {
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).size(); j++) {
                    int count = 0;
                    for (int d = 0; d < 8; d++) {
                        int x = i + DIRS[d][0];
                        int y = j + DIRS[d][1];
                        if (0 <= x && x < grid.size() && 0 <= y && y < grid.get(x).size() && grid.get(x).get(y) == 1) {
                            count++;
                        }
                    }
                    nextGrid.get(i).set(j, newState[count]);
                }
            }
            List<List<Integer>> temp = grid;
            grid = nextGrid;
            nextGrid = temp;
        }
        return grid;
    }

    public static void main(String[] args) {
        List<String> rules = Arrays.asList("dead", "dead", "dead", "alive", "dead", "alive", "dead", "dead", "dead", "dead");
        List<List<Integer>> grid = Arrays.asList(Arrays.asList(0, 1, 1, 0), Arrays.asList(1, 1, 0, 0));
//        List<String> rules = Arrays.asList("dead", "alive", "dead", "dead", "dead", "dead", "dead", "dead", "dead", "dead");
//        List<List<Integer>> grid = Arrays.asList(Arrays.asList(0, 1, 0, 0), Arrays.asList(0, 0, 0, 0));
        List<List<Integer>> newGrid = gridGame(grid, 2, rules);
    }
}
