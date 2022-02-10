package src;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LockTheFile implements DropTargetListener {
	private JPanel LockFilePanel;
	private JTextArea justDragDropAnyTextArea;
	private JButton justLockButton;
	private JButton securedLockButton;
	private JLabel infoAboutZipping;
	private JPasswordField passwordField;
	private JTextField textField_dir;
	private JButton justLockItButton;

	ArrayList<String> pathArray = new ArrayList<>();

	public LockTheFile() {
		DropTarget dt = new DropTarget(justDragDropAnyTextArea, this);
		JFrame LockFrame = new JFrame();
		LockFrame.add(LockFilePanel);
		LockFrame.setSize(680, 480);
		LockFrame.setLocationRelativeTo(null);
		LockFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		LockFrame.setVisible(true);
//		justLockItButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String fileLocAtion = textField_dir.getText();
//				try {
//					FileLockFuntionusingFileChannel(Path.of(fileLocAtion));
//				} catch (FileNotFoundException fileNotFoundException) {
//					fileNotFoundException.printStackTrace();
//				} catch (IOException ioException) {
//					ioException.printStackTrace();
//				}
//			}
//		});
		justLockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < pathArray.size(); i++) {
					String fileLoCation = pathArray.get(i);
					File file = new File(fileLoCation);
					if (!file.isDirectory()) {
						fileLockFunctionReadOnly(fileLoCation);
					} else {
						JOptionPane.showMessageDialog(null, "Folder can not be just locked.Please Drop File/Files", "Just Lock Failed", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		securedLockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//String fileLoCation = textField_dir.getText();
				for (int i = 0; i < pathArray.size(); i++) {
					String fileLoCation = pathArray.get(i);
					String password = String.valueOf(passwordField.getPassword());
					File file = new File(fileLoCation);
					if (!file.isDirectory()) {
						if (secureLock(fileLoCation,password)) {
							JOptionPane.showInternalMessageDialog(null, "Your File has been Successfully Secured", "Yess! Success!!", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showInternalMessageDialog(null, "One of the File here is already a zipped File\nDrop another file/files", "Sorry :'(", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Folder can not be securely locked.Please Drop File/Files", "Secure Lock Failed", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	public static boolean secureLock(String fileLocation,String password) {
		// TODO: 10/18/2020 choose your zip file location & delete that previous file & set passwordField

		if (fileLocation.contains(".zip")) {
			return false;
		}
		int lastSlashPosition = fileLocation.lastIndexOf('\\');
		String zipFileLocation = fileLocation.substring(0, lastSlashPosition);
		String zipFileName = zipFileLocation + "\\" + "MasumProduction.zip";

		if (fileLocation.contains(".")) {
			int lastDotPosition = fileLocation.lastIndexOf('.');
			String zipFileNaame = fileLocation.substring(lastSlashPosition + 1, lastDotPosition);
			zipFileName = zipFileLocation + "\\" + zipFileNaame + ".zip";
		}

		try {
			ZipParameters zipParameters = new ZipParameters();
			zipParameters.setEncryptFiles(true);
			zipParameters.setEncryptionMethod(EncryptionMethod.AES);
			net.lingala.zip4j.ZipFile zipFile = new net.lingala.zip4j.ZipFile(zipFileName, password.toCharArray());
			zipFile.addFile(new File(fileLocation), zipParameters);

		} catch (ZipException e) {
			e.printStackTrace();
		}
		try {
			File deleteThatFile = new File(fileLocation);
			deleteThatFile.delete();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void fileLockFunctionReadOnly(String filePath) {
		int lastSlashPosition = filePath.lastIndexOf('\\');
//		int lastDotPosition = filePath.lastIndexOf('.');
		String filename = filePath.substring(lastSlashPosition + 1, filePath.length());
		File file = new File(filePath);
		boolean success = /*file.setReadOnly(); */ file.setWritable(false);
		if (success) {
			JOptionPane.showMessageDialog(null, "Your File '" + filename + "' has been locked", "Locked Successfully", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Your File '" + filename + "' could not be Locked.Try Secure Lock", "Locked Failed", JOptionPane.ERROR_MESSAGE);
		}
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
	public void dragExit(DropTargetEvent dtde) {

	}

	@Override
	public void drop(DropTargetDropEvent event) {
		justDragDropAnyTextArea.append("\n");
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
						justDragDropAnyTextArea.append("File/Folder has been Successfully dropped\n");
						pathArray.add((list.get(j)).toString());
					}
					event.dropComplete(true);
					securedLockButton.setVisible(true);
					justLockButton.setVisible(true);
					return;
				}
			}
			justDragDropAnyTextArea.append("Drop failed: " + event);
			event.rejectDrop();
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
			event.rejectDrop();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
