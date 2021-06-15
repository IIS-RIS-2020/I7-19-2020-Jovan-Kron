package commands;

import mvc.DrawingModel;

import java.util.Collections;

import geometry.Shape;

public class CmdToBack implements Command {
	private DrawingModel model;
	private Shape shape;
	private int position;
	
	public CmdToBack(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	public void execute() {
		position = model.getShapes().indexOf(shape);
		Collections.swap(model.getShapes(), position, position - 1);
	}

	public void unexecute() {
		Collections.swap(model.getShapes(), position, position - 1);
	}
	
}
