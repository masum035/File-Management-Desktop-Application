package src;

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
import java.util.List;

public class imageConverter implements DropTargetListener {
	private JTextArea textArea;
	private JPanel imageConverterPanel;
	private JButton convertButton;
	private JButton chooseFolderButton;
	private JButton imageConvertButton;
	private JLabel infoAboutConverting;
	private JLabel dragLabel;
	private JLabel infoAboutConverting02;
	ArrayList<String> pathArray = new ArrayList<>();
	String folderofDestinationPath = "";

	public imageConverter() {
		DropTarget dt = new DropTarget(textArea, this);
		JFrame imageConverterFrame = new JFrame();
		imageConverterFrame.add(imageConverterPanel);
		imageConverterFrame.setSize(670, 480);
		imageConverterFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		imageConverterFrame.setLocationRelativeTo(null);
		imageConverterFrame.setVisible(true);

		imageConvertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sz = pathArray.size();
				for (int i = 0; i < sz; i++) {
					String inputFilePath =  pathArray.get(i);
					int	lastDotPosition = inputFilePath.lastIndexOf('.');
					int lastSlashPosition =  inputFilePath.lastIndexOf('\\');
					String actualFileName = inputFilePath.substring(lastSlashPosition + 1,lastDotPosition);
					String destFileName = "\\converted-Image_" + actualFileName;

					String destfolderLocation = folderofDestinationPath;
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

						infoAboutConverting.setForeground(Color.GREEN);
						infoAboutConverting02.setForeground(Color.GREEN);
						infoAboutConverting.setVisible(true);
						infoAboutConverting.setText("image has been successfully converted");
						infoAboutConverting02.setVisible(true);
						infoAboutConverting02.setText("Please check your destination Folder ");
						chooseFolderButton.setVisible(false);
					} catch (IOException exe) {
						//exe.printStackTrace();
						infoAboutConverting.setText("The file you dropped was not an actual image file");
						infoAboutConverting02.setText("Please drop the actual image file to convert");
						convertButton.setVisible(false);
						chooseFolderButton.setVisible(false);
					}
				}
			}
		});
		chooseFolderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imageConvertButton.setVisible(true);
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setCurrentDirectory(new File(".")); //it will be in our current directory
				int response = fileChooser.showOpenDialog(dragLabel);
				if (response == JFileChooser.APPROVE_OPTION) {
					folderofDestinationPath = fileChooser.getSelectedFile().getAbsolutePath();
					//textAreaForDestination.setText(fileDestinationPath);
				}
			}
		});
	}
//here goes Drag & Drop
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		//System.out.println("Drag Enter");
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
		//System.out.println("Drop Action Changed");
	}

	@Override
	public void drop(DropTargetDropEvent event) {
		chooseFolderButton.setVisible(true);
		try {
			Transferable tr = event.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) {
				if (flavors[i].isFlavorJavaFileListType()) {
					event.acceptDrop(DnDConstants.ACTION_COPY);
					textArea.append("Successfully file dropped.\n");
					List list = (List) tr.getTransferData(flavors[i]);
					for (int j = 0; j < list.size(); j++) {
						//textArea.append(list.get(j) + "\n");
						String allimageFile = list.get(i).toString();
						if (allimageFile.contains(".jpg") || allimageFile.contains(".png") || allimageFile.contains(".gif") || allimageFile.contains(".jpeg") || allimageFile.contains(".bmp")) {
							pathArray.add(allimageFile);
							infoAboutConverting.setVisible(false);
							infoAboutConverting02.setVisible(false);
						}else {
							infoAboutConverting.setText("Last Dropped item is not an image file");
							infoAboutConverting02.setText("please Drop an actual image file");
							chooseFolderButton.setVisible(false);
							imageConvertButton.setVisible(false);
						}
					}
					event.dropComplete(true);
					imageConvertButton.setVisible(false);
					return;
				}
			}
			textArea.append("Drop failed: " + event +"\n");
			event.rejectDrop();
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
			event.rejectDrop();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}