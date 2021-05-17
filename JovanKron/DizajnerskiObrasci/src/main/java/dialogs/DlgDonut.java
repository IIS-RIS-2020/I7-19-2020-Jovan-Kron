package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtInner;
	private JTextField txtOuter;
	private boolean isOk;
	private JButton saveButton;
	private JButton cancelButton;
	private Color edgeColor;
	private Color fillColor;

	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	

	public String getTxtInner() {
		return txtInner.getText();
	}

	public void setTxtInner(String txtInner) {
		this.txtInner.setText(txtInner);
	}

	public String getTxtOuter() {
		return txtOuter.getText();
	}

	public void setTxtOuter(String txtOuter) {
		this.txtOuter.setText(txtOuter);
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
	
	public void setTxtXEdit(boolean b)
	{
		this.txtX.setEditable(b);
	}
	
	public void setTxtYEdit(boolean b)
	{
		this.txtY.setEditable(b);
	}
	
	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean ok) {
		this.isOk = ok;
	}
	
	public JButton getSaveButton() {
		return saveButton;
	}
	
	public JButton getCancelButton() {
		return cancelButton;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
		
		setBounds(100, 100, 360, 492);
		setTitle("Donut");
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		Dimension d = new Dimension(80,30);
		
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCenter = new JLabel("Center");
		lblCenter.setHorizontalAlignment(SwingConstants.CENTER);
		lblCenter.setFont(new Font("Arial", Font.PLAIN, 20));
		
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
		
		JLabel lblRadius = new JLabel("Radius");
		lblRadius.setHorizontalAlignment(SwingConstants.CENTER);
		lblRadius.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblInner = new JLabel("Inner:");
		lblInner.setHorizontalAlignment(SwingConstants.CENTER);
		lblInner.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblOuter = new JLabel("Outer:");
		lblOuter.setHorizontalAlignment(SwingConstants.CENTER);
		lblOuter.setFont(new Font("Arial", Font.PLAIN, 20));
		
		txtInner = new JTextField();
		txtInner.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtInner.setColumns(10);
		txtInner.addKeyListener(new KeyAdapter() {
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
		
		txtOuter = new JTextField();
		txtOuter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtOuter.setColumns(10);
		txtOuter.addKeyListener(new KeyAdapter() {
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
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblCenter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblRadius, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblX, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblY, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblOuter, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(40)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtOuter, 0, 0, Short.MAX_VALUE)
						.addComponent(txtInner, 0, 0, Short.MAX_VALUE)
						.addComponent(txtY, 0, 0, Short.MAX_VALUE)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(58, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(31)
					.addComponent(lblCenter)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addComponent(lblRadius, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInner, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtInner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtOuter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOuter, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(80, Short.MAX_VALUE))
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
								txtY.getText().isEmpty() ||
								txtInner.getText().isEmpty() ||
								txtOuter.getText().isEmpty()) {
							isOk = false;
							//setVisible(true);
							JOptionPane.showMessageDialog(null, "All fields must be filled.", "Error!", JOptionPane.WARNING_MESSAGE);
						} else {
						isOk=true;
						dispose(); 
						}
					}
				});
				saveButton.setActionCommand("OK");
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
			}
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			buttonPane.add(saveButton);
			buttonPane.add(cancelButton);
		}
	}
}
