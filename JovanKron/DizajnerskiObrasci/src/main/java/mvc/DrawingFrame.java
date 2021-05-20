package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JOptionPane;

public class DrawingFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	public JToggleButton clickedButton;
	JToggleButton tglBtnDelete;
	JToggleButton tglBtnModify;
	JToggleButton tglBtnUndo;
	JToggleButton tglBtnRedo;
	private JToggleButton tglBtnBringToBack;
	private JToggleButton tglBtnToBack;
	private JToggleButton tglBtnToFront;
	private JToggleButton tglBtnBringToFront;
	private JTextPane textPane;
	
	public DrawingFrame() {
		
		ButtonGroup group = new ButtonGroup();
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JToggleButton tglBtnSelect = new JToggleButton("Select");
		tglBtnSelect.setToolTipText("Select");
		tglBtnSelect.setMargin(new Insets(0, 0, 0, 0));
		tglBtnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.unselectAll();
				clickedButton = tglBtnSelect;
			}
		});
		group.add(tglBtnSelect);
		
		tglBtnUndo = new JToggleButton("Undo");
		tglBtnUndo.setToolTipText("Undo");
		tglBtnUndo.setMargin(new Insets(0, 0, 0, 0));
		tglBtnUndo.setEnabled(false);
		tglBtnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
		});
		
		group.add(tglBtnUndo);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setToolTipText("Load");
		btnLoad.setMargin(new Insets(0, 0, 0, 0));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.setToolTipText("Save");
		btnSave.setMargin(new Insets(0, 0, 0, 0));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		
		JButton btnNew = new JButton("New");
		btnNew.setToolTipText("New");
		btnNew.setMargin(new Insets(0, 0, 0, 0));
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		panel_1.add(btnNew);
		panel_1.add(btnSave);
		panel_1.add(btnLoad);
		panel_1.add(tglBtnUndo);
		
		tglBtnRedo = new JToggleButton("Redo");
		tglBtnRedo.setToolTipText("Redo");
		tglBtnRedo.setMargin(new Insets(0, 0, 0, 0));
		tglBtnRedo.setEnabled(false);
		tglBtnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
		});
		panel_1.add(tglBtnRedo);
		panel_1.add(tglBtnSelect);
		
		tglBtnDelete = new JToggleButton("Delete");
		tglBtnDelete.setToolTipText("Delete");
		tglBtnDelete.setMargin(new Insets(0, 0, 0, 0));
		tglBtnDelete.setEnabled(false);
		tglBtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog (null, "Do you want to delete selected shapes?","Warning",JOptionPane.YES_NO_OPTION);
				if(answer == JOptionPane.YES_OPTION) {
					controller.deleteShapes();
				}
			}
		});
		
		tglBtnModify = new JToggleButton("Modify");
		tglBtnModify.setToolTipText("Modify");
		tglBtnModify.setMargin(new Insets(0, 0, 0, 0));
		tglBtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.modifyShape();
			}
		});
		tglBtnModify.setEnabled(false);
		panel_1.add(tglBtnModify);
		
		panel_1.add(tglBtnDelete);
		
		tglBtnBringToBack = new JToggleButton("Bring to back");
		tglBtnBringToBack.setToolTipText("Bring to back");
		tglBtnBringToBack.setMargin(new Insets(0, 0, 0, 0));
		tglBtnBringToBack.setEnabled(false);
		tglBtnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.positionCommand("BTB");
			}
		});
		panel_1.add(tglBtnBringToBack);
		
		tglBtnToBack = new JToggleButton("To back");
		tglBtnToBack.setToolTipText("To back");
		tglBtnToBack.setMargin(new Insets(0, 0, 0, 0));
		tglBtnToBack.setEnabled(false);
		tglBtnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.positionCommand("TB");
			}
		});
		panel_1.add(tglBtnToBack);
		
		tglBtnToFront = new JToggleButton("To front");
		tglBtnToFront.setToolTipText("To front");
		tglBtnToFront.setMargin(new Insets(0, 0, 0, 0));
		tglBtnToFront.setEnabled(false);
		tglBtnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.positionCommand("TF");
			}
		});
		panel_1.add(tglBtnToFront);
		
		tglBtnBringToFront = new JToggleButton("Bring to front");
		tglBtnBringToFront.setToolTipText("Bring to front");
		tglBtnBringToFront.setMargin(new Insets(0, 0, 0, 0));
		tglBtnBringToFront.setEnabled(false);
		tglBtnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.positionCommand("BTF");
			}
		});
		panel_1.add(tglBtnBringToFront);
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout(0, 0));
				
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		JToggleButton btnPoint = new JToggleButton("Point");
		btnPoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.unselectAll();
				clickedButton = btnPoint;
			}
		});
		panel.add(btnPoint);
		group.add(btnPoint);
		
		JToggleButton btnLine = new JToggleButton("Line");
		btnLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.unselectAll();
				clickedButton = btnLine;
			}
		});
		panel.add(btnLine);
		group.add(btnLine);
		
		JToggleButton btnDonut = new JToggleButton("Donut");
		btnDonut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.unselectAll();
				clickedButton = btnDonut;
			}
		});
		panel.add(btnDonut);
		group.add(btnDonut);
		
		JToggleButton btnCirle = new JToggleButton("Circle");
		btnCirle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.unselectAll();
				clickedButton = btnCirle;
			}
		});
		panel.add(btnCirle);
		group.add(btnCirle);
		
		JToggleButton btnRectangle = new JToggleButton("Rectangle");
		btnRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.unselectAll();
				clickedButton = btnRectangle;
				
			}
		});
		panel.add(btnRectangle);
		group.add(btnRectangle);
		
		JToggleButton btnHexagon = new JToggleButton("Hexagon");
		btnHexagon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.unselectAll();
				clickedButton = btnHexagon;
			}
		});
		panel.add(btnHexagon);
		group.add(btnHexagon);
		
		JLabel lblEdge = new JLabel("Edge");
		panel.add(lblEdge);
		
		JToggleButton btnEdgeColor = new JToggleButton("             ");
		btnEdgeColor.setToolTipText("Edge color");
		btnEdgeColor.setBackground(Color.BLACK);
		btnEdgeColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.chooseEdgeColor();
				btnEdgeColor.setBackground(controller.getCurrentEdgeColor());
				clickedButton = btnEdgeColor;
			}
		});
		panel.add(btnEdgeColor);
		group.add(btnEdgeColor);
		
		JLabel lblSurface = new JLabel("Surface");
		panel.add(lblSurface);
		
		JToggleButton btnFillColor = new JToggleButton("              ");
		btnFillColor.setToolTipText("Surface color");
		btnFillColor.setBackground(Color.YELLOW);
		btnFillColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.chooseFillColor();
				btnFillColor.setBackground(controller.getCurrentFillColor());
				clickedButton = btnFillColor;
			}
		});
		panel.add(btnFillColor);
		group.add(btnFillColor);
		
		JSplitPane southPanel = new JSplitPane();
		southPanel.setEnabled(false);
		southPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);

		JScrollPane logPanel = new JScrollPane();
		logPanel.setPreferredSize(new Dimension(southPanel.getWidth(),120));
		
		southPanel.setTopComponent(panel);
		southPanel.setBottomComponent(logPanel);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		logPanel.setViewportView(textPane);
		
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		setBounds(100, 100, 1100, 650);
		setBackground(new Color(192, 192, 192));
		
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if(clickedButton==btnPoint) {
					controller.addPointOnClick(click);
				}
				else if(clickedButton==btnLine)
					controller.addLineOnClick(click);
				else if(clickedButton==btnCirle)
					controller.addCircleOnClick(click);
				else if(clickedButton==btnRectangle)
					controller.addRectangleOnClick(click);
				else if(clickedButton==btnDonut)
					controller.addDonutOnClick(click);
				else if(clickedButton==btnHexagon)
					controller.addHexagonOnClick(click);
				else if(clickedButton==tglBtnSelect)
					controller.selectShape(click);
			}
		});
	}
	
	public void appendToLogPanel(String command) {
		StyledDocument document = (StyledDocument) textPane.getDocument();
		try {
			document.insertString(document.getLength(), command, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;

	}
	
	public DrawingController getController() {
		return controller;
	}

	public JToggleButton getTglBtnDelete() {
		return tglBtnDelete;
	}

	public JToggleButton getTglBtnModify() {
		return tglBtnModify;
	}

	public JToggleButton getTglBtnUndo() {
		return tglBtnUndo;
	}

	public JToggleButton getTglBtnRedo() {
		return tglBtnRedo;
	}

	public JToggleButton getTglBtnBringToBack() {
		return tglBtnBringToBack;
	}

	public JToggleButton getTglBtnToBack() {
		return tglBtnToBack;
	}

	public JToggleButton getTglBtnToFront() {
		return tglBtnToFront;
	}

	public JToggleButton getTglBtnBringToFront() {
		return tglBtnBringToFront;
	}

	public JTextPane getLogPanel() {
		return textPane;
	}
}
