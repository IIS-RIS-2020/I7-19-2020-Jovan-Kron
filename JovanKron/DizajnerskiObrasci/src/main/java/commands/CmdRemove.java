package commands;

import mvc.DrawingModel;

import java.util.ArrayList;

import geometry.Shape;

public class CmdRemove implements Command {
	private DrawingModel model;
	private ArrayList<Shape> shapesToBeRemoved = new ArrayList<Shape>();
	private ArrayList<Integer> oldPositions = new ArrayList<Integer>();

	public CmdRemove(DrawingModel model) {
		this.model = model;
	}
	
	public void addShapeToRemove(Shape shape) {
		shapesToBeRemoved.add(shape);
	}
	
	public ArrayList<Shape> getShapesToBeRemoved() {
		return shapesToBeRemoved;
	}

	public void execute() {
		for (Shape shapeToBeRemoved : shapesToBeRemoved)
			oldPositions.add(model.getShapes().indexOf(shapeToBeRemoved));
		for (Shape shapeToBeRemoved : shapesToBeRemoved)
			model.remove(shapeToBeRemoved);
	}

	public void unexecute() {
		for (int i = 0; i < shapesToBeRemoved.size(); i++)
			model.addAtIndex(oldPositions.get(i), shapesToBeRemoved.get(i));
		oldPositions.clear();
	}
	
}
