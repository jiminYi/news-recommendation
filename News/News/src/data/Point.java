package data;

import java.util.ArrayList;

public class Point {
	private ArrayList<Double> data;

	public Point(ArrayList<Double> data) {
		super();
		this.data = data;
	}

	public ArrayList<Double> getData() {
		return data;
	}

	public void setData(ArrayList<Double> data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object obj) {
		if(this.data.contains(((Point) obj).getData())) {
			return true;
		}
		return false;
	}

	public static double getDistance(Point p1, Point p2) {
		double distance = 0;
		double a = 0, b = 0, c = 0;
		int dimension = p1.getData().size();
		if(p2.getData().size() != dimension) {
			return -1;
		}
		for(int i = 0; i < dimension; i++) {
			a += p1.getData().get(i) * p2.getData().get(i);
			b += Math.pow(p1.getData().get(i), 2);
			c += Math.pow(p2.getData().get(i), 2);
		}
		if(a == 0) {
			return 1;
		} else {
			distance = (a / (Math.sqrt(b*c)));
			return 1 - distance;
		}
	}

	@Override
	public String toString() {
		String data = "";
		for(Double d : this.data) {
			data += d + " ";
		}
		return "Point [data=" + data + "]";
	}
}
