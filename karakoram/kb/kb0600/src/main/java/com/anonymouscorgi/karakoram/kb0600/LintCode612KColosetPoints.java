package com.anonymouscorgi.karakoram.kb0600;

import com.anonymouscorgi.karakoram.annotation.Medium;
import com.anonymouscorgi.karakoram.base.Point;
import java.util.PriorityQueue;
import java.util.Queue;
import org.jetbrains.annotations.NotNull;

@Medium
interface LintCode612KColosetPoints {

  Point[] kClosest(Point[] points, Point origin, int k);

  LintCode612KColosetPoints PQ =
      new LintCode612KColosetPoints() {

        @Override
        public Point[] kClosest(Point[] points, Point origin, int k) {
          if (k < 0 || k > points.length || points == null || points.length == 0) {
            return new Point[0];
          }

          Queue<PointWithDistance> record = new PriorityQueue<>(k);
          for (int i = 0; i < points.length; i++) {
            record.offer(
                new PointWithDistance(
                    Math.pow(2, (points[i].x - origin.x)) + Math.pow(2, (points[i].y - origin.y)),
                    points[i]));
            if (i >= k) {
              record.poll();
            }
          }

          return record.stream().map(pwd -> pwd.distance).toArray(Point[]::new);
        }

        class PointWithDistance implements Comparable<PointWithDistance> {

          Double distance;
          Point point;

          public PointWithDistance(final Double distance, final Point point) {
            this.distance = distance;
            this.point = point;
          }

          @Override
          public int compareTo(@NotNull PointWithDistance pointWithDistance) {
            return Double.compare(this.distance, pointWithDistance.distance);
          }
        }
      };
}
