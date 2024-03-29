package mvc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class DrawingFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	private JToggleButton clickedButton, tglBtnNew, tglBtnSave, tglBtnLoad, tglBtnPoint,
				tglBtnLine, tglBtnCircle, tglBtnRectangle, tglBtnDonut, tglBtnHexagon,
				tglBtnSelect, tglBtnDelete, tglBtnModify, tglBtnEdgeColor, tglBtnFillColor,
				tglBtnUndo, tglBtnRedo, tglBtnBringToBack, tglBtnToBack, tglBtnToFront, tglBtnBringToFront;
	private JTextPane textPane;
	
	public DrawingFrame() {
		ButtonGroup group = new ButtonGroup();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		tglBtnSelect = new JToggleButton("Select");
		tglBtnSelect.setToolTipText("Select");
		tglBtnSelect.setMargin(new Insets(0, 0, 0, 0));
		tglBtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				controller.undo();
			}
		});
		group.add(tglBtnUndo);
		
		tglBtnLoad = new JToggleButton("Load");
		tglBtnLoad.setToolTipText("Load");
		tglBtnLoad.setMargin(new Insets(0, 0, 0, 0));
		tglBtnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.load();
			}
		});
		group.add(tglBtnLoad);
		
		tglBtnSave = new JToggleButton("Save");
		tglBtnSave.setToolTipText("Save");
		tglBtnSave.setMargin(new Insets(0, 0, 0, 0));
		tglBtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.save();
			}
		});
		group.add(tglBtnSave);
		
		tglBtnNew = new JToggleButton("New");
		tglBtnNew.setToolTipText("New");
		tglBtnNew.setMargin(new Insets(0, 0, 0, 0));
		tglBtnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.newPainting();
			}
		});
		group.add(tglBtnNew);
		panel_1.add(tglBtnNew);
		panel_1.add(tglBtnSave);
		panel_1.add(tglBtnLoad);
		panel_1.add(tglBtnUndo);
		
		tglBtnRedo = new JToggleButton("Redo");
		tglBtnRedo.setToolTipText("Redo");
		tglBtnRedo.setMargin(new Insets(0, 0, 0, 0));
		tglBtnRedo.setEnabled(false);
		tglBtnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.redo();
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
				String question = "Do you want to delete selected shapes?";
				int answer = JOptionPane.showConfirmDialog(null, question,"Warning",JOptionPane.YES_NO_OPTION);
				if(answer == JOptionPane.YES_OPTION)
					controller.deleteShapes();
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
				controller.bringToBack();
			}
		});
		panel_1.add(tglBtnBringToBack);
		
		tglBtnToBack = new JToggleButton("To back");
		tglBtnToBack.setToolTipText("To back");
		tglBtnToBack.setMargin(new Insets(0, 0, 0, 0));
		tglBtnToBack.setEnabled(false);
		tglBtnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		panel_1.add(tglBtnToBack);
		
		tglBtnToFront = new JToggleButton("To front");
		tglBtnToFront.setToolTipText("To front");
		tglBtnToFront.setMargin(new Insets(0, 0, 0, 0));
		tglBtnToFront.setEnabled(false);
		tglBtnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		panel_1.add(tglBtnToFront);
		
		tglBtnBringToFront = new JToggleButton("Bring to front");
		tglBtnBringToFront.setToolTipText("Bring to front");
		tglBtnBringToFront.setMargin(new Insets(0, 0, 0, 0));
		tglBtnBringToFront.setEnabled(false);
		tglBtnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		panel_1.add(tglBtnBringToFront);
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout(0, 0));
				
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tglBtnPoint = new JToggleButton("Point");
		tglBtnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.unselectAll();
				clickedButton = tglBtnPoint;
			}
		});
		panel.add(tglBtnPoint);
		group.add(tglBtnPoint);
		
		tglBtnLine = new JToggleButton("Line");
		tglBtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.unselectAll();
				controller.setFlagForLine(true);
				clickedButton = tglBtnLine;
			}
		});
		panel.add(tglBtnLine);
		group.add(tglBtnLine);
		
		tglBtnDonut = new JToggleButton("Donut");
		tglBtnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.unselectAll();
				clickedButton = tglBtnDonut;
			}
		});
		panel.add(tglBtnDonut);
		group.add(tglBtnDonut);
		
		tglBtnCircle = new JToggleButton("Circle");
		tglBtnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.unselectAll();
				clickedButton = tglBtnCircle;
			}
		});
		panel.add(tglBtnCircle);
		group.add(tglBtnCircle);
		
		tglBtnRectangle = new JToggleButton("Rectangle");
		tglBtnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.unselectAll();
				clickedButton = tglBtnRectangle;
				
			}
		});
		panel.add(tglBtnRectangle);
		group.add(tglBtnRectangle);
		
		tglBtnHexagon = new JToggleButton("Hexagon");
		tglBtnHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.unselectAll();
				clickedButton = tglBtnHexagon;
			}
		});
		panel.add(tglBtnHexagon);
		group.add(tglBtnHexagon);
		
		JLabel lblEdge = new JLabel("Edge");
		panel.add(lblEdge);
		
		tglBtnEdgeColor = new JToggleButton("             ");
		tglBtnEdgeColor.setToolTipText("Edge color");
		tglBtnEdgeColor.setBackground(Color.BLACK);
		tglBtnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.chooseEdgeColor();
				tglBtnEdgeColor.setBackground(controller.getCurrentEdgeColor());
			}
		});
		panel.add(tglBtnEdgeColor);
		group.add(tglBtnEdgeColor);
		
		JLabel lblSurface = new JLabel("Surface");
		panel.add(lblSurface);
		
		tglBtnFillColor = new JToggleButton("              ");
		tglBtnFillColor.setToolTipText("Surface color");
		tglBtnFillColor.setBackground(Color.YELLOW);
		tglBtnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.chooseFillColor();
				tglBtnFillColor.setBackground(controller.getCurrentFillColor());
			}
		});
		panel.add(tglBtnFillColor);
		group.add(tglBtnFillColor);
		
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
				if(clickedButton == tglBtnPoint)
					controller.addPointOnClick(click);
				else if(clickedButton == tglBtnLine)
					controller.addLineOnClick(click);
				else if(clickedButton == tglBtnCircle)
					controller.addCircleOnClick(click);
				else if(clickedButton == tglBtnRectangle)
					controller.addRectangleOnClick(click);
				else if(clickedButton == tglBtnDonut)
					controller.addDonutOnClick(click);
				else if(clickedButton == tglBtnHexagon)
					controller.addHexagonOnClick(click);
				else if(clickedButton == tglBtnSelect)
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
	
	public void setAllShapeManipultationButtonsState(boolean areActive) {
		getTglBtnDelete().setEnabled(areActive);
		getTglBtnModify().setEnabled(areActive);
		getTglBtnBringToBack().setEnabled(areActive);
		getTglBtnToBack().setEnabled(areActive);
		getTglBtnToFront().setEnabled(areActive);
		getTglBtnBringToFront().setEnabled(areActive);
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

	public JToggleButton getClickedButton() {
		return clickedButton;
	}

	public JToggleButton getTglBtnNew() {
		return tglBtnNew;
	}

	public JToggleButton getTglBtnSave() {
		return tglBtnSave;
	}

	public JToggleButton getTglBtnLoad() {
		return tglBtnLoad;
	}

	public JToggleButton getTglBtnPoint() {
		return tglBtnPoint;
	}

	public JToggleButton getTglBtnLine() {
		return tglBtnLine;
	}

	public JToggleButton getTglBtnCircle() {
		return tglBtnCircle;
	}

	public JToggleButton getTglBtnRectangle() {
		return tglBtnRectangle;
	}

	public JToggleButton getTglBtnDonut() {
		return tglBtnDonut;
	}

	public JToggleButton getTglBtnHexagon() {
		return tglBtnHexagon;
	}

	public JToggleButton getTglBtnSelect() {
		return tglBtnSelect;
	}

	public JToggleButton getTglBtnDelete() {
		return tglBtnDelete;
	}

	public JToggleButton getTglBtnModify() {
		return tglBtnModify;
	}

	public JToggleButton getTglBtnEdgeColor() {
		return tglBtnEdgeColor;
	}

	public JToggleButton getTglBtnFillColor() {
		return tglBtnFillColor;
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
