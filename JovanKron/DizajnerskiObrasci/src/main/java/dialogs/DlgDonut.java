package dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DlgDonut extends DlgSurfaceShape {
	private static final long serialVersionUID = 1L;
	private int innerRadius, outerRadius;
	private JTextField txtInner, txtOuter;

	public DlgDonut() {
		setTitle("Donut dialog");
		defineSaveOperation();
		setBounds(100, 100, 198, 310);
		getBtnEdgeColor().setMinimumSize(new Dimension(33, 25));
		getBtnFillColor().setMinimumSize(new Dimension(33, 25));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JLabel lblNewLabel = new JLabel("Center coordinates");
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
		
		JLabel lblInnerRaduis = new JLabel("Inner radius:");
		GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
		gbc_lblInnerRadius.anchor = GridBagConstraints.EAST;
		gbc_lblInnerRadius.insets = new Insets(0, 0, 0, 5);
		gbc_lblInnerRadius.gridx = 0;
		gbc_lblInnerRadius.gridy = 3;
		getContentPanel().add(lblInnerRaduis, gbc_lblInnerRadius);
		
		txtInner = new JTextField();
		GridBagConstraints gbc_txtInnerRaduis = new GridBagConstraints();
		gbc_txtInnerRaduis.insets = new Insets(0, 0, 5, 0);
		gbc_txtInnerRaduis.fill = GridBagConstraints.BOTH;
		gbc_txtInnerRaduis.gridwidth = 3;
		gbc_txtInnerRaduis.gridx = 1;
		gbc_txtInnerRaduis.gridy = 3;
		getContentPanel().add(txtInner, gbc_txtInnerRaduis);
		txtInner.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		    	checkInputText(e);
		    }
		});
		
		JLabel lblOuterRaduis = new JLabel("Outer radius:");
		GridBagConstraints gbc_lblOuter = new GridBagConstraints();
		gbc_lblOuter.anchor = GridBagConstraints.EAST;
		gbc_lblOuter.insets = new Insets(0, 0, 0, 5);
		gbc_lblOuter.gridx = 0;
		gbc_lblOuter.gridy = 4;
		getContentPanel().add(lblOuterRaduis, gbc_lblOuter);
		
		txtOuter = new JTextField();
		GridBagConstraints gbc_txtOuterRadius = new GridBagConstraints();
		gbc_txtOuterRadius.insets = new Insets(0, 0, 5, 0);
		gbc_txtOuterRadius.fill = GridBagConstraints.BOTH;
		gbc_txtOuterRadius.gridwidth = 3;
		gbc_txtOuterRadius.gridx = 1;
		gbc_txtOuterRadius.gridy = 4;
		getContentPanel().add(txtOuter, gbc_txtOuterRadius);
		txtOuter.addKeyListener(new KeyAdapter() {
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

	@Override
	protected void defineSaveOperation() {
		getSaveButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					readBaseCoordinates();
					innerRadius = Integer.parseInt(txtInner.getText());
					outerRadius = Integer.parseInt(txtOuter.getText());
					if(getBaseCoordinateX() < 0 || getBaseCoordinateY() < 0 || innerRadius < 1 || outerRadius <1 )
						JOptionPane.showMessageDialog(null,"Entered values must be greater than 0 and 1 for inner/outer radius!");
					else if(innerRadius >= outerRadius)
						JOptionPane.showMessageDialog(null,"Inner radius must be smaller than outer by at least 1!");
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
	
	public void fillForModify(int startX, int startY, int innerRadius, int outerRadius, Color edgeColor, Color fillColor) {
		super.fillForModify(startX, startY, edgeColor, fillColor);
		txtInner.setText(Integer.toString(innerRadius));
		txtOuter.setText(Integer.toString(outerRadius));
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}

	public int getOuterRadius() {
		return outerRadius;
	}

	public JTextField getTxtInner() {
		return txtInner;
	}

	public JTextField getTxtOuter() {
		return txtOuter;
	}
	
}
