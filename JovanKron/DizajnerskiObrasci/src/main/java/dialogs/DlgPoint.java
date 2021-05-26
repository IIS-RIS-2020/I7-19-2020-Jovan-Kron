package dialogs;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgPoint extends DlgShape {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgPoint() {
		setTitle("Point dialog");
		defineSaveOperation();
	}

	@Override
	public void defineSaveOperation() {
		getSaveButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					setBaseCoordinateX(Integer.parseInt(getTxtX().getText()));
					setBaseCoordinateY(Integer.parseInt(getTxtY().getText()));
					if(getBaseCoordinateX()<0 || getBaseCoordinateY()<0)
						JOptionPane.showMessageDialog(null,"Entered values must be greater than 0!");
					else {
						setConfirmed(true);
						dispose();
					}
				}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,"Fault in entering values for numbers");
				}
			}
		});
	}
}
