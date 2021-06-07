package dialogs;

import java.awt.*;

public class DlgHexagon extends DlgCircle {
	private static final long serialVersionUID = 1L;

    public DlgHexagon() {
    	setTitle("Hexagon dialog");
    	setBounds(100, 100, 198, 300);
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

	@Override
	public void defineSaveOperation() {
		super.defineSaveOperation();	
	}
}
