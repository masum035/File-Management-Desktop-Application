import javax.swing.*;

public class SearchingFile {
	private JPanel SearchPanel;
	private JTextField textField1;
	private JButton JFileChooserButton;
	private JTextField textField2;
	private JCheckBox checkBox1;
	private JTextArea textArea1;
	private JCheckBox checkBox2;
	private JTextField textField3;
	private JTextField textField4;

	public SearchingFile(){
		JFrame SearchFrame = new JFrame();
		SearchFrame.add(SearchPanel);
		SearchFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		SearchFrame.setSize(560,480);
		SearchFrame.setLocationRelativeTo(null);
		SearchFrame.setVisible(true);

	}
}
