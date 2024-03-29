package dialogs;

import javax.swing.*;
import geometry.Circle;
import java.awt.*;
import java.awt.event.*;

public class DlgCircle extends DlgSurfaceShape {
	private static final long serialVersionUID = 1L;
	private int radius;
	private JTextField txtRadius;

	public DlgCircle() {
		setTitle("Circle dialog");
		defineSaveOperation();
		setBounds(100, 100, 198, 275);
		getBtnEdgeColor().setMinimumSize(new Dimension(33, 25));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JLabel lblNewLabel = new JLabel("Center coordinates");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPanel().add(lblNewLabel, gbc_lblNewLabel);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 4;
		gbc_separator.insets = new Insets(10, 0, 15, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		getContentPanel().add(separator, gbc_separator);
		
		JLabel lblRaduis = new JLabel("Radius:");
		GridBagConstraints gbc_lblFillColor = new GridBagConstraints();
		gbc_lblFillColor.anchor = GridBagConstraints.EAST;
		gbc_lblFillColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblFillColor.gridx = 0;
		gbc_lblFillColor.gridy = 3;
		getContentPanel().add(lblRaduis, gbc_lblFillColor);
		
		txtRadius = new JTextField();
		GridBagConstraints gbc_txtRaduis = new GridBagConstraints();
		gbc_txtRaduis.fill = GridBagConstraints.BOTH;
		gbc_txtRaduis.gridwidth = 3;
		gbc_txtRaduis.insets = new Insets(0, 0, 5, 0);
		gbc_txtRaduis.gridx = 1;
		gbc_txtRaduis.gridy = 3;
		getContentPanel().add(txtRadius, gbc_txtRaduis);
		txtRadius.addKeyListener(new KeyAdapter() {
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
					radius = Integer.parseInt(txtRadius.getText());
					if(getBaseCoordinateX() < 0 || getBaseCoordinateY() < 0 || radius < 1)
						getOptionPane().showMessageDialog(null, "Entered values must be greater than 0 and 1 for radius!");
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
	
	public void fillForModify(Circle circle) {
		fillForModify(circle.getCenter(), circle.getEdgeColor(), circle.getFillColor());
		txtRadius.setText(Integer.toString(circle.getRadius()));
	}
	
	public Circle createCircleFromInput() {
		return new Circle(createBasePointFromInput(), radius, getEdgeColor(), getFillColor());
	}

	public int getRadius() {
		return radius;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}
	
}
