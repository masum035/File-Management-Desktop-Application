import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Log_In_page implements ActionListener {
	private JPanel Loginpanel;
	private JTextField textField1;
	private JPasswordField passwordField1;
	private JButton logInButton;
	private JButton logOutButton;

	public Log_In_page() {

		logInButton.addActionListener(this);
		logOutButton.addActionListener(this);

	}

	public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
			Log_In_page logInObj = new Log_In_page();
			JFrame LogInFrame = new JFrame();
			LogInFrame.add(logInObj.Loginpanel);
			LogInFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			LogInFrame.setSize(360, 410);
			LogInFrame.setLocationRelativeTo(null);
			LogInFrame.setVisible(true);
		}
	});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logInButton) {
			mainFrame mainFrameObj = new mainFrame();
		}
		if (e.getSource() == logOutButton) {
			System.exit(0);
		}
	}
}
