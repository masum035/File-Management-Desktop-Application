package src;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HiddennFile implements DropTargetListener {
	private JPanel HiddenPanel;
	private JTextArea textAreaForDrop;
	private JButton justHideButton;
	private JButton vanishButton;
	private JLabel info;
	private JTextField textFieldForTime;
	private JLabel timeLabel;
	private JLabel info2;
	private JComboBox comboBox;

	ArrayList<String> pathArray = new ArrayList<>();

	public HiddennFile() {
		DropTarget dt = new DropTarget(textAreaForDrop, this);
		JFrame HiddenFrame = new JFrame();
		HiddenFrame.add(HiddenPanel);
		HiddenFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		HiddenFrame.setSize(680, 460);
		HiddenFrame.setLocationRelativeTo(null);
		HiddenFrame.setVisible(true);

		vanishButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int time = 0; //default value
				int x = (int)comboBox.getSelectedIndex();
				switch (x){
					case 0 : time = 1; JOptionPane.showMessageDialog(null,"The Vanish method will last 1 minute","Item Selected",JOptionPane.INFORMATION_MESSAGE);  break;
					case 1 : time = 2;  JOptionPane.showMessageDialog(null,"The Vanish method will last 2 minute","Item Selected",JOptionPane.INFORMATION_MESSAGE);  break;
					case 2 : time = 5;  JOptionPane.showMessageDialog(null,"The Vanish method will last 5 minute","Item Selected",JOptionPane.INFORMATION_MESSAGE);  break;
					case 3 : time = 10; JOptionPane.showMessageDialog(null,"The Vanish method will last 10 minute","Item Selected",JOptionPane.INFORMATION_MESSAGE);  break;
					case 4 : time = 15; JOptionPane.showMessageDialog(null,"The Vanish method will last 15 minute","Item Selected",JOptionPane.INFORMATION_MESSAGE);  break;
					case 5 : time = 30; JOptionPane.showMessageDialog(null,"The Vanish method will last 30 minute","Item Selected",JOptionPane.INFORMATION_MESSAGE);  break;
					case 6 : time = 60; JOptionPane.showMessageDialog(null,"The Vanish method will last 60 minute","Item Selected",JOptionPane.INFORMATION_MESSAGE);  break;
					case 7 : time =120; JOptionPane.showMessageDialog(null,"The Vanish method will last 120 minute","Item Selected",JOptionPane.INFORMATION_MESSAGE);  break;
				}
				for (int i = 0; i < pathArray.size(); i++) {
					String fileSrcLocation = pathArray.get(i);
					try {
							info.setForeground(Color.green);
							info.setText("Your file is still there.So what happened??");
							info2.setForeground(Color.green);
							info2.setText("Check it's content please.Vanished,right??");
						if (vanishMethod(Path.of(fileSrcLocation), time)) {
							JOptionPane.showMessageDialog(null, "Your file is still there.So what happened??\nYes,still There.But Check it's content please.\nVanished,right??", "it's Magic!!", JOptionPane.INFORMATION_MESSAGE);
						} else {
							//JOptionPane.showMessageDialog(null, "Last Dropped File can not be vanished right now \nTry out 'Just Hide' method ", "Sorry :'(", JOptionPane.ERROR_MESSAGE);
							info.setForeground(Color.red);
							info.setText("Last Dropped File can not be vanished right now ");
							info2.setForeground(Color.red);
							info2.setText("Try out 'Just Hide' method ");
						}
					} catch (FileNotFoundException fnf) {
						JOptionPane.showMessageDialog(null, "After Dropping the File here, you might renamed/deleted a file from Original Source", "File not Found", JOptionPane.WARNING_MESSAGE);
					} catch (IOException ioException) {
						ioException.printStackTrace();
						JOptionPane.showMessageDialog(null, "Some internal Error occurred , Please try again Later", "Error", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});
		justHideButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < pathArray.size(); i++) {
					String fileSrcLocation = pathArray.get(i);
					try {
						if (hideFileFunction(Path.of(fileSrcLocation))) {
							//JOptionPane.showMessageDialog(null, "Your file is still there.So what happened??\nYes,still There.But Check it's content please.\nVanished,right??", "it's Magic!!", JOptionPane.INFORMATION_MESSAGE);
							info.setForeground(Color.green);
							info.setText("Your File is hidden now successfully");
							info2.setForeground(Color.green);
							info2.setText("Just try out our Search File Feature");
						} else {
							//JOptionPane.showMessageDialog(null, "One of your dropped file is already hidden", "You are caught!!", JOptionPane.WARNING_MESSAGE);
							info.setForeground(Color.red);
							info.setText("One of your dropped file is already hidden");
							info2.setForeground(Color.red);
							info2.setText("You are caught!!");
						}
					} catch (FileNotFoundException fnf) {
						JOptionPane.showMessageDialog(null, "After Dropping the File here, you might renamed/deleted a file from Original Source", "File not Found", JOptionPane.WARNING_MESSAGE);
					} catch (IOException ioException) {
						ioException.printStackTrace();
						JOptionPane.showMessageDialog(null, "Some internal Error occurred , Please try again Later", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	public static boolean hideFileFunction(Path filePath) throws IOException {
		DosFileAttributes attr = Files.readAttributes(filePath, DosFileAttributes.class);
		// TODO: 11/11/2020 This will be for my properties
		System.out.println(filePath.getFileName() + "\nCreation time : " + attr.creationTime() + "\nLast Modification Time : " + attr.lastModifiedTime() + "\nFile Size : " + attr.size() + "kb" + "\nHidden attribute :" + attr.isHidden());
		if (!attr.isHidden()) {
			Files.setAttribute(filePath, "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
			return true;
		}
		attr = Files.readAttributes(filePath, DosFileAttributes.class);
		System.out.println(filePath.getFileName() + "\nCreation time : " + attr.creationTime() + "\nLast Modification Time : " + attr.lastModifiedTime() + "\nFile Size : " + attr.size() + "kb" + "\nHidden attribute :" + attr.isHidden());
		return false;
	}

	//this is for vanish mode
	public static boolean vanishMethod(Path filePath, int time) throws IOException {
		FileChannel channel = new RandomAccessFile(String.valueOf(filePath), "rw").getChannel();
		FileLock lock = channel.lock();
		try {
			TimeUnit.SECONDS.sleep(60 * time);
			lock.release();
			channel.close();
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {

	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {

	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {

	}

	@Override
	public void dragExit(DropTargetEvent dte) {

	}

	@Override
	public void drop(DropTargetDropEvent event) {
		textAreaForDrop.append("\n");
		textAreaForDrop.append("\n");
		try {
			Transferable tr = event.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) {
				if (flavors[i].isFlavorJavaFileListType()) {
					event.acceptDrop(DnDConstants.ACTION_COPY);
					List list = (List) tr.getTransferData(flavors[i]);
					for (int j = 0; j < list.size(); j++) {
						//textAreaForDragandDrop.append(list.get(j) + "\n");
						textAreaForDrop.append("File/Folder has been Successfully dropped\n");
						pathArray.add((list.get(j)).toString());
					}
					event.dropComplete(true);
					timeLabel.setVisible(true);
					comboBox.setVisible(true);
					vanishButton.setVisible(true);
					justHideButton.setVisible(true);
					return;
				}
			}
			textAreaForDrop.append("Drop failed: " + event);
			event.rejectDrop();
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
			event.rejectDrop();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
//File + Folder hide is done But only one should remain,only jfilechooser