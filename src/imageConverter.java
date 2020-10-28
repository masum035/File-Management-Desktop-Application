import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class imageConverter implements DropTargetListener {
	private JTextField textField1;
	private JButton fileChooseButton;
	private JTextField textField2;
	private JTextArea textArea;
	private JPanel imageConverterPanel;
	private JButton convertButton;
	ArrayList<String> pathArray = new ArrayList<>();

	public imageConverter() {
		DropTarget dt = new DropTarget(textArea, this);
		JFrame imageConverterFrame = new JFrame();
		imageConverterFrame.add(imageConverterPanel);
		imageConverterFrame.setSize(600, 480);
		imageConverterFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		imageConverterFrame.setLocationRelativeTo(null);
		imageConverterFrame.setVisible(true);

		convertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sz = pathArray.size();
				for (int i = 0; i < sz; i++) {
					System.out.println(pathArray.get(i));
					String destfolderLocation = "C:\\Users\\Malware\\Pictures\\Testing Folder\\";
					String destFileName = "output";
					Path sourceFileLocation = Path.of(pathArray.get(i));
					File souceFile = new File(String.valueOf(sourceFileLocation));

					BufferedImage image = null;
					try {
						image = ImageIO.read(souceFile);

						ImageIO.write(image, "jpg", new File(destfolderLocation + destFileName + ".jpg"));

						ImageIO.write(image, "png", new File(destfolderLocation + destFileName + ".png"));

						ImageIO.write(image, "gif", new File(destfolderLocation + destFileName + ".gif"));

						ImageIO.write(image, "bmp", new File(destfolderLocation + destFileName + ".bmp"));

						ImageIO.write(image, "jpeg", new File(destfolderLocation + destFileName + ".jpeg"));
					} catch (IOException exe) {
						//exe.printStackTrace();
						textArea.append("\nThe file you dropped was not an actual image file\nPlease drop the actual image file to convert");
					}
				}
			}
		});
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		System.out.println("Drag Enter");
	}

	@Override
	public void dragExit(DropTargetEvent dte) {

//		System.out.println("Drag Exit");
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
//		System.out.println("Drag Over");
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		System.out.println("Drop Action Changed");
	}

	@Override
	public void drop(DropTargetDropEvent event) {
		try {
			Transferable tr = event.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) {
				if (flavors[i].isFlavorJavaFileListType()) {
					event.acceptDrop(DnDConstants.ACTION_COPY);
					System.out.println("Successful file list drop.\n");
					List list = (List) tr.getTransferData(flavors[i]);
					for (int j = 0; j < list.size(); j++) {
						textArea.append(list.get(j) + "\n");
						String allimageFile = list.get(i).toString();
						if (allimageFile.contains(".jpg") || allimageFile.contains(".png") || allimageFile.contains(".gif") || allimageFile.contains(".jpeg") || allimageFile.contains(".bmp")) {
							pathArray.add(allimageFile);
						}
					}
					event.dropComplete(true);
					convertButton.setVisible(true);
					return;
				}
			}
			System.out.println("Drop failed: " + event);
			event.rejectDrop();
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
			event.rejectDrop();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}