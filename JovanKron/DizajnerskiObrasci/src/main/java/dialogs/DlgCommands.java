package dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class DlgCommands extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private boolean confirmed;
	JTextPane textPane;

	public static void main(String[] args) {
		try {
			DlgCommands dialog = new DlgCommands();
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
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent arg0) {
	        	setConfirmed(false);
				dispose();
	        }
	    });
	    
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton btnNext = new JButton("Next");
		getRootPane().setDefaultButton(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setConfirmed(true);
				dispose();
			}
		});
		panel.add(btnNext);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setConfirmed(false);
				dispose();
			}
		});
		panel.add(btnExit);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

}
