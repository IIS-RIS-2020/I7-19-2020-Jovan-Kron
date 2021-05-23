package dialogs;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;

public class DlgLine extends DlgShape {

	private static final long serialVersionUID = 1L;
	private JTextField txtEndX;
	private JTextField txtEndY;
	private int endX, endY;

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
		setTitle("Line dialog");
		defineSaveOperation();
		setBounds(100, 100, 180, 280);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPanel().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{17, 0, 0, 0, 0, 0};
		JLabel lblStartPoint = new JLabel("Start point");
		GridBagConstraints gbc_lblStartPoint = new GridBagConstraints();
		gbc_lblStartPoint.gridwidth = 3;
		gbc_lblStartPoint.insets = new Insets(0, 0, 5, 0);
		gbc_lblStartPoint.gridx = 0;
		gbc_lblStartPoint.gridy = 0;
		getContentPanel().add(lblStartPoint, gbc_lblStartPoint);
		
		GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
		gbc_btnEdgeColor.gridwidth = 2;
		gbc_btnEdgeColor.fill = GridBagConstraints.BOTH;
		gbc_btnEdgeColor.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdgeColor.gridx = 1;
		gbc_btnEdgeColor.gridy = 7;
		getContentPanel().add(getBtnEdgeColor(), gbc_btnEdgeColor);
		
		GridBagConstraints gbc_lblEdgeColor = new GridBagConstraints();
		gbc_lblEdgeColor.anchor = GridBagConstraints.EAST;
		gbc_lblEdgeColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdgeColor.gridx = 0;
		gbc_lblEdgeColor.gridy = 7;
		getContentPanel().add(getLblEdgeColor(), gbc_lblEdgeColor);
		
		
		
		JLabel lblEndPoint = new JLabel("End point");
		GridBagConstraints gbc_lblEndPoint = new GridBagConstraints();
		gbc_lblEndPoint.gridwidth = 3;
		gbc_lblEndPoint.insets = new Insets(0, 0, 5, 0);
		gbc_lblEndPoint.gridx = 0;
		gbc_lblEndPoint.gridy = 3;
		getContentPanel().add(lblEndPoint, gbc_lblEndPoint);
		
		JLabel lblCoordinateXEnd = new JLabel("Coordinate X:");
		GridBagConstraints gbc_lblCoordinateXEnd = new GridBagConstraints();
		gbc_lblCoordinateXEnd.anchor = GridBagConstraints.EAST;
		gbc_lblCoordinateXEnd.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoordinateXEnd.gridx = 0;
		gbc_lblCoordinateXEnd.gridy = 4;
		getContentPanel().add(lblCoordinateXEnd, gbc_lblCoordinateXEnd);
		
		txtEndX = new JTextField();
		GridBagConstraints gbc_txtCoordinateXEnd = new GridBagConstraints();
		gbc_txtCoordinateXEnd.gridwidth = 2;
		gbc_txtCoordinateXEnd.insets = new Insets(0, 0, 5, 0);
		gbc_txtCoordinateXEnd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCoordinateXEnd.gridx = 1;
		gbc_txtCoordinateXEnd.gridy = 4;
		getContentPanel().add(txtEndX, gbc_txtCoordinateXEnd);
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
		getContentPanel().add(lblCoordinateYEnd, gbc_lblCoordinateYEnd);
		
		txtEndY = new JTextField();
		GridBagConstraints gbc_txtCoordinateYEnd = new GridBagConstraints();
		gbc_txtCoordinateYEnd.gridwidth = 2;
		gbc_txtCoordinateYEnd.insets = new Insets(0, 0, 5, 0);
		gbc_txtCoordinateYEnd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCoordinateYEnd.gridx = 1;
		gbc_txtCoordinateYEnd.gridy = 5;
		getContentPanel().add(txtEndY, gbc_txtCoordinateYEnd);
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
	}
	
	public void fillForAdd(int startX, int startY, int endX, int endY, Color edgeColor)
	{
		fillForAdd(startX, startY, edgeColor);
		setEndX(endX);
		setEndY(endY);
		txtEndX.setText(Integer.toString(endX));
		txtEndY.setText(Integer.toString(endY));
		txtEndX.setEditable(false);
		txtEndY.setEditable(false);
	}
	
	public void fillForModify(int startX, int startY, int endX, int endY, Color edgeColor)
	{
		fillForModify(startX, startY, edgeColor);
		setEndX(endX);
		setEndY(endY);
		txtEndX.setText(Integer.toString(endX));
		txtEndY.setText(Integer.toString(endY));
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

	@Override
	public void defineSaveOperation() {
		getSaveButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (getTxtX().getText().isEmpty() ||
						getTxtY().getText().isEmpty() || 
						txtEndX.getText().isEmpty() ||
						txtEndY.getText().isEmpty()) {
					setConfirmed(false);
					JOptionPane.showMessageDialog(null, "All fields must be filled.", "Error!", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						setBaseCoordinateX(Integer.parseInt(getTxtX().getText()));
						setBaseCoordinateY(Integer.parseInt(getTxtY().getText()));
						endX = Integer.parseInt(txtEndX.getText());
						endY = Integer.parseInt(txtEndY.getText());
						if(getBaseCoordinateX()<0 || getBaseCoordinateY()<0 || endX<0 || endY<0)
							JOptionPane.showMessageDialog(null,"Entered values must be greater than 0");
						else {
							setConfirmed(true);
							dispose();
						}
					}
					catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"Fault in entering values for numbers");
					}
				}
			}
		});
	}

}
