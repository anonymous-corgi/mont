package com.anonymouscorgi.karakoram.kb0950;

interface LeetCode973KClosestPointsToOrigin {

  int[][] kClosest(int[][] points, int k);

  LeetCode973KClosestPointsToOrigin Method =
      new LeetCode973KClosestPointsToOrigin() {
        @Override
        public int[][] kClosest(int[][] points, int k) {
          Object[][] objPoints = new Object[points.length][];
          for (int i = 0; i < points.length; i++) {
            objPoints[i] =
                new Object[] {points[i][0] * points[i][0] + points[i][1] * points[i][1], points[i]};
          }
          kClosest(objPoints, 0, points.length - 1, k - 1);
          int[][] result = new int[k][];
          for (int i = 0; i < k; i++) {
            result[i] = (int[]) objPoints[i][1];
          }
          return result;
        }

        private Object[] kClosest(Object[][] points, int start, int end, int k) {
          int pivotValue = (int) points[end][0];
          int lowerPivot = start - 1;
          int upperPivot = end + 1;
          int current = start;
          while (current < upperPivot) {
            int currentValue = (int) points[current][0];
            if (currentValue < pivotValue) {
              swap(points, ++lowerPivot, current++);
            } else if (currentValue > pivotValue) {
              swap(points, current, --upperPivot);
            } else {
              current++;
            }
          }

          if (lowerPivot >= k) {
            return kClosest(points, start, lowerPivot, k);
          } else if (upperPivot <= k) {
            return kClosest(points, upperPivot, end, k);
          } else {
            return points[k];
          }
        }

        private void swap(Object[][] points, int a, int b) {
          if (a != b) {
            Object[] temp = points[a];
            points[a] = points[b];
            points[b] = temp;
          }
        }
      };
}
