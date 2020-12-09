package strategy;

import commands.*;
import geometry.*;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.DrawingModel;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LogParser {
    private String data;
    private String[] dataLine;
    private DrawingModel model;
    private Scanner myReader;
    private Command cmd = null;
    private Shape s = null;
    private ArrayList<Shape> shapesForRemove = new ArrayList<>();
    private int sizeForRemove;
    private ArrayList<Shape> shapesForDeselect = new ArrayList<>();
    private int sizeForDeselect;

    public LogParser(Scanner myReader) {
        this.myReader = myReader;
    }

    public void read() throws Exception {
        try {
            if (myReader.hasNextLine()) {
                data = myReader.nextLine();
                dataLine = data.split(" ");
                System.out.println(data);
                doCmd();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doCmd() throws Exception {
        String mode = dataLine[0];
        if (mode.equals("Add") || mode.equals("Select") || mode.equals("Unselect") || mode.equals("Remove")) {
            model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
            model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
            String shape = dataLine[1];
            parse(mode, shape);
        } else if (mode.equals("Update")) {
            String shape = dataLine[1];
            String oldInfo = data.split(" to ")[0].replaceAll("Update ", "");
            String newInfo = data.split(" to ")[1];
            model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
            model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
            data = oldInfo;
            parse(mode, shape);
            Shape oldState = s;
            data = newInfo;
            parse(mode, shape);
            Shape newState = s;
            newState.setEdgeColor(model.getEdgeColor());
            if (newState instanceof SurfaceShape) {
                ((SurfaceShape) newState).setFillColor(model.getFillColor());
            }
            for (Shape s : model.getShapes()) {
                if (s.equals(oldState)) {
                    oldState = s;
                }
            }
            cmd = new CmdUpdate(oldState, newState);
            cmd.setModel(model);
            cmd.execute();
        } else if (mode.equals("Undo")) {
            cmd = new CmdUndo(model);
            cmd.execute();
        } else if (mode.equals("Redo")) {
            cmd = new CmdRedo(model);
            cmd.execute();
        } else if (mode.equals("Delete")) {
            sizeForRemove = Integer.parseInt(dataLine[2]);
            for (int i = 0; i < sizeForRemove; i++) {
                read();
            }
        } else if (mode.equals("Deselect")) {
            sizeForDeselect = Integer.parseInt(dataLine[2]);
            for (int i = 0; i < sizeForDeselect; i++) {
                read();
            }
        } else if (mode.equals("Set")) {
            model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
            model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
            data = data.replaceAll("Set " + dataLine[1] + " Color: ", "");
            int r = Integer.parseInt(data.split(" , ")[0]);
            int g = Integer.parseInt(data.split(" , ")[1]);
            int b = Integer.parseInt(data.split(" , ")[2]);
            Color newColor = new Color(r, g, b);
            if (dataLine[1].equals("Edge")) {
                cmd = new CmdSetEdgeColor(model.getEdgeColor(), newColor);
            } else if (dataLine[1].equals("Fill")) {
                cmd = new CmdSetFillColor(model.getFillColor(), newColor);
            }
            cmd.setModel(model);
            cmd.execute();
        } else if (mode.equals("ToBack")) {
            zAxisMoving(mode);
            int index = model.indexOf(s);
            cmd = new CmdToBack(s, index);
            cmd.setModel(model);
            cmd.execute();
        } else if (mode.equals("ToFront")) {
            zAxisMoving(mode);
            int index = model.indexOf(s);
            cmd = new CmdToFront(s, index);
            cmd.setModel(model);
            cmd.execute();
        } else if (mode.equals("BringToBack")) {
            zAxisMoving(mode);
            int index = model.indexOf(s);
            cmd = new CmdBringToBack(s, index);
            cmd.setModel(model);
            cmd.execute();
        } else if (mode.equals("BringToFront")) {
            zAxisMoving(mode);
            int index = model.indexOf(s);
            cmd = new CmdBringToFront(s, index);
            cmd.setModel(model);
            cmd.execute();
        }
    }

    public void zAxisMoving(String mode) throws Exception {
        model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
        model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
        String shape = dataLine[1];
        parse(mode, shape);
        for (Shape sh : model.getShapes()) {
            if (sh.equals(s)) {
                s = sh;
            }
        }
    }

    private void parse(String mode, String shape) throws Exception {
        if (shape.equals("Point")) {
            s = new Point();
        } else if (shape.equals("Line")) {
            s = new Line();
        } else if (shape.equals("Rectangle")) {
            s = new Rectangle();
        } else if (shape.equals("Circle")) {
            s = new Circle();
        } else if (shape.equals("Donut")) {
            s = new Donut();
        } else if (shape.equals("Hexagon")) {
            s = new HexagonAdapter();
        }
        data = data.replaceAll(mode + " ", "");
        s = s.parse(data);
        if (mode.equals("Add")) {
            cmd = new CmdShapeAdd(s, model);
        } else if (mode.equals("Unselect")) {
            model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
            model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
            for (Shape sh : model.getShapes()) {
                if (sh.equals(s)) {
                    shapesForDeselect.add(sh);
                    if (shapesForDeselect.size() == sizeForDeselect) {
                        cmd = new CmdDeselect(shapesForDeselect, model);
                        cmd.execute();
                        shapesForDeselect.clear();
                    }
                    break;
                }
            }
            return;
        } else if (mode.equals("Select")) {
            for (Shape sh : model.getShapes()) {
                if (sh.equals(s)) {
                    cmd = new CmdSelect(sh, model);
                    cmd.setModel(model);
                    break;
                }
            }
        } else if (mode.equals("Remove")) {
            model.getPropertyChangeSupport().firePropertyChange("sizeUndo", model.getUndoStack().size(), model.getUndoStack().size() + 1);
            model.getPropertyChangeSupport().firePropertyChange("sizeRedo", model.getRedoStack().size(), 0);
            for (Shape sh : model.getShapes()) {
                if (sh.equals(s)) {
                    shapesForRemove.add(sh);
                    if (shapesForRemove.size() == sizeForRemove) {
                        cmd = new CmdShapeRemove(shapesForRemove, model);
                        cmd.execute();
                        shapesForRemove.clear();
                    }
                    break;
                }
            }
            return;
        } else if (mode.equals("Update") ||
                mode.equals("ToBack") ||
                mode.equals("ToFront") ||
                mode.equals("BringToBack") ||
                mode.equals("BringToFront")) {
            return;
        }
        cmd.execute();
    }

    public void setModel(DrawingModel model) {
        this.model = model;
    }
}
