package dialogs;

import javax.swing.JTextPane;

import dialogs.DlgCommandsAnswer;

public class DlgCommandsConfirmedMock implements DlgCommandsAnswer {

	public boolean isConfirmed() {
		return true;
	}

	public JTextPane getTextPane() {
		return new JTextPane();
	}

	public void setVisible(boolean visible) {}

}
