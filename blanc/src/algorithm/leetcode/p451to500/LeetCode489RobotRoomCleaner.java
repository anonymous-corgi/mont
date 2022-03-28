package algorithm.leetcode.p451to500;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode489RobotRoomCleaner {

    private final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int x;
    private int y;
    private int dir = 0;
    //Map<Integer, Set<Integer>> is a good data structure for unknown boundary place.
    private Map<Integer, Set<Integer>> map = new HashMap<>();

    public void cleanRoom(Robot robot) {
        //Each time calling cleanRoom means you are currently in (x, y).
        Set<Integer> ySet = map.getOrDefault(x, new HashSet<Integer>());
        ySet.add(y);
        map.put(x, ySet);
        robot.clean();

        for (int i = 0; i < 4; i++) {
            int nx = x + DIRS[dir][0];
            int ny = y + DIRS[dir][1];
            if ((!map.containsKey(nx) || !map.get(nx).contains(ny)) && robot.move()) {
                //Update the coordinate right after you move forward. And then clean the room.
                x = nx;
                y = ny;
                cleanRoom(robot);
                //Because the direction has turn 180 degree After the cleanRoom() is called,
                //we just need to call move() to move back to the original position.
                robot.move();
                x += DIRS[dir][0];
                y += DIRS[dir][1];
                //If this is not the 4th Direction, we turn left to clean the next direction.
                //Otherwise, we turn right to make sure we turn 180 degree to the direction we just enter.
                if (i != 3) {
                    turnLeft(robot);
                } else {
                    turnRight(robot);
                }
            } else {
                if (i != 3) {
                    turnRight(robot);
                } else {
                    turnLeft(robot);
                }
            }
        }
    }

    private void turnLeft(Robot robot) {
        dir--;
        dir = dir < 0 ? dir + 4 : dir;
        robot.turnLeft();
    }

    private void turnRight(Robot robot) {
        dir++;
        dir %= 4;
        robot.turnRight();
    }

    public static void main(String[] args) {
        int[][] map = {{1, 1, 1, 1, 1, -1, 1, 1},
                {1, 1, 1, 1, 1, -1, 1, 1},
                {1, -1, 1, 1, 1, 1, 1, 1},
                {-1, -1, -1, 1, -1, -1, -1, -1},
                {1, 1, 1, 1, 1, 1, 1, 1}};
        Robot robot = new Robot(map, 1, 3);
        LeetCode489RobotRoomCleaner one = new LeetCode489RobotRoomCleaner();
        one.cleanRoom(robot);
        System.out.println(robot.isDone());
    }

    private static class Robot {
        private final String[] DIRS_SYMBOL = {"↑", "→", "↓", "←"};
        private final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        private int x;
        private int y;
        private int dir = 0;
        private final int[][] map;
        private int unCleaned;

        Robot(int[][] map, int x, int y) {
            this.map = map;
            this.x = x;
            this.y = y;
            this.unCleaned = 0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] == 1) {
                        unCleaned++;
                    }
                }
            }
            locAndDir();
        }

        public boolean move() {
            int nx = x + DIRS[dir][0];
            int ny = y + DIRS[dir][1];
            if (inBound(nx, ny) && map[nx][ny] != -1) {
                x = nx;
                y = ny;
                locAndDir();
                return true;
            }
            System.out.println("Row: " + x + ", Col: " + y + ", Dir: " + DIRS_SYMBOL[dir] + " ×");
            return false;
        }

        public void turnLeft() {
            dir--;
            if (dir < 0) {
                dir += 4;
            }
            locAndDir();
        }

        public void turnRight() {
            dir++;
            dir %= 4;
            locAndDir();
        }

        public void clean() {
            if (map[x][y] == 1) {
                unCleaned--;
            }
            map[x][y] = 0;
            printMap();
        }

        private void printMap() {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] == 1) {
                        System.out.print("*" + "\t");
                    } else if (map[i][j] == 0) {
                        System.out.print("□" + "\t");
                    } else {
                        System.out.print("■" + "\t");
                    }
                }
                System.out.println();
            }
        }

        public boolean isDone() {
            printMap();
            return unCleaned == 0;
        }

        private boolean inBound(int x, int y) {
            return (x >= 0 && x < map.length && y >= 0 && y < map[0].length);
        }

        private void locAndDir() {
            System.out.println("Row: " + x + ", Col: " + y + ", Dir: " + DIRS_SYMBOL[dir]);
        }
    }
}
