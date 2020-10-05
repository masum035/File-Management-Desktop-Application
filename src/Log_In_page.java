import javax.swing.*;

public class Log_In_page {
	private JPanel Loginpanel;
	private JTextField textField1;
	private JPasswordField passwordField1;
	private JButton logInButton;
	private JButton logOutButton;

	public Log_In_page() {
		JFrame LogInFrame = new JFrame();
		LogInFrame.add(Loginpanel);
		LogInFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		LogInFrame.setSize(500, 600);
		LogInFrame.setLocationRelativeTo(null);
		LogInFrame.setVisible(true);

	}

	public static void main(String[] args) {
		Log_In_page y = new Log_In_page();

	}
}
