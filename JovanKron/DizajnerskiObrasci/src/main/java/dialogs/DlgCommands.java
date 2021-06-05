package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
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
import javax.swing.border.EmptyBorder;

public class DlgCommands extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private boolean confirmed;
	private JTextPane textPane;
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JButton btnNext, btnExit;

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
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent arg0) {
	        	setConfirmed(false);
				dispose();
	        }
	    });
		
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {0, 0, 0};
		gbl_contentPanel.rowHeights = new int[] {60, 0, 0, 28, 30, 37, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);

		contentPanel.setLayout(gbl_contentPanel);
		{
			buttonPanel.setBackground(Color.WHITE);
			getContentPane().add(buttonPanel, BorderLayout.SOUTH);
			{
				btnNext = new JButton("Next");
				btnNext.setBackground(Color.LIGHT_GRAY);
				btnNext.setForeground(new Color(105, 105, 105));
				btnNext.setFont(new Font("Arial", Font.BOLD, 13));
				btnNext.setPreferredSize(new Dimension(80, 30));
				buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				btnNext.setActionCommand("OK");
				buttonPanel.add(btnNext);
				getRootPane().setDefaultButton(btnNext);
				btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setConfirmed(true);
						dispose();
					}
				});
			}
			{
				btnExit = new JButton("Exit");
				btnExit.setBackground(Color.LIGHT_GRAY);
				btnExit.setFont(new Font("Arial", Font.BOLD, 13));
				btnExit.setForeground(new Color(105, 105, 105));
				btnExit.setPreferredSize(new Dimension(80, 30));
				btnExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setConfirmed(false);
						dispose();
					}
				});
				btnExit.setActionCommand("Cancel");
				buttonPanel.add(btnExit);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		textPane = new JTextPane();
		textPane.setFocusTraversalKeysEnabled(false);
		textPane.setFocusCycleRoot(false);
		textPane.setFocusable(false);
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	public JButton getBtnNext() {
		return btnNext;
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

}
