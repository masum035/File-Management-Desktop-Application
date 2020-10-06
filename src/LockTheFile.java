import javax.swing.*;

public class LockTheFile {
	private JPanel LockFilePanel;
	private JButton fileChooserButton;
	private JTextField textField1;
	private JTextField textField2;
	private JButton secureLockButton;
	private JButton justLockItButton;
	private JPasswordField passwordField1;

	public LockTheFile(){
		JFrame LockFrame = new JFrame();
		LockFrame.add(LockFilePanel);
		LockFrame.setSize(680,210);
		LockFrame.setLocationRelativeTo(null);
		LockFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		LockFrame.setVisible(true);
	}

}
