package commands;

import mvc.DrawingModel;
import java.util.Collections;

public class CmdToBack implements Command {
	private DrawingModel model;
	private int position;

	public CmdToBack(int position, DrawingModel model) {
		this.position = position;
		this.model = model;
	}
	
	public void execute() {
		Collections.swap(model.getShapes(), position, position - 1);
	}

	public void unexecute() {
		Collections.swap(model.getShapes(), position, position - 1);
	}
	
}
