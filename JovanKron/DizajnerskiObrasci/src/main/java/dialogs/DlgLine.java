package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

public class DlgLine extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartX;
	private JTextField txtStartY;
	private JTextField txtEndX;
	private JTextField txtEndY;
	private boolean isOk;
	private int startX, startY, endX, endY;
	private Color edgeColor = Color.black;
	private JButton btnEdgeColor, saveButton, cancelButton;

	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgLine() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 198, 300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setTitle("Line dialog");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {85, 82, 0};
		gbl_contentPanel.rowHeights = new int[] {80, 0, 0, 0, 0, 0, 19, 28, 58, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel lblStartPoint = new JLabel("Start point");
		GridBagConstraints gbc_lblStartPoint = new GridBagConstraints();
		gbc_lblStartPoint.gridheight = 3;
		gbc_lblStartPoint.gridwidth = 2;
		gbc_lblStartPoint.insets = new Insets(0, 0, 5, 0);
		gbc_lblStartPoint.gridx = 0;
		gbc_lblStartPoint.gridy = 0;
		contentPanel.add(lblStartPoint, gbc_lblStartPoint);
		
		JLabel lblCoordinateXStart = new JLabel("Coordinate X:");
		GridBagConstraints gbc_lblCoordinateXStart = new GridBagConstraints();
		gbc_lblCoordinateXStart.anchor = GridBagConstraints.EAST;
		gbc_lblCoordinateXStart.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoordinateXStart.gridx = 0;
		gbc_lblCoordinateXStart.gridy = 1;
		contentPanel.add(lblCoordinateXStart, gbc_lblCoordinateXStart);
		
		txtStartX = new JTextField();
		GridBagConstraints gbc_txtCoordinateXStart = new GridBagConstraints();
		gbc_txtCoordinateXStart.insets = new Insets(0, 0, 5, 0);
		gbc_txtCoordinateXStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCoordinateXStart.gridx = 1;
		gbc_txtCoordinateXStart.gridy = 1;
		contentPanel.add(txtStartX, gbc_txtCoordinateXStart);
		txtStartX.setColumns(10);
		txtStartX.addKeyListener(new KeyAdapter() {
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
		
		JLabel lblCoordinateYStart = new JLabel("Coordinate Y:");
		GridBagConstraints gbc_lblCoordinateYStart = new GridBagConstraints();
		gbc_lblCoordinateYStart.anchor = GridBagConstraints.EAST;
		gbc_lblCoordinateYStart.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoordinateYStart.gridx = 0;
		gbc_lblCoordinateYStart.gridy = 2;
		contentPanel.add(lblCoordinateYStart, gbc_lblCoordinateYStart);
		
		txtStartY = new JTextField();
		GridBagConstraints gbc_txtCoordinateYStart = new GridBagConstraints();
		gbc_txtCoordinateYStart.insets = new Insets(0, 0, 5, 0);
		gbc_txtCoordinateYStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCoordinateYStart.gridx = 1;
		gbc_txtCoordinateYStart.gridy = 2;
		contentPanel.add(txtStartY, gbc_txtCoordinateYStart);
		txtStartY.setColumns(10);
		txtStartY.addKeyListener(new KeyAdapter() {
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
		
		JLabel lblEndPoint = new JLabel("End point");
		GridBagConstraints gbc_lblEndPoint = new GridBagConstraints();
		gbc_lblEndPoint.gridwidth = 2;
		gbc_lblEndPoint.insets = new Insets(0, 0, 5, 0);
		gbc_lblEndPoint.gridx = 0;
		gbc_lblEndPoint.gridy = 3;
		contentPanel.add(lblEndPoint, gbc_lblEndPoint);
		
		JLabel lblCoordinateXEnd = new JLabel("Coordinate X:");
		GridBagConstraints gbc_lblCoordinateXEnd = new GridBagConstraints();
		gbc_lblCoordinateXEnd.anchor = GridBagConstraints.EAST;
		gbc_lblCoordinateXEnd.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoordinateXEnd.gridx = 0;
		gbc_lblCoordinateXEnd.gridy = 4;
		contentPanel.add(lblCoordinateXEnd, gbc_lblCoordinateXEnd);
		
		txtEndX = new JTextField();
		GridBagConstraints gbc_txtCoordinateXEnd = new GridBagConstraints();
		gbc_txtCoordinateXEnd.insets = new Insets(0, 0, 5, 0);
		gbc_txtCoordinateXEnd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCoordinateXEnd.gridx = 1;
		gbc_txtCoordinateXEnd.gridy = 4;
		contentPanel.add(txtEndX, gbc_txtCoordinateXEnd);
		txtEndX.setColumns(10);
		txtEndX.addKeyListener(new KeyAdapter() {
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
		
		JLabel lblCoordinateYEnd = new JLabel("Coordinate Y:");
		GridBagConstraints gbc_lblCoordinateYEnd = new GridBagConstraints();
		gbc_lblCoordinateYEnd.anchor = GridBagConstraints.EAST;
		gbc_lblCoordinateYEnd.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoordinateYEnd.gridx = 0;
		gbc_lblCoordinateYEnd.gridy = 5;
		contentPanel.add(lblCoordinateYEnd, gbc_lblCoordinateYEnd);
		
		txtEndY = new JTextField();
		GridBagConstraints gbc_txtCoordinateYEnd = new GridBagConstraints();
		gbc_txtCoordinateYEnd.insets = new Insets(0, 0, 5, 0);
		gbc_txtCoordinateYEnd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCoordinateYEnd.gridx = 1;
		gbc_txtCoordinateYEnd.gridy = 5;
		contentPanel.add(txtEndY, gbc_txtCoordinateYEnd);
		txtEndY.setColumns(10);
		txtEndY.addKeyListener(new KeyAdapter() {
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
		
		JLabel lblNewLabel = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 6;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblEdgeColor = new JLabel("Edge color:");
		GridBagConstraints gbc_lblEdgeColor = new GridBagConstraints();
		gbc_lblEdgeColor.anchor = GridBagConstraints.EAST;
		gbc_lblEdgeColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdgeColor.gridx = 0;
		gbc_lblEdgeColor.gridy = 7;
		contentPanel.add(lblEdgeColor, gbc_lblEdgeColor);
		
	    btnEdgeColor = new JButton("");
	    btnEdgeColor.setBackground(edgeColor);
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp= JColorChooser.showDialog(null, "Choose edge color", edgeColor);
	 			if(temp!=null){
	 				edgeColor=temp;
	 				btnEdgeColor.setBackground(edgeColor);
	 			}
			}
		});
		GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
		gbc_btnEdgeColor.fill = GridBagConstraints.BOTH;
		gbc_btnEdgeColor.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdgeColor.gridx = 1;
		gbc_btnEdgeColor.gridy = 7;
		contentPanel.add(btnEdgeColor, gbc_btnEdgeColor);
		
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				saveButton = new JButton("Save");
				saveButton.setBackground(Color.LIGHT_GRAY);
				saveButton.setForeground(new Color(105, 105, 105));
				saveButton.setFont(new Font("Arial", Font.BOLD, 12));
				saveButton.setPreferredSize(new Dimension(80, 30));
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtStartX.getText().isEmpty() ||
								txtStartY.getText().isEmpty() || 
								txtEndX.getText().isEmpty() ||
								txtEndY.getText().isEmpty()) {
							isOk = false;
							JOptionPane.showMessageDialog(null, "All fields must be filled.", "Error!", JOptionPane.WARNING_MESSAGE);
						} else {
							try {
								startX = Integer.parseInt(txtStartX.getText());
								startY = Integer.parseInt(txtStartY.getText());
								endX = Integer.parseInt(txtEndX.getText());
								endY = Integer.parseInt(txtEndY.getText());
								if(startX<0||startY<0||endX<0||endY<0)
									JOptionPane.showMessageDialog(null,"Entered values must be greater than 0");
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
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 5));
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
	
	public void fillForAdd(int startX, int startY, int endX, int endY, Color edgeColor)
	{
		setStartX(startX);
		setStartY(startY);
		setEndX(endX);
		setEndY(endY);
		setEdgeColor(edgeColor);
		txtStartX.setText(Integer.toString(startX));
		txtStartY.setText(Integer.toString(startY));
		txtEndX.setText(Integer.toString(endX));
		txtEndY.setText(Integer.toString(endY));
		txtStartX.setEditable(false);
		txtStartY.setEditable(false);
		txtEndX.setEditable(false);
		txtEndY.setEditable(false);
		btnEdgeColor.setBackground(edgeColor);
	}
	
	public void fillForModify(int startX, int startY, int endX, int endY, Color edgeColor)
	{
		setStartX(startX);
		setStartY(startY);
		setEndX(endX);
		setEndY(endY);
		setEdgeColor(edgeColor);
		txtStartX.setText(Integer.toString(startX));
		txtStartY.setText(Integer.toString(startY));
		txtEndX.setText(Integer.toString(endX));
		txtEndY.setText(Integer.toString(endY));
		btnEdgeColor.setBackground(edgeColor);
	}
	
	public boolean isOk() {
		return isOk;
	}

	public JButton getSaveButton() {
		return saveButton;
	}
	
	public JButton getCancelButton() {
		return cancelButton;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}
	
	

}
