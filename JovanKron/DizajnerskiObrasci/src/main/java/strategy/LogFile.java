package strategy;

import java.io.*;
import java.nio.file.*;
import commands.*;
import dialogs.*;
import geometry.*;
import mvc.*;
import observer.ObserverForButtons;

public class LogFile implements AnyFile {
	private DrawingModel model;
	private DrawingFrame frame;
	private Shape shape, updatedShape;
	private OptionPane optionPane = new RealOptionPane();
	private DlgCommandsAnswer dialog = new DlgCommands();
	
	public LogFile (DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public void saveFile(File file) {
		if(Files.exists(Paths.get(file.toString() + ".log"))) { 
			optionPane.showMessageDialog(frame,"File with same name already exists");
			return;
		}
		try {
			FileWriter fileWriter = new FileWriter(file + ".log");
			fileWriter.write(frame.getLogPanel().getText());
			fileWriter.close();
			optionPane.showMessageDialog(frame, "The log file was saved successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            optionPane.showMessageDialog(frame, "Error while saving the log file");
        }
	}
	
	public void loadFile(File file) {
		if(!Files.exists(Paths.get(file.toString()))) { 
			optionPane.showMessageDialog(frame,"File does not exist");
			return;
		}
		String[] parts = null;
		String extension = "";
		if(file.getName().contains(".")) {
			parts = file.getName().split("\\.");
			extension = parts[parts.length - 1];
		} else {
			optionPane.showMessageDialog(frame,"File can't be loaded");
			return;
		}
		if(extension.equals("log"))
			parseLogFile(file);
	    else
	    	optionPane.showMessageDialog(frame,"File has to be of type log");
	}
	
	private void parseLogFile(File file) {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
	   	 	
	   	 	String textCommand;
	        while ((textCommand = bufferedReader.readLine()) != null) {
	        	//DlgCommands dialog = new DlgCommands();
	        	showDlgCommand(textCommand);
	        	if(dialog.isConfirmed())
	        		resolveCommand(textCommand);
	        	else
	        		break;
            }
        } catch (Exception e2) {
			e2.printStackTrace();
			optionPane.showMessageDialog(frame,"Error reading the log file");
		}
	}

	private void showDlgCommand(String textCommand) {
		dialog.getTextPane().setText(textCommand);
      	dialog.setVisible(true);
	}
	
	private void resolveCommand(String text) {
		if(text.contains("Executed"))
			executeCommnad(text);
		else if (text.contains("Unexecuted"))
			frame.getController().undo();
		else if (text.contains("Re-executed"))
			frame.getController().redo();
	}
	
	private void executeCommnad(String text ) {
		Command command = null;
		shape = null;
		updatedShape = null;
		if(text.contains("CmdRemove"))
			frame.getController().deleteShapes();
		else {
			if(text.contains("Update")) {
				parseUpdateTextCmd(text);
				command = new CmdUpdate(shape, updatedShape);
			} else
				command = createAddOrSelectCmd(text);
			if(command == null)
				command = createPositioningCmd(text);
			if(command != null)
				frame.getController().executeAndLogCommand(command, shape, updatedShape);
		}
	}
	
	private void parseUpdateTextCmd(String text) {
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
		parseTextCmdToShape(text);
		if(text.contains("CmdAdd")) {
			shape.addObserver(new ObserverForButtons(model, frame));
			return new CmdAdd(shape, model);
		} else if(text.contains("CmdSelect")) {
			int pos = model.getShapes().indexOf(shape);
			return new CmdSelect(model.get(pos), shape.isSelected());
		}
		return null;
	}
	
	private void parseTextCmdToShape(String text) {
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
		int indexOfShapeToMove = frame.getController().findIndexOfMostTopSelectedShape();
		if(text.contains("BringToBack"))
	 		return new CmdBringToBack(indexOfShapeToMove, model);
 		else if(text.contains("BringToFront"))
 			return new CmdBringToFront(indexOfShapeToMove, model);
 		else if(text.contains("CmdToBack"))
 			return new CmdToBack(indexOfShapeToMove, model);
 		else if(text.contains("CmdToFront"))
 			return new CmdToFront(indexOfShapeToMove, model);
		return null;
	}
	
	public void setOptionPane(OptionPane optionPane) {
	    this.optionPane = optionPane;
	}
	
	public void setDlgCommandsAnswer(DlgCommandsAnswer dlgCommandsAnswer) {
	    this.dialog = dlgCommandsAnswer;
	}
	
}
