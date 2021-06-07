package commands;

import mvc.DrawingModel;
import geometry.Shape;

public class CmdRemove implements Command {
	private Shape shape;
	private DrawingModel model;
	private int oldPosition;

	public CmdRemove(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		oldPosition = model.getShapes().indexOf(shape);
		model.remove(shape);

	}

	@Override
	public void unexecute() {
		model.addAtIndex(oldPosition, shape);

	}
	
}
