package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CreateNewFile {
	private JTextField sourcePathTextField;
	private JTextField textField2;
	private JButton createButton;
	private JButton chooseFolderButton;
	private JScrollBar scrollBar1;
	private JScrollBar scrollBar2;
	private JPanel CreateFilePanel;

	public CreateNewFile() {
		JFrame CreateFileFrame = new JFrame();
		CreateFileFrame.add(CreateFilePanel);
		CreateFileFrame.setSize(800, 210);
		CreateFileFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		CreateFileFrame.setLocationRelativeTo(null);
		CreateFileFrame.setVisible(true);

		chooseFolderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(".")); //it will be in our current directory
				int response = fileChooser.showOpenDialog(chooseFolderButton);
				//fileChooser.getDescription(file);
				//int response = fileChooser.showSaveDialog(chooseFolderButton);
				if (response == JFileChooser.APPROVE_OPTION) {
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					System.out.println(file);
					System.out.println(fileChooser.getDescription(file));
				}
			}
		});
	}

}
