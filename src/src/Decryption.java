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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Decryption implements DropTargetListener {
	private JTextArea textAreaforDecrypted;
	private JTextArea textAreaForPlaintext;
	private JButton convertButton;
	private JTextField textFieldforKey;
	private JPanel DecryptPanael;
	private JComboBox comboBox1;
	private JButton makeItFileButton;
	private JLabel keyLabel;
	private JButton chooserForTextSave;
	private JButton saveButtonForTxt;
	private JButton chooserForFileSave;
	private JButton saveBtnforFileSave;
	private JTextArea textAreaForDragandDrop;

	ArrayList<String> pathArray = new ArrayList<>();
	String folderofDestinationPathforTxt = null;
	String folderofDestinationPathforFileSave = null;

	public Decryption() {
		DropTarget dt = new DropTarget(textAreaForDragandDrop, this);
		JFrame DecryptFrame = new JFrame();
		DecryptFrame.add(DecryptPanael);
		DecryptFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		DecryptFrame.setSize(900, 650);
		DecryptFrame.setLocationRelativeTo(null);
		DecryptFrame.setVisible(true);

		comboBox1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Base64
				if (comboBox1.getSelectedIndex() == 0) {
					String input = textAreaforDecrypted.getText();
					String output = Base64Decode(input);
					textAreaForPlaintext.setText(output);
				}
				//Caeser
				if (comboBox1.getSelectedIndex() == 1) {

				}
				//AtBash
				if (comboBox1.getSelectedIndex() == 2) {
					String input = textAreaforDecrypted.getText();
					String output = AtBashDecryption(input);
					textAreaForPlaintext.setText(output);
				}
				//Rot47
				if (comboBox1.getSelectedIndex() == 3) {

				}
				//Hex
				if (comboBox1.getSelectedIndex() == 4) {

				}
				//XOR
				if (comboBox1.getSelectedIndex() == 5) {
					keyLabel.setVisible(true);
					textFieldforKey.setVisible(true);
					convertButton.setVisible(true);
				}

			}
		});
		convertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox1.getSelectedIndex() == 5) {
					String input = textAreaforDecrypted.getText();
					String key = textFieldforKey.getText();
					String output = xorDecryption(input, key);
					textAreaForPlaintext.setText(output);

					keyLabel.setVisible(false);
					textFieldforKey.setVisible(false);
					convertButton.setVisible(false);
				}
			}
		});

		chooserForTextSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonForTxt.setVisible(true);
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setCurrentDirectory(new File(".")); //it will be in our current directory
				int response = fileChooser.showOpenDialog(textAreaforDecrypted);
				if (response == JFileChooser.APPROVE_OPTION) {
					folderofDestinationPathforTxt = fileChooser.getSelectedFile().getAbsolutePath();
					//textAreaForDestination.setText(fileDestinationPath);
				}
			}
		});
		saveButtonForTxt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileName = "\\deCryptedFile.txt";
				File file = new File(folderofDestinationPathforTxt + fileName);
				try {
					FileWriter fileWriter = new FileWriter(file);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					bufferedWriter.write(textAreaForPlaintext.getText());
					bufferedWriter.close();
					fileWriter.close();
					JOptionPane.showMessageDialog(textFieldforKey, "Decrypted File has been successfully saved as 'deCryptedFile.txt' in selected directory\nThanks for using this programme", "File Saved", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		});
		chooserForFileSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveBtnforFileSave.setVisible(true);
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setCurrentDirectory(new File(".")); //it will be in our current directory
				int response = fileChooser.showOpenDialog(textAreaforDecrypted);
				if (response == JFileChooser.APPROVE_OPTION) {
					folderofDestinationPathforFileSave = fileChooser.getSelectedFile().getAbsolutePath();
					//textAreaForDestination.setText(fileDestinationPath);
				}
			}
		});
		saveBtnforFileSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
					String decryptedFileName = "Decrypted_Of_" + actualFileName;
					String outputFileLocation = folderofDestinationPathforFileSave + "\\" + decryptedFileName + "." + extention;
					// TODO: 11/8/2020 fix the error for file decrypt 
					try {
						if (directFileDecrytion(secretkey, fileinputPath, outputFileLocation)) {
							JOptionPane.showMessageDialog(textAreaforDecrypted, "File has Been Saved as " + decryptedFileName + " in your choosen directory", "File Encryption Success", JOptionPane.INFORMATION_MESSAGE);
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

	public static String Base64Decode(String string) throws IllegalArgumentException {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] decoded = decoder.decode(string);
		return new String(decoded);
	}

	public static String AtBashDecryption(String string) {
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
					if (inp.charAt(i) == alphabet.charAt(j)) {
						out += reverseAlphabet.charAt(j);
						break;
					}
				}
			} else {
				out += inp.charAt(i);
			}
		}
		return out;
	}

	public static String xorDecryption(String string, String key) {
		String encrypText = "";
		System.out.println(string.length());
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

	public static boolean directFileDecrytion(String secretKey, String fileinputPath, String fileoutputPath) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {
		var key = new SecretKeySpec(secretKey.getBytes(), "AES");
		var cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);

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

		//here i am calling the decryption process
		byte[] outputBytes = new byte[0];
		try {
			outputBytes = cipher.doFinal(inputBytes);
			var fileEncryptOut = new File(fileoutputPath);
			//now i want to add this outputStram into a new file
			var outputStream = new FileOutputStream(fileEncryptOut);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();
			return true;
		} catch (Exception exx) {
			JOptionPane.showMessageDialog(null, "The File You Dropped isn't encrypted by our software.So,we couldn't Decrypt it.", "Couldn't Decrypt", JOptionPane.ERROR_MESSAGE);
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
		textAreaForDragandDrop.append("\n");
		chooserForFileSave.setVisible(true);
		//infoLabelForDragAndDrop.setVisible(true);
		//panelForMessageEncrypt.setVisible(false);

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
