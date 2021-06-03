package commands;

import mvc.DrawingModel;

import java.util.Collections;

import geometry.Shape;

public class CmdToFront implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private int position;
	
	public CmdToFront(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		position = model.getShapes().indexOf(shape);
		Collections.swap(model.getShapes(), position, position + 1);
	}

	@Override
	public void unexecute() {
		Collections.swap(model.getShapes(), position, position + 1);
	}
}
