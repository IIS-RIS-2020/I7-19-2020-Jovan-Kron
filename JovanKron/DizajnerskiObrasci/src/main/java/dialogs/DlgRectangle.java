package dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import geometry.Rectangle;

public class DlgRectangle extends DlgSurfaceShape {
	private static final long serialVersionUID = 1L;
	private int width, height;
	private JTextField txtWidth, txtHeight;

	public DlgRectangle() {
		setTitle("Rectangle dialog");
		defineSaveOperation();
		setBounds(100, 100, 198, 310);
		getBtnEdgeColor().setMinimumSize(new Dimension(33, 25));
		getBtnFillColor().setMinimumSize(new Dimension(33, 25));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JLabel lblNewLabel = new JLabel("Upper left corner coordinates");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 10, 0);
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
		
		JLabel lblHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.EAST;
		gbc_lblHeight.insets = new Insets(0, 0, 0, 5);
		gbc_lblHeight.gridx = 0;
		gbc_lblHeight.gridy = 3;
		getContentPanel().add(lblHeight, gbc_lblHeight);
		
		txtHeight = new JTextField();
		GridBagConstraints gbc_txtHeight = new GridBagConstraints();
		gbc_txtHeight.insets = new Insets(0, 0, 5, 0);
		gbc_txtHeight.fill = GridBagConstraints.BOTH;
		gbc_txtHeight.gridwidth = 3;
		gbc_txtHeight.gridx = 1;
		gbc_txtHeight.gridy = 3;
		getContentPanel().add(txtHeight, gbc_txtHeight);
		txtHeight.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyTyped(KeyEvent e) {
		    	checkInputText(e);
		    }
		});
		
		JLabel lblWidth = new JLabel("Width:");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.anchor = GridBagConstraints.EAST;
		gbc_lblWidth.insets = new Insets(0, 0, 0, 5);
		gbc_lblWidth.gridx = 0;
		gbc_lblWidth.gridy = 4;
		getContentPanel().add(lblWidth, gbc_lblWidth);
		
		txtWidth = new JTextField();
		GridBagConstraints gbc_txtWidth = new GridBagConstraints();
		gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
		gbc_txtWidth.fill = GridBagConstraints.BOTH;
		gbc_txtWidth.gridwidth = 3;
		gbc_txtWidth.gridx = 1;
		gbc_txtWidth.gridy = 4;
		getContentPanel().add(txtWidth, gbc_txtWidth);
		txtWidth.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyTyped(KeyEvent e) {
		    	checkInputText(e);
		    }
		});
		
		GridBagConstraints gbc_lblEdgeColor = new GridBagConstraints();
		gbc_lblEdgeColor.anchor = GridBagConstraints.EAST;
		gbc_lblEdgeColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdgeColor.gridx = 0;
		gbc_lblEdgeColor.gridy = 5;
		getContentPanel().add(getLblEdgeColor(), gbc_lblEdgeColor);
		
		GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
		gbc_btnEdgeColor.gridwidth = 3;
		gbc_btnEdgeColor.fill = GridBagConstraints.BOTH;
		gbc_btnEdgeColor.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdgeColor.gridx = 1;
		gbc_btnEdgeColor.gridy = 5;
		getContentPanel().add(getBtnEdgeColor(), gbc_btnEdgeColor);
		
		GridBagConstraints gbc_lblFillColor = new GridBagConstraints();
		gbc_lblFillColor.anchor = GridBagConstraints.EAST;
		gbc_lblFillColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblFillColor.gridx = 0;
		gbc_lblFillColor.gridy = 6;
		getContentPanel().add(getLblFillColor(), gbc_lblFillColor);
		
		GridBagConstraints gbc_btnFillColor = new GridBagConstraints();
		gbc_btnFillColor.gridwidth = 3;
		gbc_btnFillColor.fill = GridBagConstraints.BOTH;
		gbc_btnFillColor.insets = new Insets(0, 0, 5, 0);
		gbc_btnFillColor.gridx = 1;
		gbc_btnFillColor.gridy = 6;
		getContentPanel().add(getBtnFillColor(), gbc_btnFillColor);
		
	}

	protected void defineSaveOperation() {
		getSaveButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					readBaseCoordinates();
					width = Integer.parseInt(txtWidth.getText());
					height = Integer.parseInt(txtHeight.getText());
					if(getBaseCoordinateX() < 0 || getBaseCoordinateY() < 0 || width < 1 || height < 1)
						JOptionPane.showMessageDialog(null,"Entered values must be greater than 0 and 1 for width/height!");
					else {
						setConfirmed(true);
						dispose();
					}
				}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,"Fault in entering values for numbers");
				}
			}
		});
	}
	
	public void fillForModify(Rectangle rectangle) {
		fillForModify(rectangle.getUpperLeftPoint(), rectangle.getEdgeColor(), rectangle.getFillColor());
		txtWidth.setText(Integer.toString(rectangle.getWidth()));
		txtHeight.setText(Integer.toString(rectangle.getHeight()));
	}
	
	public Rectangle createRectangleFromInput() {
		return new Rectangle(createBasePointFromInput(), width, height, getEdgeColor(), getFillColor());
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

}
