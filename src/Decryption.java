import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

public class Decryption {
	private JTextArea textAreaforDecrypted;
	private JTextArea textAreaForPlaintext;
	private JButton convertButton;
	private JTextField textFieldforKey;
	private JPanel DecryptPanael;
	private JComboBox comboBox1;
	private JButton makeItFileButton;
	private JLabel keyLabel;
	private JTextPane textPane;

	public Decryption() {
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
					textPane.setVisible(true);
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
					textPane.setVisible(false);
				}
			}
		});
	}

	public static String Base64Decode(String string) throws  IllegalArgumentException{
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
}
