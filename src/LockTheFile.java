import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class LockTheFile {
	private JPanel LockFilePanel;
	private JButton fileChooserButton;
	private JTextField textField1;
	private JTextField textField_dir;
	private JButton secureLockButton;
	private JButton justLockItButton;
	private JPasswordField passwordField1;
	private JButton readOnlyButton;

	public LockTheFile(){
		JFrame LockFrame = new JFrame();
		LockFrame.add(LockFilePanel);
		LockFrame.setSize(680,210);
		LockFrame.setLocationRelativeTo(null);
		LockFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		LockFrame.setVisible(true);
		justLockItButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileLocAtion = textField_dir.getText();
				try {
					FileLockFuntionusingFileChannel(Path.of(fileLocAtion));
				} catch (FileNotFoundException fileNotFoundException) {
					fileNotFoundException.printStackTrace();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		});
		readOnlyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileLoCation = textField_dir.getText();
				fileLockFunctionReadOnly(Path.of(fileLoCation));
			}
		});
	}
	public static void fileLockFunctionReadOnly(Path filePath){
		File file = new File(String.valueOf(filePath));
		boolean success = /*file.setReadOnly(); */ file.setWritable(false);
		if(success) {
			System.out.println("File marked as read only");
		} else {
			System.out.println("File could not be marked as read only");
		}

	}
	public static void FileLockFuntionusingFileChannel(Path filePath) throws IOException {
		FileChannel channel = new RandomAccessFile(String.valueOf(filePath), "rw").getChannel();
		FileLock lock = channel.lock();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.release();
		channel.close();
	}

}
