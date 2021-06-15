package dialogs;

import javax.swing.JTextPane;

public interface DlgCommandsAnswer {
	boolean isConfirmed();
	JTextPane getTextPane();
	void setVisible(boolean visible);
}
