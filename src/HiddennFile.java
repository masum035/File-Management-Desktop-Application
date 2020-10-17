import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributes;

public class HiddennFile {
	private JTextField textField1;
	private JButton fileChooserButton;
	private JTextField textField2;
	private JButton hideItButton;
	private JPanel HiddenPanel;

	public HiddennFile(){
		JFrame HiddenFrame = new JFrame();
		HiddenFrame.add(HiddenPanel);
		HiddenFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		HiddenFrame.setSize(680,170);
		HiddenFrame.setLocationRelativeTo(null);
		HiddenFrame.setVisible(true);
		hideItButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String x = textField1.getText();
				try {
					HideFileFunction(Path.of(x));
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		});
	}
	public static void HideFileFunction(Path filePath) throws IOException {
		DosFileAttributes attr = Files.readAttributes(filePath,DosFileAttributes.class);
		System.out.println(filePath.getFileName() + "\nCreation time : " + attr.creationTime() + "\nLast Modification Time : "+ attr.lastModifiedTime() + "\nFile Size : " + attr.size() +"kb"+ "\nHidden attribute :" + attr.isHidden());
		Files.setAttribute(filePath, "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
		attr = Files.readAttributes(filePath,DosFileAttributes.class);
		System.out.println(filePath.getFileName() + "\nCreation time : " + attr.creationTime() + "\nLast Modification Time : "+ attr.lastModifiedTime() + "\nFile Size : " + attr.size() + "kb" + "\nHidden attribute :" + attr.isHidden());
	}

}
//File + Folder hide is done But only one should remain,only jfilechooser