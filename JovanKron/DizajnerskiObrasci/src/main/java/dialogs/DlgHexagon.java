package dialogs;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DlgHexagon extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtY;
    private JTextField txtX;
    private JTextField txtRadius;
    private boolean isOk;
    private JButton saveButton;
    private JButton cancelButton;
    private Color edgeColor;
    private Color fillColor;

    public Color getEdgeColor() {
        return edgeColor;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public String getTxtY() {
        return txtY.getText();
    }

    public void setTxtY(String txtY) {
        this.txtY.setText(txtY);
    }

    public String getTxtX() {
        return txtX.getText();
    }

    public void setTxtX(String txtX) {
        this.txtX.setText(txtX);
    }

    public void setTxtXEdit(boolean b) {
        this.txtX.setEditable(b);
    }

    public void setTxtYEdit(boolean b) {
        this.txtY.setEditable(b);
    }

    public boolean isOk() {
        return isOk;
    }

    public String getTxtRadius() {
        return txtRadius.getText();
    }

    public void setTxtRadius(String textField) {
        this.txtRadius.setText(textField);
    }
    
    public JButton getSaveButton() {
		return saveButton;
	}
	
	public JButton getCancelButton() {
		return cancelButton;
	}

    public static void main(String[] args) {
        try {
            DlgCircle dialog = new DlgCircle();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgHexagon() {

        setTitle("Hexagon");
        setBounds(100, 100, 312, 391);
        getContentPane().setLayout(new BorderLayout());
        this.setModal(true);
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Dimension d = new Dimension(80,30);

        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblCenter = new JLabel("Center");
        lblCenter.setFont(new Font("Arial", Font.PLAIN, 21));

        JLabel lblX = new JLabel("X coordinate:");
        lblX.setHorizontalAlignment(SwingConstants.CENTER);
        lblX.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblY = new JLabel("Y coordinate:");
        lblY.setHorizontalAlignment(SwingConstants.CENTER);
        lblY.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblRadius = new JLabel("Radius:");
        lblRadius.setHorizontalAlignment(SwingConstants.CENTER);
        lblRadius.setFont(new Font("Arial", Font.PLAIN, 20));

        txtX = new JTextField();
        txtX.setFont(new Font("Tahoma", Font.PLAIN, 20));
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

        txtY = new JTextField();
        txtY.setFont(new Font("Tahoma", Font.PLAIN, 20));
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

        txtRadius = new JTextField();
        txtRadius.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtRadius.setColumns(10);
        txtRadius.addKeyListener(new KeyAdapter() {
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



        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
        	gl_contentPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPanel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPanel.createSequentialGroup()
        					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(lblRadius, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(lblY, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(lblX, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
        					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtX, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtY, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtRadius, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
        					.addGap(104))
        				.addGroup(gl_contentPanel.createSequentialGroup()
        					.addComponent(lblCenter, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(170, Short.MAX_VALUE))))
        );
        gl_contentPanel.setVerticalGroup(
        	gl_contentPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPanel.createSequentialGroup()
        			.addGap(18)
        			.addComponent(lblCenter, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblX, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtX, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblY, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtY, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        			.addGap(35)
        			.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblRadius)
        				.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(92, Short.MAX_VALUE))
        );

        contentPanel.setLayout(gl_contentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                saveButton = new JButton("Save");
                saveButton.setBackground(Color.LIGHT_GRAY);
                saveButton.setForeground(new Color(105, 105, 105));
                saveButton.setFont(new Font("Arial", Font.BOLD, 17));
                saveButton.setPreferredSize(d);
                saveButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        if (txtX.getText().isEmpty() ||
                                txtY.getText().isEmpty() ||
                                txtRadius.getText().isEmpty()) {
                            isOk = false;
                            setVisible(true);
                            JOptionPane.showMessageDialog(null, "All fields must be filled.", "Error!", JOptionPane.WARNING_MESSAGE);
                        } else {
                            isOk=true;
                            dispose();
                        }
                    }
                });
                saveButton.setActionCommand("OK");
                getRootPane().setDefaultButton(saveButton);
            }
            {
                cancelButton = new JButton("Cancel");
                cancelButton.setBackground(Color.LIGHT_GRAY);
                cancelButton.setFont(new Font("Arial", Font.BOLD, 17));
                cancelButton.setForeground(new Color(105, 105, 105));
                cancelButton.setPreferredSize(d);
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
            }
            buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            buttonPane.add(saveButton);
            buttonPane.add(cancelButton);
        }
    }
}
