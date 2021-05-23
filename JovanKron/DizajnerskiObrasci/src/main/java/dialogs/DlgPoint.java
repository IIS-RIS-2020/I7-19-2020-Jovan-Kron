package dialogs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DlgPoint extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private boolean isOk;
	private int x, y;
	private Color edgeColor = Color.black;
	private JButton btnEdgeColor, saveButton, cancelButton;

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
		setModal(true);
		setBounds(100, 100, 198, 224);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setTitle("Point dialog");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {0, 0, 0};
		gbl_contentPanel.rowHeights = new int[] {60, 0, 0, 28, 30, 37, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCoordinateX = new JLabel("Coordinate x:");
			GridBagConstraints gbc_lblCoordinateX = new GridBagConstraints();
			gbc_lblCoordinateX.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateX.gridx = 0;
			gbc_lblCoordinateX.gridy = 1;
			contentPanel.add(lblCoordinateX, gbc_lblCoordinateX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtCoordinateX = new GridBagConstraints();
			gbc_txtCoordinateX.gridwidth = 2;
			gbc_txtCoordinateX.insets = new Insets(0, 0, 5, 0);
			gbc_txtCoordinateX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCoordinateX.gridx = 1;
			gbc_txtCoordinateX.gridy = 1;
			contentPanel.add(txtX, gbc_txtCoordinateX);
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
		}
		{
			JLabel lblCoordinateY = new JLabel("Coordinate y:");
			GridBagConstraints gbc_lblCoordinateY = new GridBagConstraints();
			gbc_lblCoordinateY.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateY.gridx = 0;
			gbc_lblCoordinateY.gridy = 2;
			contentPanel.add(lblCoordinateY, gbc_lblCoordinateY);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtCoordinateY = new GridBagConstraints();
			gbc_txtCoordinateY.gridwidth = 2;
			gbc_txtCoordinateY.insets = new Insets(0, 0, 5, 0);
			gbc_txtCoordinateY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCoordinateY.gridx = 1;
			gbc_txtCoordinateY.gridy = 2;
			contentPanel.add(txtY, gbc_txtCoordinateY);
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
		}
		{
			JLabel lblEdgeColor = new JLabel("Edge color:");
			GridBagConstraints gbc_lblEdgeColor = new GridBagConstraints();
			gbc_lblEdgeColor.anchor = GridBagConstraints.EAST;
			gbc_lblEdgeColor.insets = new Insets(0, 0, 5, 5);
			gbc_lblEdgeColor.gridx = 0;
			gbc_lblEdgeColor.gridy = 3;
			contentPanel.add(lblEdgeColor, gbc_lblEdgeColor);
		}
		{
			btnEdgeColor = new JButton("");
			btnEdgeColor.setBackground(edgeColor);
			btnEdgeColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
			 		Color temp = JColorChooser.showDialog(null, "Choose edge color", edgeColor);
					if(temp != null){
						setEdgeColor(temp);
						btnEdgeColor.setBackground(edgeColor);
					}
				}
			});
			
			GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
			gbc_btnEdgeColor.gridwidth = 2;
			gbc_btnEdgeColor.fill = GridBagConstraints.BOTH;
			gbc_btnEdgeColor.insets = new Insets(0, 0, 5, 0);
			gbc_btnEdgeColor.gridx = 1;
			gbc_btnEdgeColor.gridy = 3;
			contentPanel.add(btnEdgeColor, gbc_btnEdgeColor);
		}
		
		
		
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				saveButton = new JButton("Save");
				saveButton.setBackground(Color.LIGHT_GRAY);
				saveButton.setForeground(new Color(105, 105, 105));
				saveButton.setFont(new Font("Arial", Font.BOLD, 13));
				saveButton.setPreferredSize(new Dimension(80, 30));
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtX.getText().isEmpty() || txtY.getText().isEmpty()) {
							isOk = false;
							JOptionPane.showMessageDialog(null, "All fields must be filled.", "Error!", JOptionPane.WARNING_MESSAGE);
						} else {
							try {
								x = Integer.parseInt(txtX.getText());
								y = Integer.parseInt(txtY.getText());
								if(x<0 || y<0)
									JOptionPane.showMessageDialog(null,"Entered values must be greater than 0!");
								else {
									isOk=true;
									dispose();
								}
							}
							catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(null,"Fault in entering values for numbers");
							}
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
				cancelButton.setFont(new Font("Arial", Font.BOLD, 13));
				cancelButton.setForeground(new Color(105, 105, 105));
				cancelButton.setPreferredSize(new Dimension(80, 30));
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
	
	public void fillForAdd(int x, int y, Color edgeColor) {
		setX(x);
		setY(y);
		setEdgeColor(edgeColor);
		txtX.setText(Integer.toString(x));
		txtY.setText(Integer.toString(y));
		txtX.setEditable(false);
		txtY.setEditable(false);
		btnEdgeColor.setBackground(edgeColor);
	}
	
	public void fillForModify(int x, int y, Color edgeColor)
	{
		setX(x);
		setY(y);
		setEdgeColor(edgeColor);
		txtX.setText(Integer.toString(x));
		txtY.setText(Integer.toString(y));
		btnEdgeColor.setBackground(edgeColor);
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
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}
}
