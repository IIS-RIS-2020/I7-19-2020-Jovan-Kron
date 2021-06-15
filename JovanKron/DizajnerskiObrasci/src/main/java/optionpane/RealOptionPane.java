package optionpane;

import java.awt.Component;
import javax.swing.JOptionPane;

public class RealOptionPane implements OptionPane {

	public void showMessageDialog(Component parentComponent, Object message) {
		JOptionPane.showMessageDialog(parentComponent, message);
	}

}
