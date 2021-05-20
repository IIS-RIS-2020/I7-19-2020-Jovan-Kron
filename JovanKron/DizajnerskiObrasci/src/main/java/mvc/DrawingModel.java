package mvc;

import java.util.ArrayList;

import geometry.Shape;;

public class DrawingModel {
	
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public void add(Shape s) {
		shapes.add(s);
	}
	
	public void addAtIndex(int index, Shape s) {
		shapes.add(index, s);
	}
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public Shape get(int i) {
		return shapes.get(i);
	}

	public void remove(Shape s) {
		shapes.remove(s);
	}
}
