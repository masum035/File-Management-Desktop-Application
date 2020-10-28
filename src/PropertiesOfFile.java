import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

public class PropertiesOfFile {
	private JTextField textFieldforChoose;
	private JButton fileChooserButton;
	private JTextField textFieldforDir;
	private JButton showMeButton;
	private JTextArea textArea;
	private JPanel PropertyPanel;

	public PropertiesOfFile() {
		JFrame propertyFrame = new JFrame();
		propertyFrame.add(PropertyPanel);
		propertyFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		propertyFrame.setSize(570, 550);
		propertyFrame.setLocationRelativeTo(null);
		propertyFrame.setVisible(true);

		showMeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy HH:mm:ss a");
				String path = textFieldforDir.getText();
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
					textArea.setText(owner + filePath + parentFile + canonicalPath + createdTime + accessedTime + lastModified + FileSize + readable + writable + hidden + executable);
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		});
	}

}


//G:\\Arnob Sir
// C:\Users\Malware\Desktop\New Text Document.txt