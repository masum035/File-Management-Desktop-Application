import javax.swing.*;

public class PropertiesOfFile {
	private JTextField textField1;
	private JButton fileChooserButton;
	private JTextField textField2;
	private JButton showMeButton;
	private JTextArea textArea1;
	private JPanel PropertyPanel;

	public PropertiesOfFile(){
		JFrame propertyFrame = new JFrame();
		propertyFrame.add(PropertyPanel);
		propertyFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		propertyFrame.setSize(570,550);
		propertyFrame.setLocationRelativeTo(null);
		propertyFrame.setVisible(true);

	}

}
