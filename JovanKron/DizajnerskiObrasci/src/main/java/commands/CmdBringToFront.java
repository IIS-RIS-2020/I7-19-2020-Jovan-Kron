package commands;

import mvc.DrawingModel;
import geometry.Shape;

public class CmdBringToFront implements Command {
	private DrawingModel model;
	private Shape shape;
	private int oldPosition;
	
	public CmdBringToFront(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		oldPosition = model.getShapes().indexOf(shape);
		model.getShapes().remove(oldPosition);
		model.getShapes().add(shape);
	}

	@Override
	public void unexecute() {
		model.getShapes().remove(model.getShapes().size()-1);
		model.getShapes().add(oldPosition, shape);
		
	}
	
}
