package jiuzhang.c8.heappriorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class M612KColosetPoints {
	

	public Point[] kClosest(Point[] points, Point origin, int k) {
		// write your code here
		if(k < 0){
			return null;
		}
		Point[] result = new Point[k];
		if(points == null || points.length == 0){
			return result;
		}
		this.origin = origin;
		Queue<Point> record = new PriorityQueue<Point>(k, new PointComparator());
		for(int i = 0; i < points.length; i++){
			record.offer(points[i]);
		}
		for(int i = 0; i < k; i++){
			result[i] = record.poll();
		}
		return result;
	}
	
	private Point origin;
	
	private double distance(Point p){
		double sq = Math.pow(2, (double)(p.x - origin.x)) + Math.pow(2, (double)(p.y - origin.y));
		double dis = Math.sqrt(sq);
		return dis;
	}
	
	
	private class Point {
		int x;
		int y;
		@SuppressWarnings("unused")
		Point() { x = 0; y = 0; }
		@SuppressWarnings("unused")
		Point(int a, int b) { x = a; y = b; }
	}
	
	private class PointComparator implements Comparator<Point>{
		public int compare(Point a, Point b){
			double da = distance(a);
			double db = distance(b);
			if(da < db){
				return -1;
			}else if(da > db){
				return 1;
			}else{
				if(a.x < b.x){
					return -1;
				}else if(a.x > b.x){
					return 1;
				}else{
					if(a.y < b.y){
						return -1;
					}else if(a.y > b.y){
						return 1;
					}else{
						return 0;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
