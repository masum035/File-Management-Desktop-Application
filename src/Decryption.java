import javax.swing.*;

public class Decryption {
	private JTextArea textArea1;
	private JTextArea textArea2;
	private JButton clickButton;
	private JTextField textField1;
	private JPanel DecryptPanael;

	public Decryption(){
		JFrame DecryptFrame = new JFrame();
		DecryptFrame.add(DecryptPanael);
		DecryptFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		DecryptFrame.setSize(900,650);
		DecryptFrame.setLocationRelativeTo(null);
		DecryptFrame.setVisible(true);
	}


}
