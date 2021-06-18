package commands;

import mvc.DrawingModel;

public class CmdBringToFront implements Command {
	private DrawingModel model;
	private int position;
	
	public CmdBringToFront(int position, DrawingModel model) {
		this.position = position;
		this.model = model;
	}
	
	public void execute() {
		model.getShapes().add(model.get(position));
		model.getShapes().remove(position);
	}

	public void unexecute() {
		model.getShapes().add(position, model.get(model.getShapes().size() - 1));
		model.getShapes().remove(model.getShapes().size() - 1);
	}
	
}
