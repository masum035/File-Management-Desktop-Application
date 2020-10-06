import javax.swing.*;

public class UnlockTheFile {
	private JTextField textField1;
	private JButton fileChooserButton;
	private JTextField textField2;
	private JPasswordField passwordField1;
	private JButton unLockButton;
	private JPanel UnLockPanel;

	public UnlockTheFile(){
		JFrame UnlockFileFrame = new JFrame();
		UnlockFileFrame.add(UnLockPanel);
		UnlockFileFrame.setSize(680,220);
		UnlockFileFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		UnlockFileFrame.setLocationRelativeTo(null);
		UnlockFileFrame.setVisible(true);

	}

}
