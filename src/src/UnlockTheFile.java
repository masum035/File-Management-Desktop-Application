package src;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnlockTheFile implements DropTargetListener {
	private JButton fileChooserButton;
	private JTextField textFieldforFileChooser;
	private JPanel UnLockPanel;
	private JTextArea textAreaForDragandDrop;
	private JButton unlockButton;
	private JLabel infoLabel;

	String fileabsolutePath = "";
	ArrayList<String> pathArray = new ArrayList<>();

	public UnlockTheFile() {
		DropTarget dt = new DropTarget(textAreaForDragandDrop, this);
		JFrame UnlockFileFrame = new JFrame();
		UnlockFileFrame.add(UnLockPanel);
		UnlockFileFrame.setSize(700, 480);
		UnlockFileFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		UnlockFileFrame.setLocationRelativeTo(null);
		UnlockFileFrame.setVisible(true);

//		fileChooserButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				pathArray.clear();
//				JFileChooser fileChooser = new JFileChooser();
//				fileChooser.setCurrentDirectory(new File(".")); //it will be in our current directory
//				int response = fileChooser.showOpenDialog(fileChooserButton);
//				if (response == JFileChooser.APPROVE_OPTION) {
//					fileabsolutePath = fileChooser.getSelectedFile().getAbsolutePath();
//					textFieldforFileChooser.setText(fileabsolutePath);
//				}
//			}
//		});
		unlockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < pathArray.size(); i++) {
					fileabsolutePath = pathArray.get(i);
					int lastSlashPosition = fileabsolutePath.lastIndexOf('\\');
					String fileName = fileabsolutePath.substring(lastSlashPosition + 1, fileabsolutePath.length());
					try {
						File fileC = new File(fileabsolutePath);
						if (!fileC.canWrite()) {
							fileC.setWritable(true);
							infoLabel.setForeground(Color.red);
							infoLabel.setText(fileName + " is now unlocked,You can Edit your File");
						} else {
							infoLabel.setForeground(Color.blue);
							infoLabel.setText(fileName + " is already unlocked,Just Enjoy!!");
						}
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			}
		});
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
		//textAreaForDragandDrop.append("\n");
		//chooseFolderButtonForDragAndDrop.setVisible(true);
		//infoAboutZipping.setVisible(true);

		try {
			Transferable tr = event.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) {
				if (flavors[i].isFlavorJavaFileListType()) {
					event.acceptDrop(DnDConstants.ACTION_COPY);
					List list = (List) tr.getTransferData(flavors[i]);
					for (int j = 0; j < list.size(); j++) {
						//textAreaForDragandDrop.append(list.get(j) + "\n");
						textAreaForDragandDrop.append("File/Folder has been Successfully dropped\n");
						pathArray.add((list.get(j)).toString());
					}
					event.dropComplete(true);
					unlockButton.setVisible(true);
					return;
				}
			}
			textAreaForDragandDrop.append("Drop failed: " + event);
			event.rejectDrop();
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
			event.rejectDrop();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
