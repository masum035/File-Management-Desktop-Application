package src;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Encryption implements DropTargetListener {
	private JPanel panel1;
	private JTextArea textAreaForDragandDrop;
	private JTextArea textAreaForInfo;
	private JTextArea textAreaforEncrypt;
	private JTextArea textAreaForDecrypt;
	private JComboBox comboBox1;
	private JButton MessageSaveButton;
	private JButton fileSaveButton;
	private JButton makeItFileButton;
	private JTextPane TextPane;
	private JButton convertButton;
	private JButton chooseFolderButton;
	private JTextField textFieldforKey;
	private JCheckBox checkBoxForFileEncryption;
	private JLabel labelForKey;
	private JPanel panelForMessageEncrypt;
	private JButton chooseFolderButtonForDragAndDrop;
	private JPanel infoLabelForDragAndDrop;
	private JScrollPane scrollPaneForPlainText;
	private JScrollPane scrollPaneForEncryption;

	ArrayList<String> pathArray = new ArrayList<>();
	String fileDestinationPath = "";
	String outputFilePath = "";

	public Encryption() {
		DropTarget dt = new DropTarget(textAreaForDragandDrop, this);
		JFrame encryptFrame = new JFrame();
		encryptFrame.add(panel1);
		encryptFrame.setSize(900, 650);
		encryptFrame.setLocationRelativeTo(null);
		encryptFrame.setVisible(true);
		encryptFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);


		comboBox1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Base64
				if (comboBox1.getSelectedIndex() == 0) {
					String input = textAreaforEncrypt.getText();
					String output = Base64encode(input);
					textAreaForDecrypt.setText(output);
				}
				//Caeser
				if (comboBox1.getSelectedIndex() == 1) {

				}
				//AtBash
				if (comboBox1.getSelectedIndex() == 2) {
					String input = textAreaforEncrypt.getText();
					String output = AtBashEncryption(input);
					textAreaForDecrypt.setText(output);
				}
				//Rot47
				if (comboBox1.getSelectedIndex() == 3) {

				}
				//Hex
				if (comboBox1.getSelectedIndex() == 4) {

				}
				//XOR
				if (comboBox1.getSelectedIndex() == 5) {
					labelForKey.setVisible(true);
					textFieldforKey.setVisible(true);
					convertButton.setVisible(true);
				}

			}
		});

		convertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox1.getSelectedIndex() == 5) {
					String input = textAreaforEncrypt.getText();
					String Key = textFieldforKey.getText();
					String output = xorEncryption(input, Key);
					textAreaForDecrypt.setText(output);
				}
				convertButton.setVisible(false);
				textFieldforKey.setVisible(false);
				labelForKey.setVisible(false);
			}
		});
		chooseFolderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MessageSaveButton.setVisible(true);
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setCurrentDirectory(new File(".")); //it will be in our current directory
				int response = fileChooser.showOpenDialog(textFieldforKey);
				if (response == JFileChooser.APPROVE_OPTION) {
					fileDestinationPath = fileChooser.getSelectedFile().getAbsolutePath();
					//textAreaForDestination.setText(fileDestinationPath);
				}
			}
		});
		MessageSaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileName = "\\CryptedFile.txt";
				File file = new File(fileDestinationPath + fileName);
				try {
					FileWriter fileWriter = new FileWriter(file);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					bufferedWriter.write(textAreaForDecrypt.getText());
					bufferedWriter.close();
					fileWriter.close();
					JOptionPane.showMessageDialog(textFieldforKey, "Encrypted File has been successfully saved as 'CryptedFile.txt' in selected directory\nThanks for using this programme", "File Saved", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		});

		chooseFolderButtonForDragAndDrop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileSaveButton.setVisible(true);
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setCurrentDirectory(new File(".")); //it will be in our current directory
				int response = fileChooser.showOpenDialog(textFieldforKey);
				if (response == JFileChooser.APPROVE_OPTION) {
					outputFilePath = fileChooser.getSelectedFile().getAbsolutePath();
					//textAreaForDestination.setText(fileDestinationPath);
				}
			}
		});
		fileSaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelForMessageEncrypt.setVisible(true);

				String secretkey = "mYnAMeiSMaSumVai"; // exactly 16 byte disi
				for (int i = 0; i < pathArray.size(); i++) {
					String fileinputPath = pathArray.get(i);
					String extention = "";
					int lastDotPosition = 0;
					if (fileinputPath.contains(".")) {
						lastDotPosition = fileinputPath.lastIndexOf('.');
						extention = fileinputPath.substring(lastDotPosition + 1, fileinputPath.length());
					}
					int lastSlashPosition =  fileinputPath.lastIndexOf('\\');
					String actualFileName = fileinputPath.substring(lastSlashPosition + 1,lastDotPosition);
					String encryptedFileName = "Encrypted_" + actualFileName;
					String outputFileLocation = outputFilePath + "\\" + encryptedFileName + "." + extention;
					try {
						if (directFileEncrytion(secretkey, fileinputPath, outputFileLocation)) {
							JOptionPane.showMessageDialog(textAreaforEncrypt, "File has Been Saved as " + encryptedFileName +" in your choosen directory", "File Encryption Success",JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (NoSuchPaddingException noSuchPaddingException) {
						noSuchPaddingException.printStackTrace();
					} catch (NoSuchAlgorithmException noSuchAlgorithmException) {
						noSuchAlgorithmException.printStackTrace();
					} catch (InvalidKeyException invalidKeyException) {
						invalidKeyException.printStackTrace();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					} catch (BadPaddingException badPaddingException) {
						badPaddingException.printStackTrace();
					} catch (IllegalBlockSizeException illegalBlockSizeException) {
						illegalBlockSizeException.printStackTrace();
					}
				}
			}
		});
	}

	// TODO: 10/24/2020 rot47 and Hex encryption Bakki ase 
	public static String Base64encode(String string) {
		Base64.Encoder encoder = Base64.getEncoder();
		byte[] encoded = encoder.encode(string.getBytes());
		return new String(encoded);
	}

	public static String AtBashEncryption(String string) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		alphabet = alphabet.toLowerCase();
		String reverseAlphabet = "";
		for (int i = alphabet.length() - 1; i >= 0; i--) {
			reverseAlphabet += alphabet.charAt(i);
		}
		String inp = string.toLowerCase();
		String out = "";
		for (int i = 0; i < string.length(); i++) {
			if (inp.charAt(i) == (char) 32) {
				out += " "; //space remove kore disi
			} else if (96 < inp.charAt(i) && inp.charAt(i) < 123) {
				for (int j = 0; j < reverseAlphabet.length(); j++) {
					if (inp.charAt(i) == reverseAlphabet.charAt(j)) {
						out += alphabet.charAt(j);
						break;
					}
				}
			} else {
				out += inp.charAt(i);
			}
		}
		return out;
	}

	public static String xorEncryption(String string, String key) {
		String encrypText = "";
		//System.out.println(string.length());
		int keycount = 0;
		for (int i = 0; i < string.length(); i++) {
			int temp = string.charAt(i) ^ key.charAt(keycount); //XOR
			encrypText += (char) temp;
			keycount++;
			if (keycount >= key.length()) {
				// jodi key length excess kore tobe abar prothom theke
				keycount = 0;
			}
		}
		return encrypText;
	}

	public static boolean directFileEncrytion(String secretKey, String fileinputPath, String fileoutputPath) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {
		var key = new SecretKeySpec(secretKey.getBytes(), "AES");
		var cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		//here i read the file & take it into array
		var fileInput = new File(fileinputPath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fileInput);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "After Dropping the File here, you might renamed/deleted a file from Original Source", "File not Found",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		var inputBytes = new byte[(int) fileInput.length()];
		inputStream.read(inputBytes);

		//here i am calling the encryption process
		try {
			var outputBytes = cipher.doFinal(inputBytes);

			//now i want to add this outputStram into a new file
			var fileEncryptOut = new File(fileoutputPath);
			var outputStream = new FileOutputStream(fileEncryptOut);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "This file can not be encrypted right now.\nTry Again Later", "Couldn't Encrypt", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
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
		textAreaForDragandDrop.append("\n");
		chooseFolderButtonForDragAndDrop.setVisible(true);
		infoLabelForDragAndDrop.setVisible(true);
		panelForMessageEncrypt.setVisible(false);

		try {
			Transferable tr = event.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) {
				if (flavors[i].isFlavorJavaFileListType()) {
					event.acceptDrop(DnDConstants.ACTION_COPY);
					List list = (List) tr.getTransferData(flavors[i]);
					for (int j = 0; j < list.size(); j++) {
						//textAreaForDragandDrop.append(list.get(j) + "\n");
						textAreaForDragandDrop.append("File/Folder has been Successfully dropped\n");
						pathArray.add((list.get(j)).toString());
					}
					event.dropComplete(true);
					return;
				}
			}
			textAreaForDragandDrop.append("Drop failed: " + event);
			event.rejectDrop();
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
			event.rejectDrop();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
