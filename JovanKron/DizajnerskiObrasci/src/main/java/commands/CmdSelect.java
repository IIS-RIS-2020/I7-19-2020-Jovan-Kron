package commands;

import geometry.Shape;

public class CmdSelect implements Command {
	private Shape shape;
	private boolean selected;
	
	public CmdSelect(Shape shape, boolean selected) {
		this.shape = shape;
		this.selected = selected;
	}
	
	public void execute() {
		shape.setSelected(selected);
	}

	public void unexecute() {	
		if (shape.isSelected())
			shape.setSelected(false);
		else 
			shape.setSelected(true);
	}
	
}
