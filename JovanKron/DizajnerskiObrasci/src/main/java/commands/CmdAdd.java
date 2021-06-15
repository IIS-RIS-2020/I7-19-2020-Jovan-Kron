package commands;

import mvc.DrawingModel;
import geometry.Shape;

public class CmdAdd implements Command {
	private Shape shape;
	private DrawingModel model;

	public CmdAdd(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	public void execute() {
		model.add(shape);
	}

	public void unexecute() {
		model.remove(shape);	
	}
	
}
