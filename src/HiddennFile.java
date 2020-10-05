import javax.swing.*;

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
	}
}
