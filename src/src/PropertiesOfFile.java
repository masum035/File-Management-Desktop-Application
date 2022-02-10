package src;

import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PropertiesOfFile implements DropTargetListener {
	private JTextField textFieldforDir;
	private JButton showMeButton;
	private JTextArea textArea;
	private JPanel PropertyPanel;
	private JTextArea textAreaForDragandDrop;
	ArrayList<String> pathArray = new ArrayList<>();

	public PropertiesOfFile() {
		DropTarget dt = new DropTarget(textAreaForDragandDrop, this);
		JFrame propertyFrame = new JFrame();
		propertyFrame.add(PropertyPanel);
		propertyFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		propertyFrame.setSize(570, 550);
		propertyFrame.setLocationRelativeTo(null);
		propertyFrame.setVisible(true);

		showMeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < pathArray.size(); i++) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy HH:mm:ss a");
					String path = pathArray.get(i);
					File file = new File(path);
					Path fileLocation = Paths.get(path);
					try {
						BasicFileAttributes attr = Files.readAttributes(fileLocation, BasicFileAttributes.class);
						String owner = "Owner: " + file.getParent() + "\n";
						String filePath = "File Path : " + file.getAbsolutePath() + "\n";
						String parentFile = "Parent File      : " + file.getParentFile() + "\n";
						String canonicalPath = "File canonicalPath : " + file.getCanonicalPath() + "\n";
						String createdTime = "Creation Time : " + attr.creationTime() + "\n";
						String accessedTime = "Accessed Time : " + attr.lastAccessTime() + "\n";
						String lastModified = "Last Modified : " + attr.lastModifiedTime() + "\n";
						String FileSize = "File Size     : " + attr.size() + " Bytes\n";
						String readable = "Readable       : " + file.canRead() + "\n";
						String writable = "Writable      : " + file.canWrite() + "\n";
						String hidden = "Hidden      : " + file.isHidden() + "\n";
						String executable = "Executable      : " + file.canExecute() + "\n";
						//String totalSpace = "totalSpace      : " + (file.getTotalSpace() / 1024)/1024 + " GB\n";
						//String freeSpace = "FreeSpace      : " + (file.getFreeSpace() / 1024 )/1024+ "GB\n";
						textArea.append(owner + filePath + parentFile + canonicalPath + createdTime + accessedTime + lastModified + FileSize + readable + writable + hidden + executable + "\n\n");
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
				}
			}
		});
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
//		System.out.println("Drag Enter");
//		textAreaForDragandDrop.append("Drag Enter");
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
		textAreaForDragandDrop.append("\n");
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


//G:\\Arnob Sir
// C:\Users\Malware\Desktop\New Text Document.txt