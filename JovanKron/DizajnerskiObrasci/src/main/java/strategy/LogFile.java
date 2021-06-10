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
	private Command command;
	
	public LogFile (DrawingModel model,DrawingFrame frame) {
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
       	 	String forRemoveUndo = "";
       	 	String forRemoveRedo = "";
            while ((textCommand = bufferedReader.readLine()) != null) {
		       	if(textCommand.contains("Unexecuted CmdRemove")) {
		       		forRemoveUndo = forRemoveUndo + textCommand + "\n";
		       		if(!forRemoveRedo.isEmpty()) {
		       			dialog.getTextPane().setText(forRemoveRedo);
		            	forRemoveRedo = "";
			            dialog.setVisible(true);
			            if(dialog.isConfirmed())
			            	executeCommand("Re-executed");
			            else
			            	break;
		       		 }
		       		 continue;
		       	 }
		       	 if(!forRemoveUndo.isEmpty()) {
		       		 dialog.getTextPane().setText(forRemoveUndo);
		       		 forRemoveUndo = "";
		       		 dialog.setVisible(true);
	            	 if(dialog.isConfirmed())
	            		 executeCommand("Unexecuted");
	            	 else
	            		 break;
		       	 }
	           	 if(textCommand.contains("Re-executed CmdRemove")) {
	           		 forRemoveRedo = forRemoveRedo + textCommand + "\n";
	           		 continue;
	           	 }
	           	 if(!forRemoveRedo.isEmpty()) {
	           		 dialog.getTextPane().setText(forRemoveRedo);
	           		 forRemoveRedo = "";
	            	 dialog.setVisible(true);
	            	 if(dialog.isConfirmed())
	            		 executeCommand("Re-executed");
	            	 else
	            		 break;
	           	 }
	           	 dialog.getTextPane().setText(textCommand);
	           	 dialog.setVisible(true);
	           	 if(dialog.isConfirmed())
	           		executeCommand(textCommand);
	           	 else
	           		 break;
            }
            //in case when unexecute or re-execute are last commands in batch do to continue in while will skip them
            checkForRedoUndoRemove(dialog, forRemoveUndo, forRemoveRedo);
            
        } catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(frame,"Error reading the log file");
		}
	}
	
	private void executeCommand(String text) {
		command = null;
		shape = null;
		updatedShape = null;
		if(text.contains("Executed")) {
			parseTextToShape(text);
			if(updatedShape == null)
				createAddSelectOrRemoveCmd(text);
			else
				command = new CmdUpdate(shape, updatedShape);
			if(command == null)
	   	 		createPositioningCmd(text);
			if(command != null)
				frame.getController().executeAndLogCommand(command, shape, updatedShape);
		} else if (text.contains("Unexecuted"))
			frame.getController().undo();
		else if (text.contains("Re-executed"))
			frame.getController().redo();
	}
	
	private void parseTextToShape(String text) {
		if(text.contains("Point")) {
			if(text.contains("Update"))
				modifyPoint(text);
			else
				shape = new Point().parse(text);
		} else if(text.contains("Line")) {
			if(text.contains("Update"))
				modifyLine(text);
			else
				shape = new Line().parse(text);
		} else if(text.contains("Circle")) {
			if(text.contains("Update"))
				modifyCircle(text);
			else
				shape = new Circle().parse(text);
		} else if(text.contains("Rectangle")) {
			if(text.contains("Update"))
				modifyRectangle(text);
			else
				shape = new Rectangle().parse(text);
		} else if(text.contains("Hexagon")) {
			if(text.contains("Update"))
				modifyHexagon(text);
			else
				shape = new HexagonAdapter().parse(text);
		} else if(text.contains("Donut")) {
			if(text.contains("Update"))
				modifyDonut(text);
			else
				shape = new Donut().parse(text);
		}
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

	private void createAddSelectOrRemoveCmd(String text) {
		int pos = model.getShapes().indexOf(shape);
		if(text.contains("Add")) {
			shape.addObserver(new ObserverForButtons(model, frame));
			command = new CmdAdd(shape, model);
		} else if(text.contains("CmdSelect"))
			command = new CmdSelect(model.get(pos), shape.isSelected());
		else if(text.contains("Remove")) {
			command = new CmdRemove(model.get(pos), model);
			frame.setAllShapeManipultationButtonsState(false);
		}
	}
	
	private void createPositioningCmd(String text) {
		if(text.contains("BringToBack"))
	 			command = new CmdBringToBack(shape, model);
 		else if(text.contains("BringToFront"))
 			command = new CmdBringToFront(shape, model);
 		else if(text.contains("CmdToBack"))
 			command = new CmdToBack(shape, model);
 		else if(text.contains("CmdToFront"))
 			command = new CmdToFront(shape, model);
	}
	
	private void checkForRedoUndoRemove(DlgCommands dialog, String forRemoveUndo, String forRemoveRedo) {
		if(!forRemoveRedo.isEmpty()) {
			dialog.getTextPane().setText(forRemoveRedo);
		   	forRemoveRedo = "";
		   	dialog.setVisible(true);
		   	if(dialog.isConfirmed())
		   		executeCommand("Re-executed");
		}
		if(!forRemoveUndo.isEmpty()) {
			dialog.getTextPane().setText(forRemoveUndo);
   		 	forRemoveUndo = "";
   		 	dialog.setVisible(true);
   		 	if(dialog.isConfirmed())
   		 	executeCommand("Unexecuted");
		}
	}

}
