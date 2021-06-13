package strategy;

import java.io.*;
import java.nio.file.*;
import javax.swing.JOptionPane;
import commands.*;
import dialogs.DlgCommands;
import geometry.*;
import mvc.*;
import observer.ObserverForButtons;

public class LogFile implements AnyFile {
	private DrawingModel model;
	private DrawingFrame frame;
	private Shape shape, updatedShape;
	
	public LogFile (DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public void saveFile(File file) {
		if(Files.exists(Paths.get(file.toString() + ".log"))) { 
			JOptionPane.showMessageDialog(frame,"File with same name already exists");
			return;
		}
		try {
			FileWriter fileWriter = new FileWriter(file + ".log");
			fileWriter.write(frame.getLogPanel().getText());
			fileWriter.close();
            JOptionPane.showMessageDialog(null, "The log file was saved successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while saving the log file");
        }
		
	}
	
	public void loadFile(File file) {
		if(!Files.exists(Paths.get(file.toString()))) { 
			JOptionPane.showMessageDialog(frame,"File does not exist");
			return;
		}
		String [] parts = null;
		String ext = "";
		if(file.getName().contains(".")) {
			parts = file.getName().split("\\.");
			ext = parts[parts.length - 1];
		} else {
			JOptionPane.showMessageDialog(frame,"File can't be loaded");
			return;
		}
		if(ext.equals("log"))
			parseLogFile(file);
	    else
			JOptionPane.showMessageDialog(frame,"File has to be of type log");
	}
	
	private void parseLogFile(File file) {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
	   	 	DlgCommands dialog = new DlgCommands();
	   	 	String textCommand;
	        while ((textCommand = bufferedReader.readLine()) != null) {
	        	showDlgCommand(dialog, textCommand);
	        	if(dialog.isConfirmed())
	        		executeCommand(textCommand);
	        	else
	        		break;
            }
        } catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(frame,"Error reading the log file");
		}
	}

	private void showDlgCommand(DlgCommands dialog, String textCommand) {
		dialog.getTextPane().setText(textCommand);
      	dialog.setVisible(true);
	}
	
	private void executeCommand(String text) {
		Command command = null;
		shape = null;
		updatedShape = null;
		if(text.contains("Executed")) {
			if(text.contains("CmdRemove"))
				frame.getController().deleteShapes();
			else {
				if(text.contains("Update")) {
					parseTextToUpdatedShape(text);
					command = new CmdUpdate(shape, updatedShape);
				} else
					command = createAddOrSelectCmd(text);
				if(command == null)
					command = createPositioningCmd(text);
				if(command != null)
					frame.getController().executeAndLogCommand(command, shape, updatedShape);
			}
		} else if (text.contains("Unexecuted"))
			frame.getController().undo();
		else if (text.contains("Re-executed"))
			frame.getController().redo();
	}
	
	private void parseTextToUpdatedShape(String text) {
		if(text.contains("Point"))
			modifyPoint(text);
		else if(text.contains("Line"))
			modifyLine(text);
		else if(text.contains("Circle"))
			modifyCircle(text);
		else if(text.contains("Rectangle"))
			modifyRectangle(text);
		else if(text.contains("Hexagon"))
			modifyHexagon(text);
		else if(text.contains("Donut"))
			modifyDonut(text);
	}
	
	private Command createAddOrSelectCmd(String text) {
		parseTextToShape(text);
		if(text.contains("Add")) {
			shape.addObserver(new ObserverForButtons(model, frame));
			return new CmdAdd(shape, model);
		} else if(text.contains("CmdSelect")) {
			int pos = model.getShapes().indexOf(shape);
			return new CmdSelect(model.get(pos), shape.isSelected());
		}
		return null;
	}
	
	private void parseTextToShape(String text) {
		if(text.contains("Point"))
			shape = new Point().parse(text);
		else if(text.contains("Line"))
			shape = new Line().parse(text);
		else if(text.contains("Circle"))
			shape = new Circle().parse(text);
		else if(text.contains("Rectangle"))
			shape = new Rectangle().parse(text);
		else if(text.contains("Hexagon"))
			shape = new HexagonAdapter().parse(text);
		else if(text.contains("Donut"))
			shape = new Donut().parse(text);
	}
	
	private void modifyPoint(String text) {
		shape = new Point().parse(text.split("_to_")[0]);
		int i = model.getShapes().indexOf(shape);
		if(i != -1) {
			shape = (Point) model.get(i);
			updatedShape = new Point().parse(text.split("_to_")[1]);
		}
	}
	
	private void modifyLine(String text) {
		shape = new Line().parse(text.split("_to_")[0]);
		int i = model.getShapes().indexOf(shape);
		if(i != -1) {
			shape = (Line) model.get(i);
			updatedShape = new Line().parse(text.split("_to_")[1]);
		}
	}
	
	private void modifyCircle(String text) {
		shape = new Circle().parse(text.split("_to_")[0]);
		int i = model.getShapes().indexOf(shape);
		if(i != -1) {
			shape = (Circle) model.get(i);
			updatedShape = new Circle().parse(text.split("_to_")[1]);
		}
	}
	
	private void modifyRectangle(String text) {
		shape = new Rectangle().parse(text.split("_to_")[0]);
		int i = model.getShapes().indexOf(shape);
		if(i != -1) {
			shape = (Rectangle) model.get(i);
			updatedShape = new Rectangle().parse(text.split("_to_")[1]);
		}
	}
	
	private void modifyHexagon(String text) {
		shape = new HexagonAdapter().parse(text.split("_to_")[0]);
		int i = model.getShapes().indexOf(shape);
		if(i != -1) {
			shape = (HexagonAdapter) model.get(i);
			updatedShape = new HexagonAdapter().parse(text.split("_to_")[1]);
		}
	}
	
	private void modifyDonut(String text) {
		shape = new Donut().parse(text.split("_to_")[0]);
		int i = model.getShapes().indexOf(shape);
		if(i != -1) {
			shape = (Donut) model.get(i);
			updatedShape = new Donut().parse(text.split("_to_")[1]);
		}
	}

	private Command createPositioningCmd(String text) {
		if(text.contains("BringToBack"))
	 		return new CmdBringToBack(shape, model);
 		else if(text.contains("BringToFront"))
 			return new CmdBringToFront(shape, model);
 		else if(text.contains("CmdToBack"))
 			return new CmdToBack(shape, model);
 		else if(text.contains("CmdToFront"))
 			return new CmdToFront(shape, model);
		return null;
	}
	
}
