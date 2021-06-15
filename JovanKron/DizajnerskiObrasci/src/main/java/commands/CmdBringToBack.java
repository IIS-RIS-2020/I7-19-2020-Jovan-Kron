package commands;

import mvc.DrawingModel;
import geometry.Shape;

public class CmdBringToBack implements Command {
    private DrawingModel model;
	private Shape shape;
	private int oldPosition;
	
	public CmdBringToBack(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	public void execute() {
		oldPosition = model.getShapes().indexOf(shape);
		model.getShapes().remove(oldPosition);
		model.getShapes().add(0, shape);
	}

	public void unexecute() {
		model.getShapes().remove(0);
		model.getShapes().add(oldPosition, shape);
	}
	
}
