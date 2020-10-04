import javax.swing.*;

public class Encryption {
	private JPanel panel1;
	private JTextArea textArea1;
	private JTextArea textArea2;
	private JTextField textField1;
	private JButton clickButton;
	private JComboBox comboBox1;

	public Encryption(){
		JFrame encryptFrame = new JFrame();
		encryptFrame.add(panel1);
		encryptFrame.setSize(900,650);
		encryptFrame.setLocationRelativeTo(null);
		encryptFrame.setVisible(true);
		encryptFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

	}


}
