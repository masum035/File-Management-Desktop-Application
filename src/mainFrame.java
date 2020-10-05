import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainFrame {
	private JPanel RoooTpanel;
	private JButton createNewFileButton;
	private JButton moveFileButton;
	private JButton searchFileButton;
	private JButton lockFileButton;
	private JButton unLockFileButton;
	private JButton encryptFileButton;
	private JButton decryptFileButton;
	private JButton deleteFileButton;
	private JButton propertiesButton;
	private JButton hideFileButton;

	public mainFrame() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		RoooTpanel.setOpaque(true);

		createNewFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateNewFile CreateFileobj = new CreateNewFile();
			}
		});
		encryptFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Object create korte hobe
				Encryption EncryptObj = new Encryption();

			}
		});
		decryptFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Decryption DecryptObj = new Decryption();

			}
		});
		createNewFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateNewFile creatFileObj = new CreateNewFile();
			}
		});
		moveFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MoveFile moveFileObj = new MoveFile();
			}
		});
		deleteFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteFile DeleteFileObj = new DeleteFile();
			}
		});
		searchFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchingFile SearchFileObj = new SearchingFile();
			}
		});
		hideFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HiddennFile HiddenFileObj = new HiddennFile();
			}
		});
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame RoooT = new mainFrame();
				JFrame RoootFrame = new JFrame();
				RoootFrame.add(RoooT.RoooTpanel);
				RoootFrame.setVisible(true);
				RoootFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				//RoootFrame.setOpacity((float) .8);
				RoootFrame.setSize(250, 380);
				RoootFrame.setLocationRelativeTo(null);
			}
		});
	}
}
