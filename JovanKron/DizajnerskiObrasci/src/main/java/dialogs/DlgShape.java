package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public abstract class DlgShape extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private int baseCoordinateX;
	private int baseCoordinateY;
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
			{
				JLabel lblCoordinateX = new JLabel("Coordinate x:");
				GridBagConstraints gbc_lblCoordinateX = new GridBagConstraints();
				gbc_lblCoordinateX.anchor = GridBagConstraints.EAST;
				gbc_lblCoordinateX.insets = new Insets(0, 0, 5, 5);
				gbc_lblCoordinateX.gridx = 0;
				gbc_lblCoordinateX.gridy = 1;
				getContentPanel().add(lblCoordinateX, gbc_lblCoordinateX);
			}
			{
				txtX = new JTextField();
				GridBagConstraints gbc_txtCoordinateX = new GridBagConstraints();
				gbc_txtCoordinateX.gridwidth = 2;
				gbc_txtCoordinateX.insets = new Insets(0, 0, 5, 0);
				gbc_txtCoordinateX.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtCoordinateX.gridx = 1;
				gbc_txtCoordinateX.gridy = 1;
				getContentPanel().add(txtX, gbc_txtCoordinateX);
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
				getContentPanel().add(lblCoordinateY, gbc_lblCoordinateY);
			}
			{
				txtY = new JTextField();
				GridBagConstraints gbc_txtCoordinateY = new GridBagConstraints();
				gbc_txtCoordinateY.gridwidth = 2;
				gbc_txtCoordinateY.insets = new Insets(0, 0, 5, 0);
				gbc_txtCoordinateY.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtCoordinateY.gridx = 1;
				gbc_txtCoordinateY.gridy = 2;
				getContentPanel().add(txtY, gbc_txtCoordinateY);
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
				lblEdgeColor = new JLabel("Edge color:");
				GridBagConstraints gbc_lblEdgeColor = new GridBagConstraints();
				gbc_lblEdgeColor.anchor = GridBagConstraints.EAST;
				gbc_lblEdgeColor.insets = new Insets(0, 0, 5, 5);
				gbc_lblEdgeColor.gridx = 0;
				gbc_lblEdgeColor.gridy = 3;
				getContentPanel().add(lblEdgeColor, gbc_lblEdgeColor);
			}
			{
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
				gbc_btnEdgeColor.gridwidth = 2;
				gbc_btnEdgeColor.fill = GridBagConstraints.BOTH;
				gbc_btnEdgeColor.insets = new Insets(0, 0, 5, 0);
				gbc_btnEdgeColor.gridx = 1;
				gbc_btnEdgeColor.gridy = 3;
				contentPanel.add(btnEdgeColor, gbc_btnEdgeColor);
			}
			
			
			
			getContentPanel().setLayout(gbl_contentPanel);
			{
				getButtonPanel().setBackground(Color.WHITE);
				getContentPane().add(getButtonPanel(), BorderLayout.SOUTH);
				{
					saveButton = new JButton("Save");
					saveButton.setBackground(Color.LIGHT_GRAY);
					saveButton.setForeground(new Color(105, 105, 105));
					saveButton.setFont(new Font("Arial", Font.BOLD, 13));
					saveButton.setPreferredSize(new Dimension(80, 30));
					getButtonPanel().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					saveButton.setActionCommand("OK");
					getButtonPanel().add(saveButton);
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
					buttonPanel.add(cancelButton);
				}
			}
	}
	
	public void fillForAdd(int x, int y, Color edgeColor) {
		setBaseCoordinateX(x);
		setBaseCoordinateY(y);
		setEdgeColor(edgeColor);
		getTxtX().setText(Integer.toString(x));
		getTxtY().setText(Integer.toString(y));
		getTxtX().setEditable(false);
		getTxtY().setEditable(false);
		getBtnEdgeColor().setBackground(edgeColor);
	}
	
	public void fillForModify(int x, int y, Color edgeColor)
	{
		setBaseCoordinateX(x);
		setBaseCoordinateY(y);
		setEdgeColor(edgeColor);
		getTxtX().setText(Integer.toString(x));
		getTxtY().setText(Integer.toString(y));
		getBtnEdgeColor().setBackground(edgeColor);
	}
	
	public abstract void defineSaveOperation();
	
	public JLabel getLblEdgeColor() {
		return lblEdgeColor;
	}

	public JButton getSaveButton() {
		return saveButton;
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

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPane) {
		this.buttonPanel = buttonPane;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public void setBtnEdgeColor(JButton btnEdgeColor) {
		this.btnEdgeColor = btnEdgeColor;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public int getBaseCoordinateX() {
		return baseCoordinateX;
	}

	public void setBaseCoordinateX(int baseCoordinateX) {
		this.baseCoordinateX = baseCoordinateX;
	}

	public int getBaseCoordinateY() {
		return baseCoordinateY;
	}

	public void setBaseCoordinateY(int baseCoordinateY) {
		this.baseCoordinateY = baseCoordinateY;
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
