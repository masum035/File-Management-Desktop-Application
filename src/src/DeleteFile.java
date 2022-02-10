package src;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DeleteFile implements DropTargetListener {
	private JButton deleteButton;
	private JPanel DeletePanel;
	private JTextArea textAreaForDeletation;

	String deleteFilePath = "";

	public DeleteFile() {
		DropTarget dt = new DropTarget(textAreaForDeletation, this);
		JFrame deleteFileFrame = new JFrame();
		deleteFileFrame.add(DeletePanel);
		deleteFileFrame.setSize(504, 410);
		deleteFileFrame.setLocationRelativeTo(null);
		deleteFileFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		deleteFileFrame.setVisible(true);

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (deleteFileMethod(deleteFilePath)) {
					textAreaForDeletation.setText("Successfully & Permanently Deleted");
				} else {
					textAreaForDeletation.setText("The file / folder has already been deleted or it don't exists anymore\nPlease Check");
				}
			}
		});
	}

	public static boolean deleteFileMethod(String fileLocation) {
		try {
			File dir = new File(fileLocation);

			if(!dir.isDirectory()) {
				//that means it's a file
				dir.delete();
				return true;
			}
			File[] listFiles = dir.listFiles();
			for(File file : listFiles){
				//System.out.println("Deleting "+file.getName());
				//shobgula file-ke delete kore dilam
				file.delete();
			}
			//now directory is empty, so i can delete it
			dir.delete();
			return true;
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {

	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {

	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {

	}

	@Override
	public void dragExit(DropTargetEvent dte) {

	}

	@Override
	public void drop(DropTargetDropEvent event) {
		try {
			Transferable tr = event.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) {
				if (flavors[i].isFlavorJavaFileListType()) {
					event.acceptDrop(DnDConstants.ACTION_COPY);
					List list = (List) tr.getTransferData(flavors[i]);
					for (int j = 0; j < list.size(); j++) {
						textAreaForDeletation.setText("File/Folder has been Successfully dropped\n");
						deleteFilePath = ((list.get(j)).toString());
					}
					event.dropComplete(true);
					deleteButton.setVisible(true);
					return;
				}
			}
			textAreaForDeletation.append("Drop failed: " + event + "\n");
			event.rejectDrop();
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
			event.rejectDrop();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
