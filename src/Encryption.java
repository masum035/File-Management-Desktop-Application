import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.util.Objects;
import java.util.Scanner;

public class Encryption {
	private JPanel panel1;
	private JTextArea textAreaforEncrypt;
	private JTextArea textAreaForDecrypt;
	private JTextField textFieldforKey;
	private JComboBox comboBox1;
	private JButton makeItFileButton;
	private JButton chooseFileDirButton;
	private JTextPane TextPane;
	private JLabel keyLabel;
	private JButton convertButton;

	public Encryption() {
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

			}
		});
		makeItFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TextPane.setVisible(true);
				chooseFileDirButton.setVisible(true);
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
}
