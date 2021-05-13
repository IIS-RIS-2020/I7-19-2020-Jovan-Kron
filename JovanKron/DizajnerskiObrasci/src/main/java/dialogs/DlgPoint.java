package dialogs;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DlgPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private boolean isOk;
	private Color c = Color.BLACK;
	JButton saveButton;
	JButton cancelButton;
	
	public void setTxtXEdit(boolean b){
		txtX.setEditable(b);
	}
	
	public void setTxtYEdit(boolean b){
		txtY.setEditable(b);
	}

	public String getTxtX() {
		return txtX.getText();
	}

	public void setTxtX(String txtX) {
		this.txtX.setText(txtX);
	}

	public String getTxtY() {
		return txtY.getText();
	}

	public void setTxtY(String txtY) {
		this.txtY.setText(txtY);
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean ok) {
		this.isOk = ok;
	}
	
	public Color getColor() {
		return c;
	}
	
	public void setColor(Color color) {
		c = color;
	}
	
	public JButton getSaveButton() {
		return saveButton;
	}
	
	public JButton getCancelButton() {
		return cancelButton;
	}

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
		
		setTitle("Point");
		setBounds(100, 100, 297, 321);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		Dimension d = new Dimension(80,30);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblX = new JLabel("X coordinate:");
		lblX.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblY = new JLabel("Y coordinate:");
		lblY.setFont(new Font("Arial", Font.PLAIN, 20));
		
		txtX = new JTextField();
		txtX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtX.setColumns(10);
		txtX.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE))) {
		        getToolkit().beep();
		        e.consume();
		      }
		    }
		  });
		
		txtY = new JTextField();
		txtY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtY.setColumns(10);
		txtY.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE))) {
		        getToolkit().beep();
		        e.consume();
		      }
		    }
		  });

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblX)
						.addComponent(lblY))
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtY, 0, 0, Short.MAX_VALUE)
						.addComponent(txtX, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(106, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				saveButton = new JButton("Save");
				saveButton.setBackground(Color.LIGHT_GRAY);
				saveButton.setForeground(new Color(105, 105, 105));
				saveButton.setFont(new Font("Arial", Font.BOLD, 17));
				saveButton.setPreferredSize(d);
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtX.getText().isEmpty() ||
								txtY.getText().isEmpty()) {
							isOk = false;
							setVisible(true);
							JOptionPane.showMessageDialog(null, "All fields must be filled.", "Error!", JOptionPane.WARNING_MESSAGE);
						} else {
						isOk=true;
						dispose(); 
						}
					}
				});
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBackground(Color.LIGHT_GRAY);
				cancelButton.setFont(new Font("Arial", Font.BOLD, 17));
				cancelButton.setForeground(new Color(105, 105, 105));
				cancelButton.setPreferredSize(d);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
