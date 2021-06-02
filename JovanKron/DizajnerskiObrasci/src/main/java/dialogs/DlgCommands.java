package dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class DlgCommands extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private int choise=0;
	JTextPane textPane;

	public static void main(String[] args) {
		try {
			DlgCommands dialog = new DlgCommands();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgCommands() {
		setModal(true);
		setBounds(100, 100, 650, 261);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle("Log commands dialog");
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choise = 0;
				dispose();
			}
		});
		panel.add(btnExit);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choise = 1;
				dispose();
			}
		});
		panel.add(btnNext);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
	}

	public int getChoise() {
		return choise;
	}

	public void setChoise(int choise) {
		this.choise = choise;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

}
