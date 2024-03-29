package dialogs;

import javax.swing.*;
import geometry.Point;
import geometry.Line;
import java.awt.*;
import java.awt.event.*;

public class DlgLine extends DlgShape {
	private static final long serialVersionUID = 1L;
	private JTextField txtEndX, txtEndY;
	private int endX, endY;

	public DlgLine() {
		setTitle("Line dialog");
		defineSaveOperation();
		setBounds(100, 100, 198, 320);
		getBtnEdgeColor().setMinimumSize(new Dimension(33, 25));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPanel().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{17, 0, 0, 0, 0, 0};
		JLabel lblStartPoint = new JLabel("Start point");
		GridBagConstraints gbc_lblStartPoint = new GridBagConstraints();
		gbc_lblStartPoint.gridwidth = 4;
		gbc_lblStartPoint.insets = new Insets(0, 0, 5, 0);
		gbc_lblStartPoint.gridx = 0;
		gbc_lblStartPoint.gridy = 0;
		getContentPanel().add(lblStartPoint, gbc_lblStartPoint);
		
		GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
		gbc_btnEdgeColor.gridwidth = 3;
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
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 4;
		gbc_separator.insets = new Insets(10, 0, 10, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		getContentPanel().add(separator, gbc_separator);
		
		JLabel lblEndPoint = new JLabel("End point");
		GridBagConstraints gbc_lblEndPoint = new GridBagConstraints();
		gbc_lblEndPoint.gridwidth = 4;
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
		gbc_txtCoordinateXEnd.gridwidth = 3;
		gbc_txtCoordinateXEnd.insets = new Insets(0, 0, 5, 0);
		gbc_txtCoordinateXEnd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCoordinateXEnd.gridx = 1;
		gbc_txtCoordinateXEnd.gridy = 4;
		getContentPanel().add(txtEndX, gbc_txtCoordinateXEnd);
		txtEndX.setColumns(10);
		txtEndX.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		    	checkInputText(e);
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
		gbc_txtCoordinateYEnd.gridwidth = 3;
		gbc_txtCoordinateYEnd.insets = new Insets(0, 0, 5, 0);
		gbc_txtCoordinateYEnd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCoordinateYEnd.gridx = 1;
		gbc_txtCoordinateYEnd.gridy = 5;
		getContentPanel().add(txtEndY, gbc_txtCoordinateYEnd);
		txtEndY.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 4;
		gbc_separator_1.insets = new Insets(10, 0, 10, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 6;
		getContentPanel().add(separator_1, gbc_separator_1);
		txtEndY.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyTyped(KeyEvent e) {
		    	checkInputText(e);
		    }
		});
	}

	protected void defineSaveOperation() {
		getSaveButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					readBaseCoordinates();
					endX = Integer.parseInt(txtEndX.getText());
					endY = Integer.parseInt(txtEndY.getText());
					if(getBaseCoordinateX() < 0 || getBaseCoordinateY() < 0 || endX < 0 || endY < 0)
						getOptionPane().showMessageDialog(null, "Entered values must be greater than 0");
					else {
						setConfirmed(true);
						dispose();
					}
				}
				catch (NumberFormatException e1) {
					getOptionPane().showMessageDialog(null, "Fault in entering values for numbers");
				}
			}
		});
	}
	
	public void fillForAdd(Point basePoint, Point endPoint, Color edgeColor) {
		fillForAdd(basePoint, edgeColor);
		txtEndX.setText(Integer.toString(endPoint.getX()));
		txtEndY.setText(Integer.toString(endPoint.getY()));
		txtEndX.setEditable(false);
		txtEndY.setEditable(false);
	}
	
	public void fillForModify(Line line) {
		fillForModify(line.getStartPoint(), line.getEdgeColor());
		txtEndX.setText(Integer.toString(line.getEndPoint().getX()));
		txtEndY.setText(Integer.toString(line.getEndPoint().getY()));
	}
	
	public Line createLineFromInput() {
		return new Line(createBasePointFromInput(), new Point(endX, endY), getEdgeColor());
	}

	public JTextField getTxtEndX() {
		return txtEndX;
	}

	public JTextField getTxtEndY() {
		return txtEndY;
	}
	
}
