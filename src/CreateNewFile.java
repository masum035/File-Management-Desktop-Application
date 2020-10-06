import javax.swing.*;

public class CreateNewFile {
	private JTextField sourcePathTextField;
	private JTextField textField2;
	private JButton createButton;
	private JButton chooseFolderButton;
	private JScrollBar scrollBar1;
	private JScrollBar scrollBar2;
	private JPanel CreateFilePanel;

	public CreateNewFile(){
		JFrame CreateFileFrame = new JFrame();
		CreateFileFrame.add(CreateFilePanel);
		CreateFileFrame.setSize(800,210);
		CreateFileFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		CreateFileFrame.setLocationRelativeTo(null);
		CreateFileFrame.setVisible(true);
	}

}
