import javax.swing.*;

public class DeleteFile {
	private JButton JFileChooserButton;
	private JTextField fileLocationTextField;
	private JTextField textField2;
	private JButton deleteButton;
	private JPanel DeletePanel;

	public DeleteFile(){
		JFrame deleteFileFrame = new JFrame();
		deleteFileFrame.add(DeletePanel);
		deleteFileFrame.setSize(800,210);
		deleteFileFrame.setLocationRelativeTo(null);
		deleteFileFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		deleteFileFrame.setVisible(true);

	}

}
