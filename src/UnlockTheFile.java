import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UnlockTheFile {
	private JTextField textField1;
	private JButton fileChooserButton;
	private JTextField textFieldforFileChooser;
	private JPasswordField passwordField1;
	private JButton unLockButton;
	private JPanel UnLockPanel;
	private JLabel infoLabel;

	String fileabsolutePath = "";

	public UnlockTheFile() {
		JFrame UnlockFileFrame = new JFrame();
		UnlockFileFrame.add(UnLockPanel);
		UnlockFileFrame.setSize(680, 240);
		UnlockFileFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		UnlockFileFrame.setLocationRelativeTo(null);
		UnlockFileFrame.setVisible(true);
		fileChooserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(".")); //it will be in our current directory
				int response = fileChooser.showOpenDialog(fileChooserButton);
				//fileChooser.getDescription(file);
				//int response = fileChooser.showSaveDialog(chooseFolderButton);
				if (response == JFileChooser.APPROVE_OPTION) {
					fileabsolutePath = fileChooser.getSelectedFile().getAbsolutePath();
					textFieldforFileChooser.setText(fileabsolutePath);
				}
			}
		});
		unLockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File fileC = new File(fileabsolutePath);
					if (!fileC.canWrite()) {
						fileC.setWritable(true);
						infoLabel.setForeground(Color.green);
						infoLabel.setText("File is now unlocked");
					} else {
						infoLabel.setForeground(Color.blue);
						infoLabel.setText("File is already unlocked");
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
	}

}
