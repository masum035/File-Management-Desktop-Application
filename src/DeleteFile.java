import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteFile {
	private JButton JFileChooserButton;
	private JTextField fileLocationTextField;
	private JTextField textField_dir;
	private JButton deleteButton;
	private JPanel DeletePanel;

	public DeleteFile(){
		JFrame deleteFileFrame = new JFrame();
		deleteFileFrame.add(DeletePanel);
		deleteFileFrame.setSize(800,210);
		deleteFileFrame.setLocationRelativeTo(null);
		deleteFileFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		deleteFileFrame.setVisible(true);

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Location = textField_dir.getText();
			deleteFileMethod(Location);
			}
		});
	}
	public static void deleteFileMethod(String fileLocation){
		try {
			File file = new File(fileLocation);
			Boolean success = file.delete();
			if(success){
				System.out.println("Deleted");
			}else
			{
				System.out.println("couldn't delete");
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}

}
