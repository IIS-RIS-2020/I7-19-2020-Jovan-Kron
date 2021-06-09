package mvc;

import java.util.ArrayList;
import geometry.Shape;;

public class DrawingModel {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public void add(Shape shape) {
		shapes.add(shape);
	}
	
	public void addAtIndex(int index, Shape shape) {
		shapes.add(index, shape);
	}
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public Shape get(int index) {
		return shapes.get(index);
	}

	public void remove(Shape shape) {
		shapes.remove(shape);
	}
	
}
