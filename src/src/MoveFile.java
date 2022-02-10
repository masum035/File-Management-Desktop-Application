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
import java.util.List;

public class MoveFile implements DropTargetListener {
	private JTextField sourceDirectoryTextField;
	private JTextField destinitionPathTextField;
	private JButton moveButton;
	private JPanel Movepanel;
	private JTextArea textAreaForDestination;
	private JTextArea textAreaForSource;
	private JButton chooseDestinationButton;
	private JLabel infoLabel;
	private JLabel infoLabel2;

	String fileDestinationPath = "";
	String sourceFilePath = "";

	public MoveFile() {
		DropTarget dt = new DropTarget(textAreaForSource, this);
		JFrame moveFileFrame = new JFrame();
		moveFileFrame.add(Movepanel);
		moveFileFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		moveFileFrame.setSize(815, 445);
		moveFileFrame.setLocationRelativeTo(null);
		moveFileFrame.setVisible(true);

		moveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String des, src;
				src = sourceFilePath;
				des = fileDestinationPath; // TODO: 11/5/2020 change here please

				File srcFile = new File(src);
				if (!srcFile.isDirectory()) {
					int lastSlashPosition = des.lastIndexOf('\\');
					String desFileLocation = des.substring(0, lastSlashPosition+1);
					des = desFileLocation;
					if (src.contains(".")) {
						int lastDotPosition = src.lastIndexOf('.');
						String fileExtention = src.substring(lastDotPosition + 1, src.length());
						des = desFileLocation + "\\OurProjectProduction." + fileExtention;
					}
				} else {
					int lastSlashPosition = fileDestinationPath.lastIndexOf('\\');
					String destFileLocation = fileDestinationPath.substring(0, lastSlashPosition+1);
					des = destFileLocation;
					//des = des + "\\OurProjectProduction";
				}
				String finalDes = des;
				String finalSrc = src;

				try {
					File file = new File(finalSrc);
					if (file.renameTo(new File(finalDes))) {
						file.delete();
						infoLabel.setForeground(Color.GREEN);
						infoLabel.setText("File moved Successfully");
						infoLabel2.setText("");
					} else {
						infoLabel.setForeground(Color.RED);
						infoLabel.setText("File can not be moved.please check the source or destination file name");
						infoLabel2.setText("N.B:This Error may happen if there are already duplicate named file exists.Such as \"OurProjectProduction\"");
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		chooseDestinationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setCurrentDirectory(new File(".")); //it will be in our current directory
				int response = fileChooser.showOpenDialog(chooseDestinationButton);
				//fileChooser.getDescription(file);
				//int response = fileChooser.showSaveDialog(chooseFolderButton);
				if (response == JFileChooser.APPROVE_OPTION) {
					fileDestinationPath = fileChooser.getSelectedFile().getAbsolutePath();
					textAreaForDestination.setText(fileDestinationPath);
				}
				moveButton.setVisible(true);
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
		try {
			Transferable tr = event.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) {
				if (flavors[i].isFlavorJavaFileListType()) {
					event.acceptDrop(DnDConstants.ACTION_COPY);
					List list = (List) tr.getTransferData(flavors[i]);
					for (int j = 0; j < list.size(); j++) {
						textAreaForSource.setText("File/Folder has been Successfully dropped\n");
						sourceFilePath = ((list.get(j)).toString());
					}
					event.dropComplete(true);
					chooseDestinationButton.setVisible(true);
					return;
				}
			}
			textAreaForSource.append("Drop failed: " + event);
			event.rejectDrop();
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
			event.rejectDrop();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
