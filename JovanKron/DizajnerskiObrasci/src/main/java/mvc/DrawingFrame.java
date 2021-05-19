package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class DrawingFrame extends JFrame implements PropertyChangeListener {
	private DrawingView view = new DrawingView();
	private DrawingController controller;

    private JToggleButton tglbtnPoint;
    private JToggleButton tglbtnLine;
    private JToggleButton tglbtnRectangle;
    private JToggleButton tglbtnCircle;
    private JToggleButton tglbtnDonut;
    private JToggleButton tglbtnHexagon;
    private JToggleButton tglbtnSelect;
    private JToggleButton tglbtnModify;
    private JToggleButton tglbtnDelete;
    private JToggleButton tglbtnToFront;
    private JToggleButton tglbtnToBack;
    private JToggleButton tglbtnBringToFront;
    private JToggleButton tglbtnBringToBack;
    private JToggleButton tglbtnUndo;
    private JToggleButton tglbtnRedo;
    private JToggleButton tglbtnEdgeColor;
    private JToggleButton tglbtnFillColor;
    private JToggleButton tglbtnNext;
    private JToggleButton tglbtnSaveCommands;
    private JToggleButton tglbtnSaveShapes;
    private JToggleButton tglbtnOpenCommands;
    private JToggleButton tglbtnOpenShapes;
    private JList list;
    private ListModel<String> listModel;
    private JScrollPane scrollPane;
    
    public boolean isTglbtnPointSelected() {
        return tglbtnPoint.isSelected();
    }

    public boolean isTglbtnLineSelected() {
        return tglbtnLine.isSelected();
    }

    public boolean isTglbtnRectangleSelected() {
        return tglbtnRectangle.isSelected();
    }

    public boolean isTglbtnCircleSelected() {
        return tglbtnCircle.isSelected();
    }

    public boolean isTglbtnDonutSelected() {
        return tglbtnDonut.isSelected();
    }

    public boolean isTglbtnSelectSelected() {
        return tglbtnSelect.isSelected();
    }

    public boolean isTglbtnHexagonSelected() {
        return tglbtnHexagon.isSelected();
    }
	
	public DrawingFrame() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					controller.mouseClicked(arg0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		getContentPane().add(view, BorderLayout.CENTER);

        JPanel pnlNorth = new JPanel();
        getContentPane().add(pnlNorth, BorderLayout.NORTH);

        GridBagLayout gbl_pnlNorth = new GridBagLayout();
        pnlNorth.setLayout(gbl_pnlNorth);

        ButtonGroup group = new ButtonGroup();
        Dimension dimensionForButton = new Dimension(99,29);

        tglbtnPoint = new JToggleButton("Point");
        GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
        gbc_tglbtnPoint.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnPoint.gridx = 0;
        gbc_tglbtnPoint.gridy = 0;
        tglbtnPoint.setSelected(true);
        pnlNorth.add(tglbtnPoint, gbc_tglbtnPoint);
        tglbtnPoint.setPreferredSize(dimensionForButton);
        group.add(tglbtnPoint);

        tglbtnLine = new JToggleButton("Line");
        GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
        gbc_tglbtnLine.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnLine.gridx = 1;
        gbc_tglbtnLine.gridy = 0;
        pnlNorth.add(tglbtnLine, gbc_tglbtnLine);
        tglbtnLine.setPreferredSize(dimensionForButton);
        group.add(tglbtnLine);

        tglbtnRectangle = new JToggleButton("Rectangle");
        GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
        gbc_tglbtnRectangle.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnRectangle.gridx = 2;
        gbc_tglbtnRectangle.gridy = 0;
        pnlNorth.add(tglbtnRectangle, gbc_tglbtnRectangle);
        tglbtnRectangle.setPreferredSize(dimensionForButton);
        group.add(tglbtnRectangle);

        tglbtnCircle = new JToggleButton("Circle");
        GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
        gbc_tglbtnCircle.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnCircle.gridx = 3;
        gbc_tglbtnCircle.gridy = 0;
        pnlNorth.add(tglbtnCircle, gbc_tglbtnCircle);
        tglbtnCircle.setPreferredSize(dimensionForButton);
        group.add(tglbtnCircle);

        tglbtnDonut = new JToggleButton("Donut");
        GridBagConstraints gbc_tglbtnDonut = new GridBagConstraints();
        gbc_tglbtnDonut.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnDonut.gridx = 4;
        gbc_tglbtnDonut.gridy = 0;
        pnlNorth.add(tglbtnDonut, gbc_tglbtnDonut);
        tglbtnDonut.setPreferredSize(dimensionForButton);
        group.add(tglbtnDonut);

        tglbtnHexagon = new JToggleButton("Hexagon");
        GridBagConstraints gbc_tglbtnHexagon = new GridBagConstraints();
        gbc_tglbtnHexagon.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnHexagon.gridx = 5;
        gbc_tglbtnHexagon.gridy = 0;
        pnlNorth.add(tglbtnHexagon, gbc_tglbtnHexagon);
        tglbtnHexagon.setPreferredSize(dimensionForButton);
        group.add(tglbtnHexagon);

        tglbtnSelect = new JToggleButton("Select");
        tglbtnSelect.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
        gbc_tglbtnSelect.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnSelect.gridx = 6;
        gbc_tglbtnSelect.gridy = 0;
        pnlNorth.add(tglbtnSelect, gbc_tglbtnSelect);
        group.add(tglbtnSelect);

        tglbtnModify = new JToggleButton("Modify");
        tglbtnModify.setEnabled(false);
        tglbtnModify.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnModify = new GridBagConstraints();
        gbc_tglbtnModify.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnModify.gridx = 7;
        gbc_tglbtnModify.gridy = 0;
        pnlNorth.add(tglbtnModify, gbc_tglbtnModify);
        group.add(tglbtnModify);

        tglbtnDelete = new JToggleButton("Delete");
        tglbtnDelete.setEnabled(false);
        tglbtnDelete.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnDelete = new GridBagConstraints();
        gbc_tglbtnDelete.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnDelete.gridx = 8;
        gbc_tglbtnDelete.gridy = 0;
        pnlNorth.add(tglbtnDelete, gbc_tglbtnDelete);
        group.add(tglbtnDelete);

        tglbtnToFront = new JToggleButton("To Front");
        tglbtnToFront.setEnabled(false);
        tglbtnToFront.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnToFront = new GridBagConstraints();
        gbc_tglbtnToFront.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnToFront.gridx = 9;
        gbc_tglbtnToFront.gridy = 0;
        pnlNorth.add(tglbtnToFront, gbc_tglbtnToFront);
        group.add(tglbtnToFront);

        tglbtnToBack = new JToggleButton("To Back");
        tglbtnToBack.setEnabled(false);
        tglbtnToBack.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnToBack = new GridBagConstraints();
        gbc_tglbtnToBack.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnToBack.gridx = 10;
        gbc_tglbtnToBack.gridy = 0;
        pnlNorth.add(tglbtnToBack, gbc_tglbtnToBack);
        group.add(tglbtnToBack);

        tglbtnBringToFront = new JToggleButton("Bring To Front");
        tglbtnBringToFront.setEnabled(false);
        tglbtnBringToFront.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnBringToFront = new GridBagConstraints();
        gbc_tglbtnBringToFront.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnBringToFront.gridx = 11;
        gbc_tglbtnBringToFront.gridy = 0;
        pnlNorth.add(tglbtnBringToFront, gbc_tglbtnBringToFront);
        group.add(tglbtnBringToFront);

        tglbtnBringToBack = new JToggleButton("Bring To Back");
        tglbtnBringToBack.setEnabled(false);
        tglbtnBringToBack.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnBringToBack = new GridBagConstraints();
        gbc_tglbtnBringToBack.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnBringToBack.gridx = 12;
        gbc_tglbtnBringToBack.gridy = 0;
        pnlNorth.add(tglbtnBringToBack, gbc_tglbtnBringToBack);
        group.add(tglbtnBringToBack);

        tglbtnUndo = new JToggleButton("Undo");
        tglbtnUndo.setEnabled(false);
        tglbtnUndo.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnUndo = new GridBagConstraints();
        gbc_tglbtnUndo.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnUndo.gridx = 14;
        gbc_tglbtnUndo.gridy = 0;
        pnlNorth.add(tglbtnUndo, gbc_tglbtnUndo);
        group.add(tglbtnUndo);

        tglbtnRedo = new JToggleButton("Redo");
        tglbtnRedo.setEnabled(false);
        tglbtnRedo.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnRedo = new GridBagConstraints();
        gbc_tglbtnRedo.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnRedo.gridx = 15;
        gbc_tglbtnRedo.gridy = 0;
        pnlNorth.add(tglbtnRedo, gbc_tglbtnRedo);
        group.add(tglbtnRedo);

        tglbtnEdgeColor = new JToggleButton("Edge Color");
        tglbtnEdgeColor.setPreferredSize(dimensionForButton);
        tglbtnEdgeColor.setBackground(Color.BLACK);
        GridBagConstraints gbc_tglbtnEdgeColor = new GridBagConstraints();
        gbc_tglbtnEdgeColor.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnEdgeColor.gridx = 16;
        gbc_tglbtnEdgeColor.gridy = 0;
        pnlNorth.add(tglbtnEdgeColor, gbc_tglbtnEdgeColor);
        group.add(tglbtnEdgeColor);

        tglbtnFillColor = new JToggleButton("Fill Color");
        tglbtnFillColor.setPreferredSize(dimensionForButton);
        tglbtnFillColor.setBackground(Color.YELLOW);
        GridBagConstraints gbc_tglbtnFillColor = new GridBagConstraints();
        gbc_tglbtnFillColor.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnFillColor.gridx = 17;
        gbc_tglbtnFillColor.gridy = 0;
        pnlNorth.add(tglbtnFillColor, gbc_tglbtnFillColor);
        group.add(tglbtnFillColor);

        tglbtnNext = new JToggleButton("Next");
        tglbtnNext.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnNext = new GridBagConstraints();
        gbc_tglbtnNext.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnNext.gridx = 0;
        gbc_tglbtnNext.gridy = 1;
        pnlNorth.add(tglbtnNext, gbc_tglbtnNext);
        group.add(tglbtnNext);

        tglbtnOpenCommands = new JToggleButton("Open Commans");
        tglbtnOpenCommands.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnOpenCommands = new GridBagConstraints();
        gbc_tglbtnOpenCommands.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnOpenCommands.gridx = 10;
        gbc_tglbtnOpenCommands.gridy = 1;
        pnlNorth.add(tglbtnOpenCommands, gbc_tglbtnOpenCommands);
        group.add(tglbtnOpenCommands);

        tglbtnOpenShapes = new JToggleButton("Open Shapes");
        tglbtnOpenShapes.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnOpenShapes = new GridBagConstraints();
        gbc_tglbtnOpenShapes.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnOpenShapes.gridx = 11;
        gbc_tglbtnOpenShapes.gridy = 1;
        pnlNorth.add(tglbtnOpenShapes, gbc_tglbtnOpenShapes);
        group.add(tglbtnOpenShapes);

        tglbtnSaveCommands = new JToggleButton("Save Commans");
        tglbtnSaveCommands.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnSaveCommands = new GridBagConstraints();
        gbc_tglbtnSaveCommands.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnSaveCommands.gridx = 15;
        gbc_tglbtnSaveCommands.gridy = 1;
        pnlNorth.add(tglbtnSaveCommands, gbc_tglbtnSaveCommands);
        group.add(tglbtnSaveCommands);

        tglbtnSaveShapes = new JToggleButton("Save Shapes");
        tglbtnSaveShapes.setPreferredSize(dimensionForButton);
        GridBagConstraints gbc_tglbtnSaveShapes = new GridBagConstraints();
        gbc_tglbtnSaveShapes.insets = new Insets(5, 0, 5, 5);
        gbc_tglbtnSaveShapes.gridx = 16;
        gbc_tglbtnSaveShapes.gridy = 1;
        pnlNorth.add(tglbtnSaveShapes, gbc_tglbtnSaveShapes);
        group.add(tglbtnSaveShapes);
        
        list = new JList();
        getContentPane().add(list, BorderLayout.SOUTH);
       // listModel = view.getModel().getListModel();
        list.setModel(view.getModel().getListModel());
        
        scrollPane = new JScrollPane(list);
        
        getContentPane().add(scrollPane, BorderLayout.SOUTH);
//        view.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent arg0) {
//                lblXY.setText("x: " + arg0.getX() + "  " + "y: " + arg0.getY());
//            }
//        });
        
        tglbtnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.onDelete();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        tglbtnEdgeColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.setEdgeColor();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        tglbtnFillColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.setFillColor();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        tglbtnModify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.onModify();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        tglbtnToFront.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.onToFront();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        tglbtnToBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.onToBack();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        tglbtnBringToFront.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.onBringToFront();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        tglbtnBringToBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.onBringToBack();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        tglbtnUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.onUndo();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        tglbtnRedo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.onRedo();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        tglbtnSaveShapes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if (view.getModel().getUndoStack().size() > 0) {
                        controller.onSaveShapes();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        tglbtnOpenShapes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.onOpenShapes();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        tglbtnSaveCommands.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if (view.getModel().getUndoStack().size() > 0) {
                        controller.onSaveCommands();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        tglbtnOpenCommands.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.onOpenCommands();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        tglbtnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.next();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
	}
	
	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	
	public JList getList() {
		return list;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("EdgeColor")) {
            tglbtnEdgeColor.setBackground((Color) evt.getNewValue());
            tglbtnSelect.setSelected(true);
        } else if (evt.getPropertyName().equals("FillColor")) {
            tglbtnFillColor.setBackground((Color) evt.getNewValue());
            tglbtnSelect.setSelected(true);
        } else if (evt.getPropertyName().equals("sizeUpdate")) {
            if ((int)evt.getNewValue() == 0) {
                tglbtnModify.setEnabled(false);
                tglbtnDelete.setEnabled(false);
            } else if ((int)evt.getNewValue() == 1) {
                tglbtnModify.setEnabled(true);
                tglbtnDelete.setEnabled(true);
            } else {
                tglbtnModify.setEnabled(false);
                tglbtnDelete.setEnabled(true);
            }
        } else if (evt.getPropertyName().equals("sizeUndo")) {
            if ((int)evt.getNewValue() == 0) {
                tglbtnUndo.setEnabled(false);
            } else {
                tglbtnUndo.setEnabled(true);
            }
        } else if (evt.getPropertyName().equals("sizeRedo")) {
            if ((int)evt.getNewValue() == 0) {
                tglbtnRedo.setEnabled(false);
            } else {
                tglbtnRedo.setEnabled(true);
            }
        }
		
	}

	public DrawingController getController() {
		return controller;
	}

	public JToggleButton getTglbtnModify() {
		return tglbtnModify;
	}

	public JToggleButton getTglbtnDelete() {
		return tglbtnDelete;
	}

	public JToggleButton getTglbtnToFront() {
		return tglbtnToFront;
	}

	public JToggleButton getTglbtnToBack() {
		return tglbtnToBack;
	}

	public JToggleButton getTglbtnBringToFront() {
		return tglbtnBringToFront;
	}

	public JToggleButton getTglbtnBringToBack() {
		return tglbtnBringToBack;
	}

	public JToggleButton getTglbtnUndo() {
		return tglbtnUndo;
	}

	public JToggleButton getTglbtnRedo() {
		return tglbtnRedo;
	}

	public JToggleButton getTglbtnEdgeColor() {
		return tglbtnEdgeColor;
	}

	public JToggleButton getTglbtnFillColor() {
		return tglbtnFillColor;
	}

	public JToggleButton getTglbtnNext() {
		return tglbtnNext;
	}

	public JToggleButton getTglbtnSaveCommands() {
		return tglbtnSaveCommands;
	}

	public JToggleButton getTglbtnSaveShapes() {
		return tglbtnSaveShapes;
	}

	public JToggleButton getTglbtnOpenCommands() {
		return tglbtnOpenCommands;
	}

	public JToggleButton getTglbtnOpenShapes() {
		return tglbtnOpenShapes;
	}

	public ListModel<String> getListModel() {
		return listModel;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}
	
}
