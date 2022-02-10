package src;// TODO: 11/5/2020 shobgula textField-e toolTip Text Dite Hobe
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainFrame implements ActionListener {
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
	private JButton imageConverterButton;
	private JMenuBar menuBar;
	private JMenuItem CreateFileMenu;
	private JMenuItem MoveFileMenu;
	private JMenuItem SearchFileMenu;
	private JMenuItem HideFileMenu;
	private JMenuItem LockFileMenu;
	private JMenuItem EncryptFileMenu;
	private JMenuItem PropertyFileMenu;
	private JMenuItem DeleteFileMenu;
	private JMenuItem gameMenu;
	private JMenu helpMenu;

	public mainFrame() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		RoooTpanel.setOpaque(true);

		// TODO: 10/6/2020 menuBar add korte hobe + game + help feature add korte hobe
		menuBar = new JMenuBar();
		helpMenu = new JMenu("Need Help?");
		gameMenu = new JMenuItem("Bored! Let's Play!");

		CreateFileMenu = new JMenuItem("Regarding Create File");
		MoveFileMenu = new JMenuItem("Regarding Move File");
		SearchFileMenu = new JMenuItem("Regarding Search File");
		HideFileMenu = new JMenuItem("Regarding Hide File");
		LockFileMenu = new JMenuItem("Regarding Lock File");
		EncryptFileMenu = new JMenuItem("Regarding Encrypt File");
		PropertyFileMenu = new JMenuItem("Regarding Properties");
		DeleteFileMenu = new JMenuItem("Regarding Delete File");

		CreateFileMenu.addActionListener(this);
		MoveFileMenu.addActionListener(this);
		SearchFileMenu.addActionListener(this);
		HideFileMenu.addActionListener(this);
		LockFileMenu.addActionListener(this);
		EncryptFileMenu.addActionListener(this);
		PropertyFileMenu.addActionListener(this);
		DeleteFileMenu.addActionListener(this);
		gameMenu.addActionListener(this);

		helpMenu.add(CreateFileMenu);
		helpMenu.add(MoveFileMenu);
		helpMenu.add(SearchFileMenu);
		helpMenu.add(HideFileMenu);
		helpMenu.add(LockFileMenu);
		helpMenu.add(EncryptFileMenu);
		helpMenu.add(PropertyFileMenu);
		helpMenu.add(DeleteFileMenu);
		helpMenu.add(gameMenu);

		menuBar.add(helpMenu);
		//menuBar.add(gameMenu);


//		createNewFileButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				CreateNewFile CreateFileobj = new CreateNewFile();
//			}
//		});
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
//		createNewFileButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				CreateNewFile creatFileObj = new CreateNewFile();
//			}
//		});
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
		imageConverterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imageConverter imageConverterObj = new imageConverter();
			}
		});

		JFrame RoootFrame = new JFrame();
		RoootFrame.add(RoooTpanel);
		RoootFrame.setJMenuBar(menuBar);
		RoootFrame.setVisible(true);
		RoootFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//RoootFrame.setOpacity((float) .8);
		RoootFrame.setSize(250, 410);
		RoootFrame.setLocation(6, 90);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gameMenu) {
			ExtraFeature gameObj = new ExtraFeature();
		}
		if (e.getSource() == CreateFileMenu) {
			//ekhane dialogueBox use korte hobe
			System.out.println("instruction goes here");
		}
		if (e.getSource() == MoveFileMenu) {
			JOptionPane.showMessageDialog(null,"You can move any file .\nJust Dreag & Drop Here","Help For MoveFile Menu",JOptionPane.INFORMATION_MESSAGE);
		}
		if (e.getSource() == SearchFileMenu) {
			System.out.println("");
		}
		if (e.getSource() == HideFileMenu) {
			System.out.println("");
		}
		if (e.getSource() == EncryptFileMenu) {
			System.out.println("");
		}
		if (e.getSource() == LockFileMenu) {
			System.out.println("");
		}
		if (e.getSource() == PropertyFileMenu) {
			System.out.println("");
		}
		if (e.getSource() == DeleteFileMenu) {
			System.out.println("");
		}

//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//			}
//		});

	}

}
