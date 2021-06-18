package commands;

import mvc.DrawingModel;

public class CmdBringToBack implements Command {
    private DrawingModel model;
	private int position;
	
	public CmdBringToBack(int position, DrawingModel model) {
		this.position = position;
		this.model = model;
	}
	
	public void execute() {
		model.getShapes().add(0, model.get(position));
		model.getShapes().remove(position + 1);
	}

	public void unexecute() {
		model.getShapes().add(position + 1, model.get(0));
		model.getShapes().remove(0);
	}
	
}
