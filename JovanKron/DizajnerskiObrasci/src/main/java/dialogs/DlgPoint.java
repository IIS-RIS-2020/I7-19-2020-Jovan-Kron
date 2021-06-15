package dialogs;

import java.awt.event.*;

public class DlgPoint extends DlgShape {
	private static final long serialVersionUID = 1L;

	public DlgPoint() {
		setTitle("Point dialog");
		defineSaveOperation();
	}

	protected void defineSaveOperation() {
		getSaveButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					readBaseCoordinates();
					if(getBaseCoordinateX() < 0 || getBaseCoordinateY() < 0)
						getOptionPane().showMessageDialog(null, "Entered values must be greater than 0!");
					else {
						setConfirmed(true);
						dispose();
					}
				}
				catch (NumberFormatException e1) {
					getOptionPane().showMessageDialog(null, "Fault in entering values for numbers");
				}
			}
		});
	}
	
}
