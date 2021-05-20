package commands;

import geometry.Shape;

public class CmdSelect implements Command {
	
	private Shape shape;
	private boolean selected;
	
	public CmdSelect(Shape shape, boolean selected) {
		this.shape = shape;
		this.selected = selected;
	}
	
	@Override
	public void execute() {
		shape.setSelected(selected);
	}

	@Override
	public void unexecute() {	
		if (shape.isSelected())
			shape.setSelected(false);
		else 
			shape.setSelected(true);
	}
}
