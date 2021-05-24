package dialogs;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import java.awt.GridBagLayout;

public abstract class DlgSurfaceShape extends DlgShape {
	
	private static final long serialVersionUID = 1L;
	private Color fillColor;
	private JButton btnFillColor;
	private JLabel lblFillColor;
	
	public DlgSurfaceShape() {
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPanel().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 30};
		
		setBounds(100, 100, 198, 300);
				
		lblFillColor = new JLabel("Fill color:");
		GridBagConstraints gbc_lblFillColor = new GridBagConstraints();
		gbc_lblFillColor.anchor = GridBagConstraints.EAST;
		gbc_lblFillColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblFillColor.gridx = 0;
		gbc_lblFillColor.gridy = 5;
		getContentPanel().add(lblFillColor, gbc_lblFillColor);
		
		btnFillColor = new JButton("");
		btnFillColor.setBackground(getFillColor());
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		 		Color temp = JColorChooser.showDialog(null, "Choose fill color", getFillColor());
				if(temp != null){
					setFillColor(temp);
					btnFillColor.setBackground(getFillColor());
				}
			}
		});
		
		GridBagConstraints gbc_btnFillColor = new GridBagConstraints();
		gbc_btnFillColor.fill = GridBagConstraints.BOTH;
		gbc_btnFillColor.gridwidth = 3;
		gbc_btnFillColor.insets = new Insets(0, 0, 5, 0);
		gbc_btnFillColor.gridx = 1;
		gbc_btnFillColor.gridy = 5;
		getContentPanel().add(btnFillColor, gbc_btnFillColor);
	}
	
	public void fillForAdd(int x, int y, Color edgeColor, Color fillColor) {
		super.fillForAdd(x, y, edgeColor);
		setFillColor(fillColor);
		getBtnFillColor().setBackground(fillColor);
	}
	
	public void fillForModify(int x, int y, Color edgeColor, Color fillColor)
	{
		super.fillForModify(x, y, edgeColor);
		setFillColor(fillColor);
		getBtnFillColor().setBackground(fillColor);
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public JButton getBtnFillColor() {
		return btnFillColor;
	}

	public void setBtnFillColor(JButton btnFillColor) {
		this.btnFillColor = btnFillColor;
	}

	public JLabel getLblFillColor() {
		return lblFillColor;
	}

	public void setLblFillColor(JLabel lblFillColor) {
		this.lblFillColor = lblFillColor;
	}

}
