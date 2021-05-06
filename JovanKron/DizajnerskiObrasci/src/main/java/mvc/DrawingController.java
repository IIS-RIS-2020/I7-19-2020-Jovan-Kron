package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import commands.*;
import dialogs.*;
import geometry.*;
import strategy.*;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Command cmd;
	private Point startPoint;
	private Shape tempShape;
	private SaveManager saveManager;
    private OpenManager openManager;
    private Save shapesSave = new ShapesSave(); 
    private Open shapesOpen = new ShapesOpen();
    private ListIterator<Shape> it;
    private Save commandsSave = new CommandsSave();
    private Open commandsOpen = new CommandsOpen();
    private LogParser lp;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent e) throws Exception {
		if(frame.getTglbtnPoint()) {
            Point point = new Point(e.getX(), e.getY());

            ModPoint dialog = new ModPoint();
            dialog.setTxtX(Integer.toString(point.getX()));
            dialog.setTxtY(Integer.toString(point.getY()));
            dialog.setTxtXEdit(false);
            dialog.setTxtYEdit(false);
            dialog.setVisible(true);

            if(dialog.isOk()) {
            	point.setEdgeColor(model.getEdgeColor());

                cmd = new CmdShapeAdd(point, model);
            	model.getRedoStack().clear();
            	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
                model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
                cmd.execute();
            }

        } else if(frame.getTglbtnLine()) {
            ModLine dialog = new ModLine();

            Point p = new Point(e.getX(), e.getY());

            if (startPoint == null) {
                startPoint = new Point(p.getX(), p.getY());
            } else {
                dialog.setTxtStartX(Integer.toString(startPoint.getX()));
                dialog.setTxtStartY(Integer.toString(startPoint.getY()));
                dialog.setTxtStartXEdit(false);
                dialog.setTxtStartYEdit(false);

                dialog.setTxtEndX(Integer.toString(p.getX()));
                dialog.setTxtEndY(Integer.toString(p.getY()));
                dialog.setTxtEndXEdit(false);
                dialog.setTxtEndYEdit(false);
                dialog.setVisible(true);

                if (dialog.isOk()) {
                	Line line = new Line(startPoint, new Point(e.getX(), e.getY()));
                    line.setColor(model.getEdgeColor());

                    cmd = new CmdShapeAdd(line, model);
                	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
                    model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
                    cmd.execute();
                }
                startPoint = null;
            }
        } else if (frame.getTglbtnRectangle()) {
            Point p = new Point(e.getX(),e.getY());

            DlgRectangle dialog = new DlgRectangle();
            dialog.setTxtX(Integer.toString(p.getX()));
            dialog.setTxtY(Integer.toString(p.getY()));
            dialog.setTxtXEdit(false);
            dialog.setTxtYEdit(false);
            dialog.setVisible(true);

            int height = Integer.parseInt(dialog.getTxtHeight().getText());
            int width = Integer.parseInt(dialog.getTxtWidth().getText());

            if (dialog.isOk()) {
            	 Rectangle rectangle = new Rectangle(p,width,height);
                 
                 rectangle.setEdgeColor(model.getEdgeColor());
                 rectangle.setFillColor(model.getFillColor());

                 cmd = new CmdShapeAdd(rectangle, model);
            	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
                model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
                cmd.execute();
            }
        } else if(frame.getTglbtnCircle()) {
            Point p = new Point(e.getX(),e.getY());

            DlgCircle dialog = new DlgCircle();
            dialog.setTxtX(Integer.toString(p.getX()));
            dialog.setTxtY(Integer.toString(p.getY()));
            dialog.setTxtXEdit(false);
            dialog.setTxtYEdit(false);
            dialog.setVisible(true);

            int radius = Integer.parseInt(dialog.getTxtRadius());
            

            if(dialog.isOk()) {
                try {
                	Circle circle = new Circle(p,radius);
                    
                    circle.setEdgeColor(model.getEdgeColor());
                    circle.setFillColor(model.getFillColor());

                    cmd = new CmdShapeAdd(circle, model);
                	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
                    model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
                    cmd.execute();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        } else if(frame.getTglbtnDonut()) {
            Point p = new Point(e.getX(),e.getY());

            DlgDonut dialog = new DlgDonut();
            dialog.setTxtX(Integer.toString(p.getX()));
            dialog.setTxtY(Integer.toString(p.getY()));
            dialog.setTxtXEdit(false);
            dialog.setTxtYEdit(false);
            dialog.setVisible(true);

            int innerRadius = Integer.parseInt(dialog.getTxtInner());
            int outerRadius = Integer.parseInt(dialog.getTxtOuter());

            if(dialog.isOk()) {
            	Donut donut = new Donut(p,outerRadius,innerRadius);
                
                donut.setEdgeColor(model.getEdgeColor());
                donut.setFillColor(model.getFillColor());

                cmd = new CmdShapeAdd(donut, model);
            	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
                model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
                cmd.execute();
            }
        } else if(frame.getTglbtnHexagon()) {
            Point p = new Point(e.getX(),e.getY());

            DlgHexagon dialog = new DlgHexagon();
            dialog.setTxtX(Integer.toString(p.getX()));
            dialog.setTxtY(Integer.toString(p.getY()));
            dialog.setTxtXEdit(false);
            dialog.setTxtYEdit(false);
            dialog.setVisible(true);
            int radius = Integer.parseInt(dialog.getTxtRadius());
            
            if(dialog.isOk()) {
            	HexagonAdapter hexagon = new HexagonAdapter(p.getX(), p.getY(), radius);
                
                hexagon.setEdgeColor(model.getEdgeColor());
                hexagon.setFillColor(model.getFillColor());
                
                cmd = new CmdShapeAdd(hexagon, model);

            	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
                model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
                cmd.execute();
            }
        } else if (frame.getTglbtnSelect()) {
            if (tempShape != null) {
                tempShape = null;
            }
            // prolazi se kroz listu svih shape-ova i selektuje se jedan, ako je kliknuto na njega
            Iterator<Shape> it = model.getShapes().iterator();
            while(it.hasNext())
            {
                Shape temp = it.next();
                if(temp.contains(e.getX(), e.getY()))
                {
                    tempShape = temp;
                }
            }
            // ukoliko je nekog selektovao u prethodnom delu koda
            if (tempShape != null) {
                // prolazi kroz listu prethodno selektovanih shape-ove
                for (Shape shape : model.getShapes()) {
                    // ukoliko se selektovan u ovom prolazu nalazi vec u listi
                    if (shape.isSelected() && shape.equals(tempShape)) {
                        ArrayList<Shape> shapeForDeselect = new ArrayList();
                        shapeForDeselect.add(shape);
                        cmd = new CmdDeselect(shapeForDeselect, model);
                        model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
                        model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
                        cmd.execute();
                        tempShape = null;
                        break;
                    }
                }
                if (tempShape != null) {
                    cmd = new CmdSelect(tempShape, model);
                    cmd.execute();
                }
            } else if (model.getSelectedShapes().size() > 0) {
                ArrayList shapeForDeselect = new ArrayList();
                for (Shape shape : model.getShapes()) {
                    if (shape.isSelected()) {
                        shapeForDeselect.add(shape);
                    }
                }
                cmd = new CmdDeselect(shapeForDeselect, model);
                model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
                model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
                cmd.execute();
                model.getSelectedShapes().clear();
            }
        }
		model.getRedoStack().clear();
        frame.repaint();
	}
	
	public void onDelete() throws Exception {
		if (model.getSelectedShapes().size() > 0) {
            if(JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete this shape?", "Confirmation",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
                model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
                cmd = new CmdShapeRemove(model.getSelectedShapes(), model);
                cmd.execute();
                model.getSelectedShapes().clear();
                model.getRedoStack().clear();
                frame.repaint();
            }
        }
    }
	  
	  public void onModify() throws Exception {
	        if(model.getSelectedShapes().size() == 1) {
	            Shape shape = model.getSelectedShapes().get(0);
	            if(shape instanceof Point) {
	                Point oldState = (Point) shape;

	                ModPoint mp = new ModPoint();
	                mp.setTxtX(Integer.toString(oldState.getX()));
	                mp.setTxtY(Integer.toString(oldState.getY()));
	                mp.setVisible(true);

	                if(mp.isOk()) {
	                	Point newState = new Point(Integer.parseInt(mp.getTxtX()), Integer.parseInt(mp.getTxtY()), model.getEdgeColor());
		                //newState.setEdgeColor(model.getEdgeColor());

		                cmd = new CmdUpdate(oldState, newState);
	                	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
	                    model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
	                    cmd.setModel(model);
	                    cmd.execute();
	                    model.getRedoStack().clear();
	                    frame.repaint();
	                }
	            } else if(shape instanceof Line) {
	                Line oldState = (Line) shape;

	                ModLine ml = new ModLine();
	                ml.setTxtStartXEdit(true);
	                ml.setTxtStartYEdit(true);
	                ml.setTxtEndXEdit(true);
	                ml.setTxtEndYEdit(true);
	                ml.setTxtStartX(Integer.toString(oldState.getStartPoint().getX()));
	                ml.setTxtStartY(Integer.toString(oldState.getStartPoint().getY()));
	                ml.setTxtEndX(Integer.toString(oldState.getEndPoint().getX()));
	                ml.setTxtEndY(Integer.toString(oldState.getEndPoint().getY()));
	                ml.setColor(oldState.getColor());
	                ml.setVisible(true);

	                if(ml.isOk()) {
	                	Point newStartPoint = new Point(Integer.parseInt(ml.getTxtStartX()), Integer.parseInt(ml.getTxtStartY()));
		                Point newEndPoint = new Point(Integer.parseInt(ml.getTxtEndX()), Integer.parseInt(ml.getTxtEndY()));

		                Line newState = new Line(newStartPoint, newEndPoint);
		                newState.setColor(model.getEdgeColor());

		                cmd = new CmdUpdate(oldState, newState);
	                	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
	                    model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
	                    cmd.setModel(model);
	                    cmd.execute();
	                    model.getRedoStack().clear();
	                    frame.repaint();
	                }
	            }
	            else if(shape instanceof Rectangle) {
	                Rectangle oldState = (Rectangle) shape;

	                DlgRectangle dr = new DlgRectangle();
	                dr.setTxtXEdit(true);
	                dr.setTxtYEdit(true);
	                dr.setTxtX(Integer.toString(oldState.getUpperLeftPoint().getX()));
	                dr.setTxtY(Integer.toString(oldState.getUpperLeftPoint().getY()));
	                dr.setTxtWidth(Integer.toString(oldState.getWidth()));
	                dr.setTxtHeight(Integer.toString(oldState.getHeight()));
	                dr.setEdgeColor(oldState.getEdgeColor());
	                dr.setFillColor(oldState.getFillColor());
	                dr.setVisible(true);

	                
	                if(dr.isOk()) {
	                	Rectangle newState = new Rectangle();
		                newState.setUpperLeftPoint(new Point(Integer.parseInt(dr.getTxtX().getText()), Integer.parseInt(dr.getTxtY().getText())));
		                newState.setWidth(Integer.parseInt(dr.getTxtWidth().getText()));
		                newState.setHeight(Integer.parseInt(dr.getTxtHeight().getText()));
		                newState.setEdgeColor(model.getEdgeColor());
		                newState.setFillColor(model.getFillColor());

		                cmd = new CmdUpdate(oldState, newState);
	                	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
	                    model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
	                    cmd.setModel(model);
	                    cmd.execute();
	                    model.getRedoStack().clear();
	                    frame.repaint();
	                }
	            } else if(shape instanceof Donut) {
	                Donut oldState = (Donut) shape;

	                DlgDonut dd = new DlgDonut();
	                dd.setTxtXEdit(true);
	                dd.setTxtYEdit(true);
	                dd.setTxtX(Integer.toString(oldState.getCenter().getX()));
	                dd.setTxtY(Integer.toString(oldState.getCenter().getY()));
	                dd.setTxtInner(Integer.toString(oldState.getInnerRadius()));
	                dd.setTxtOuter(Integer.toString(oldState.getRadius()));
	                dd.setEdgeColor(oldState.getEdgeColor());
	                dd.setFillColor(oldState.getFillColor());
	                dd.setVisible(true);

	                if(dd.isOk()) {
	                	Donut newState = new Donut(
		                        new Point(Integer.parseInt(dd.getTxtX()),Integer.parseInt(dd.getTxtY())),
		                        Integer.parseInt(dd.getTxtOuter()),
		                        Integer.parseInt(dd.getTxtInner())
		                );
		                newState.setFillColor(model.getFillColor());
		                newState.setEdgeColor(model.getEdgeColor());

		                cmd = new CmdUpdate(oldState, newState);
	                	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
	                    model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
	                    cmd.setModel(model);
	                    cmd.execute();
	                    model.getRedoStack().clear();
	                    frame.repaint();
	                }
	            } else if(shape instanceof Circle) {
	                Circle oldState = (Circle) shape;

	                DlgCircle dc = new DlgCircle();
	                dc.setTxtXEdit(true);
	                dc.setTxtYEdit(true);
	                dc.setTxtX(Integer.toString(oldState.getCenter().getX()));
	                dc.setTxtY(Integer.toString(oldState.getCenter().getY()));
	                dc.setRadius(Integer.toString(oldState.getRadius()));
	                dc.setEdgeColor(oldState.getEdgeColor());
	                dc.setFillColor(oldState.getFillColor());
	                dc.setVisible(true);

	                if(dc.isOk()) {
	                	Circle newState = new Circle(new Point(Integer.parseInt(dc.getTxtX()),Integer.parseInt(dc.getTxtY())), Integer.parseInt(dc.getTxtRadius()));
		                newState.setEdgeColor(model.getEdgeColor());
		                newState.setFillColor(model.getFillColor());

		                cmd = new CmdUpdate(oldState, newState);
	                	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
	                    model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
	                    cmd.setModel(model);
	                    cmd.execute();
	                    model.getRedoStack().clear();
	                    frame.repaint();
	                }
	            } else if(shape instanceof HexagonAdapter) {
	                HexagonAdapter oldState = (HexagonAdapter) shape;

	                DlgHexagon dh = new DlgHexagon();
	                dh.setTxtXEdit(true);
	                dh.setTxtYEdit(true);
	                dh.setTxtX(Integer.toString(oldState.getX()));
	                dh.setTxtY(Integer.toString(oldState.getY()));
	                dh.setRadius(Integer.toString(oldState.getR()));
	                dh.setEdgeColor(oldState.getEdgeColor());
	                dh.setFillColor(oldState.getFillColor());
	                dh.setVisible(true);

	                if (dh.isOk()) {
	                	HexagonAdapter newState = new HexagonAdapter(Integer.parseInt(dh.getTxtX()),Integer.parseInt(dh.getTxtY()), Integer.parseInt(dh.getTxtRadius()));
		                newState.setEdgeColor(model.getEdgeColor());
		                newState.setFillColor(model.getFillColor());

		                cmd = new CmdUpdate(oldState, newState);
	                	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
	                    model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
	                    cmd.setModel(model);
	                    cmd.execute();
	                    model.getRedoStack().clear();
	                    frame.repaint();
	                }
	            }
	        }
	    }
	  public void onToFront() throws Exception {
	        if (model.getSelectedShapes().size() == 1) {
	            Shape shape = model.getSelectedShapes().get(0);
	            int index = model.indexOf(shape);
	            if (index + 1 < model.getShapes().size()) {
	            	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
	                model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
	                cmd = new CmdToFront(shape, index);
	                cmd.setModel(model);
	                cmd.execute();
	            }
	            model.getRedoStack().clear();
	            frame.repaint();
	        }
	    }

	    public void onToBack() throws Exception {
	        if(model.getSelectedShapes().size() == 1) {
	            Shape shape = model.getSelectedShapes().get(0);
	            int index = model.indexOf(shape);
	            if (index > 0) {
	            	model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
	                model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
	                cmd = new CmdToBack(shape, index);
	                cmd.setModel(model);
	                cmd.execute();
	            }
	            model.getRedoStack().clear();
	            frame.repaint();
	        }
	    }

	    public void onBringToFront() throws Exception {
	        if(model.getSelectedShapes().size() == 1) {
	            Shape shape = model.getSelectedShapes().get(0);
	            int index = model.indexOf(shape);
	            model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
                model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
                cmd = new CmdBringToFront(shape, index);
	            cmd.setModel(model);
	            cmd.execute();
	            model.getRedoStack().clear();
	            frame.repaint();
	        }
	    }

	    public void onBringToBack() throws Exception {
	        if(model.getSelectedShapes().size() == 1) {
	            Shape shape = model.getSelectedShapes().get(0);
	            int index = model.indexOf(shape);
	            model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
                model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
                cmd = new CmdBringToBack(shape, index);
	            cmd.setModel(model);
	            cmd.execute();
	            model.getRedoStack().clear();
	            frame.repaint();
	        }
	    }
	    
	    public void onUndo() throws Exception {
	        if (model.getUndoStack().size() > 0) {
	            cmd = new CmdUndo(model);
	            cmd.execute();
	            frame.repaint();
	        }
	    }

	    public void onRedo() throws Exception {
	        if (model.getRedoStack().size() > 0) {
	            cmd = new CmdRedo(model);
	            cmd.execute();
	            frame.repaint();
	        }
	    }
	    
	    public void setEdgeColor() throws Exception {
	    	Color chosenEdgeColor = JColorChooser.showDialog(null, "Choose the edge color:", model.getEdgeColor());
	    	if (chosenEdgeColor != null) {
		        cmd = new CmdSetEdgeColor(model.getEdgeColor(), chosenEdgeColor);
		        cmd.setModel(model);
		        model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
		        model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
		        cmd.execute();
		        model.getRedoStack().clear();
	    	}
	    }

	    public void setFillColor() throws Exception {
	    	Color chosenFillColor = JColorChooser.showDialog(null, "Choose the fill color:", model.getFillColor());
	    	if (chosenFillColor != null) {
		        cmd = new CmdSetFillColor(model.getFillColor(), chosenFillColor);
		        cmd.setModel(model);
		        model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
		        model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
		        cmd.execute();
		        model.getRedoStack().clear();
	    	}
	    }
	    
	    public void onSaveShapes() throws IOException {
	        saveManager = new SaveManager(shapesSave);
	        saveManager.save(model.getShapes());
	    }
	    
	    public void onOpenShapes() throws IOException, ClassNotFoundException {
	        openManager = new OpenManager(shapesOpen);
	        it = ((ArrayList) openManager.open()).listIterator();
	        while (it.hasNext()) {
	            Shape s = it.next();
	            model.add(s);
	        }
	        frame.repaint();
	    }
	    
	    public void next() throws Exception {
	        lp.read();
	        frame.repaint();
	    }

	    public void onSaveCommands() throws IOException {
	        saveManager = new SaveManager(commandsSave);
	        saveManager.save(model.getAllCommands());
	    }
	    
	    public void onOpenCommands() throws IOException, ClassNotFoundException {
	        openManager = new OpenManager(commandsOpen);
	        Scanner myReader = (Scanner)openManager.open();
	        lp = new LogParser(myReader);
	        lp.setModel(model);
	    }
}
