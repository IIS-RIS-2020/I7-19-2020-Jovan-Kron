package dialogs;

import javax.swing.*;
import java.awt.*;

public class DlgHexagon extends DlgCircle {

	private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        try {
        	DlgHexagon dialog = new DlgHexagon();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
