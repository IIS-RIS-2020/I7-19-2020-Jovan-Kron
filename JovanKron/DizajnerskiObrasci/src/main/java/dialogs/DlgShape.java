package dialogs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import geometry.Point;

public abstract class DlgShape extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JTextField txtX, txtY;
	private int baseCoordinateX, baseCoordinateY;
	private Color edgeColor;
	private boolean confirmed;
	private JButton btnEdgeColor, saveButton, cancelButton;
	private JLabel lblEdgeColor;
	
	public DlgShape() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 198, 224);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(getContentPanel(), BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {0, 0, 0};
		gbl_contentPanel.rowHeights = new int[] {60, 0, 0, 28, 30, 37, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPanel().setLayout(gbl_contentPanel);
		
		JLabel lblCoordinateX = new JLabel("Coordinate x:");
		GridBagConstraints gbc_lblCoordinateX = new GridBagConstraints();
		gbc_lblCoordinateX.anchor = GridBagConstraints.EAST;
		gbc_lblCoordinateX.insets = new Insets(50, 0, 5, 5);
		gbc_lblCoordinateX.gridx = 0;
		gbc_lblCoordinateX.gridy = 0;
		getContentPanel().add(lblCoordinateX, gbc_lblCoordinateX);
	
		txtX = new JTextField();
		GridBagConstraints gbc_txtCoordinateX = new GridBagConstraints();
		gbc_txtCoordinateX.gridwidth = 3;
		gbc_txtCoordinateX.insets = new Insets(50, 0, 5, 0);
		gbc_txtCoordinateX.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCoordinateX.gridx = 1;
		gbc_txtCoordinateX.gridy = 0;
		getContentPanel().add(txtX, gbc_txtCoordinateX);
		txtX.setColumns(10);
		txtX.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyTyped(KeyEvent e) {
		    	checkInputText(e);
		    }
		});
	
		JLabel lblCoordinateY = new JLabel("Coordinate y:");
		GridBagConstraints gbc_lblCoordinateY = new GridBagConstraints();
		gbc_lblCoordinateY.anchor = GridBagConstraints.EAST;
		gbc_lblCoordinateY.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoordinateY.gridx = 0;
		gbc_lblCoordinateY.gridy = 1;
		getContentPanel().add(lblCoordinateY, gbc_lblCoordinateY);
	
		txtY = new JTextField();
		GridBagConstraints gbc_txtCoordinateY = new GridBagConstraints();
		gbc_txtCoordinateY.gridwidth = 3;
		gbc_txtCoordinateY.insets = new Insets(0, 0, 5, 0);
		gbc_txtCoordinateY.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCoordinateY.gridx = 1;
		gbc_txtCoordinateY.gridy = 1;
		getContentPanel().add(txtY, gbc_txtCoordinateY);
		txtY.setColumns(10);
		txtY.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyTyped(KeyEvent e) {
		      checkInputText(e);
		    }
		});
	
		lblEdgeColor = new JLabel("Edge color:");
		GridBagConstraints gbc_lblEdgeColor = new GridBagConstraints();
		gbc_lblEdgeColor.anchor = GridBagConstraints.EAST;
		gbc_lblEdgeColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdgeColor.gridx = 0;
		gbc_lblEdgeColor.gridy = 4;
		getContentPanel().add(lblEdgeColor, gbc_lblEdgeColor);
	
		btnEdgeColor = new JButton("");
		btnEdgeColor.setBackground(getEdgeColor());
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		 		Color temp = JColorChooser.showDialog(null, "Choose edge color", getEdgeColor());
				if(temp != null){
					setEdgeColor(temp);
					btnEdgeColor.setBackground(getEdgeColor());
				}
			}
		});
		
		GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
		gbc_btnEdgeColor.gridwidth = 3;
		gbc_btnEdgeColor.fill = GridBagConstraints.BOTH;
		gbc_btnEdgeColor.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdgeColor.gridx = 1;
		gbc_btnEdgeColor.gridy = 4;
		contentPanel.add(btnEdgeColor, gbc_btnEdgeColor);
			
		getContentPanel().setLayout(gbl_contentPanel);
		
		buttonPanel.setBackground(Color.WHITE);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
			
		saveButton = new JButton("Save");
		saveButton.setBackground(Color.LIGHT_GRAY);
		saveButton.setForeground(new Color(105, 105, 105));
		saveButton.setFont(new Font("Arial", Font.BOLD, 13));
		saveButton.setPreferredSize(new Dimension(80, 30));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		saveButton.setActionCommand("OK");
		buttonPanel.add(saveButton);
		getRootPane().setDefaultButton(saveButton);
	
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
		buttonPanel.add(cancelButton);
	}
	
	public void fillForAdd(Point point, Color edgeColor) {
		fillForModify(point, edgeColor);
		getTxtX().setEditable(false);
		getTxtY().setEditable(false);
	}
	
	public void fillForModify(Point point, Color edgeColor) {
		getTxtX().setText(Integer.toString(point.getX()));
		getTxtY().setText(Integer.toString(point.getY()));
		setEdgeColor(edgeColor);
		getBtnEdgeColor().setBackground(edgeColor);
	}
	
	public Point createBasePointFromInput() {
		readBaseCoordinates();
		return new Point(baseCoordinateX, baseCoordinateY, edgeColor);
	}
	
	public void readBaseCoordinates() {
		baseCoordinateX = Integer.parseInt(getTxtX().getText());
		baseCoordinateY = Integer.parseInt(getTxtY().getText());
	}
	
	public void checkInputText(KeyEvent e) {
		char c = e.getKeyChar();
		if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
				getToolkit().beep();
				e.consume();
		}
	}
	
	protected abstract void defineSaveOperation();
	
	public JLabel getLblEdgeColor() {
		return lblEdgeColor;
	}	

	public JButton getSaveButton() {
		return saveButton;
	}
	
	public JButton getCancelButton() {
		return cancelButton;
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public int getBaseCoordinateX() {
		return baseCoordinateX;
	}

	public int getBaseCoordinateY() {
		return baseCoordinateY;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}
	
}
