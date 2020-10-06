import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainFrame implements ActionListener{
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
	private JMenuBar menuBar;

	public mainFrame() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		RoooTpanel.setOpaque(true);

		// TODO: 10/6/2020 menuBar add korte hobe + game + help feature add korte hobe
		menuBar = new JMenuBar();
		JMenu helpMenu = new JMenu("Need Help?");

		JMenuItem CreateFileMenu = new JMenuItem("Regarding Create File");
		JMenuItem MoveFileMenu = new JMenuItem("Regarding Move File");
		JMenuItem SearchFileMenu = new JMenuItem("Regarding Search File");
		JMenuItem HideFileMenu = new JMenuItem("Regarding Hide File");
		JMenuItem LockFileMenu = new JMenuItem("Regarding Lock File");
		JMenuItem EncryptFileMenu = new JMenuItem("Regarding Encrypt File");
		JMenuItem PropertyFileMenu = new JMenuItem("Regarding Properties");
		JMenuItem DeleteFileMenu = new JMenuItem("Regarding Delete File");

		CreateFileMenu.addActionListener(this);
		MoveFileMenu.addActionListener(this);
		SearchFileMenu.addActionListener(this);
		HideFileMenu.addActionListener(this);
		LockFileMenu.addActionListener(this);
		EncryptFileMenu.addActionListener(this);
		PropertyFileMenu.addActionListener(this);
		DeleteFileMenu.addActionListener(this);

		helpMenu.add(CreateFileMenu);
		helpMenu.add(MoveFileMenu);
		helpMenu.add(SearchFileMenu);
		helpMenu.add(HideFileMenu);
		helpMenu.add(LockFileMenu);
		helpMenu.add(EncryptFileMenu);
		helpMenu.add(PropertyFileMenu);
		helpMenu.add(DeleteFileMenu);

		JMenu gameMenu = new JMenu("Bored! Play with me!");
		menuBar.add(helpMenu);
		menuBar.add(gameMenu);




		createNewFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateNewFile CreateFileobj = new CreateNewFile();
			}
		});
		encryptFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
		lockFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LockTheFile LockFileObj = new LockTheFile();
			}
		});
		unLockFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UnlockTheFile UnlockFileObj = new UnlockTheFile();
			}
		});
		propertiesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PropertiesOfFile propertiesObj = new PropertiesOfFile();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame RoooT = new mainFrame();
				JFrame RoootFrame = new JFrame();
				RoootFrame.add(RoooT.RoooTpanel);
				RoootFrame.setJMenuBar(RoooT.menuBar);
				RoootFrame.setVisible(true);
				RoootFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				//RoootFrame.setOpacity((float) .8);
				RoootFrame.setSize(250, 380);
				RoootFrame.setLocationRelativeTo(null);
			}
		});
	}

}
