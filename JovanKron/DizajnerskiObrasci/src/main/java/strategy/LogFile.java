package strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import commands.CmdAdd;
import commands.CmdBringToBack;
import commands.CmdBringToFront;
import commands.CmdRemove;
import commands.CmdSelect;
import commands.CmdToBack;
import commands.CmdToFront;
import commands.CmdUpdate;
import commands.Command;
import dialogs.DlgCommands;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import observer.ObserverForButtons;

public class LogFile implements AnyFile {

	private DrawingModel model;
	private DrawingFrame frame;
	
	public LogFile (DrawingModel model,DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	@Override
	public void saveFile(File file) {
		if(Files.exists(Paths.get(file.toString()+".log"))) { 
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

	@Override
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
		if(ext.equals("log")) {
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
			            		 doCommand("Re-executed");
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
		            		 doCommand("Unexecuted");
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
		            		 doCommand("Re-executed");
		            	 else
		            		 break;
	            	 }
	            	 dialog.getTextPane().setText(textCommand);
	            	 dialog.setVisible(true);
	            	 if(dialog.isConfirmed())
	            		 doCommand(textCommand);
	            	 else
	            		 break;
	             }
	             //in case when unexecute or re-execute are last commands in batch do to continue in while will skip them
	             checkForRedoUndoRemove(dialog, forRemoveUndo, forRemoveRedo);
	             
	         } catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(frame,"Error reading the log file");
			 }
		} else
			JOptionPane.showMessageDialog(frame,"File has to be of type log");
	}
	
	public void doCommand (String text) {
		Command command = null;
		Shape shape = null;
		Shape updatedShape = null;
		if(text.contains("Executed")) {
			if(text.contains("Point")) {
				if(text.contains("Update")) {
					shape = new Point().parse(text.split("_to_")[0]);
					int i = model.getShapes().indexOf(shape);
					if(i != -1) {
						shape = (Point) model.get(i);
						updatedShape = new Point().parse(text.split("_to_")[1]);
					}
				} else
					shape = new Point().parse(text);
				
			} else if(text.contains("Line")) {
				if(text.contains("Update")) {
					shape = new Line().parse(text.split("_to_")[0]);
					int i = model.getShapes().indexOf(shape);
					if(i != -1) {
						shape = (Line) model.get(i);
						updatedShape = new Line().parse(text.split("_to_")[1]);
					}
				} else
					shape = new Line().parse(text);
				
			} else if(text.contains("Circle")) {
				if(text.contains("Update")) {
					shape = new Circle().parse(text.split("_to_")[0]);
					int i = model.getShapes().indexOf(shape);
					if(i != -1) {
						shape = (Circle) model.get(i);
						updatedShape = new Circle().parse(text.split("_to_")[1]);
					}
				} else
					shape = new Circle().parse(text);
				
			} else if(text.contains("Rectangle")) {
				if(text.contains("Update")) {
					shape = new Rectangle().parse(text.split("_to_")[0]);
					int i = model.getShapes().indexOf(shape);
					if(i != -1) {
						shape = (Rectangle) model.get(i);
						updatedShape = new Rectangle().parse(text.split("_to_")[1]);
					}
				} else
					shape = new Rectangle().parse(text);
			} else if(text.contains("Hexagon")) {
				if(text.contains("Update")) {
					shape = new HexagonAdapter().parse(text.split("_to_")[0]);
					int i = model.getShapes().indexOf(shape);
					if(i != -1) {
						shape = (HexagonAdapter) model.get(i);
						updatedShape = new HexagonAdapter().parse(text.split("_to_")[1]);
					}
				} else
					shape = new HexagonAdapter().parse(text);
			} else if(text.contains("Donut")) {
				if(text.contains("Update")) {
					shape = new Donut().parse(text.split("_to_")[0]);
					int i = model.getShapes().indexOf(shape);
					if(i != -1) {
						shape = (Donut) model.get(i);
						updatedShape = new Donut().parse(text.split("_to_")[1]);
					}
				} else
					shape = new Donut().parse(text);
			}
			
			if(updatedShape == null) {
				int pos = model.getShapes().indexOf(shape);
				if(text.contains("Add")) {
					shape.addObserver(new ObserverForButtons(model, frame));
					command = new CmdAdd(shape, model);
				} else if(text.contains("CmdSelect"))
					command = new CmdSelect(model.get(pos), shape.isSelected());
				else if(text.contains("Remove")) {
					command = new CmdRemove(model.get(pos), model);
					frame.getController().disableButtons();
				}
			} else
				command = new CmdUpdate(shape, updatedShape);
			
			if(command == null) {
	   	 		if(text.contains("BringToBack"))
	   	 			command = new CmdBringToBack(shape, model);
	   	 		else if(text.contains("BringToFront"))
	   	 			command = new CmdBringToFront(shape, model);
	   	 		else if(text.contains("CmdToBack"))
	   	 			command = new CmdToBack(shape, model);
	   	 		else if(text.contains("CmdToFront"))
	   	 			command = new CmdToFront(shape, model);
	   	 	}
			
			if(command != null)
				frame.getController().logCommand(command, shape, updatedShape);
			
		} else if (text.contains("Unexecuted"))
			frame.getController().undo();
		else if (text.contains("Re-executed"))
			frame.getController().redo();
	}
	
	public void checkForRedoUndoRemove(DlgCommands dialog, String forRemoveUndo, String forRemoveRedo) {
		if(!forRemoveRedo.isEmpty()) {
   		 dialog.getTextPane().setText(forRemoveRedo);
   		 forRemoveRedo = "";
       	 dialog.setVisible(true);
       	 if(dialog.isConfirmed()) {
       		 doCommand("Re-executed");
       	 }
   	 }
        if(!forRemoveUndo.isEmpty()) {
   		 dialog.getTextPane().setText(forRemoveUndo);
   		 forRemoveUndo = "";
       	 dialog.setVisible(true);
       	 if(dialog.isConfirmed()) {
       		 doCommand("Unexecuted");
       	 }
   	 }
	}

}
