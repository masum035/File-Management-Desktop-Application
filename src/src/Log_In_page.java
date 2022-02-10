package src;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Log_In_page implements ActionListener {
	private JPanel Loginpanel;
	private JTextField textField_for_name;
	private JPasswordField passwordField;
	private JButton logInButton;
	private JButton logOutButton;
	public String password;
	static int flag = 0;

	public Log_In_page() {

		logInButton.addActionListener(this);
		logOutButton.addActionListener(this);

		logInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Write need modification
				String[] arr = new String[100];
				// TODO: 10/20/2020 Ekbar log in korle previous information delete hoye okhane new info append hoitese,How to prevent it?

				if (textField_for_name.getText() == "" || String.valueOf(passwordField.getPassword()) == "") {
					flag = -1;

					// TODO: 10/22/2020 userke badha dite hobe jeno she r shamne na egote pare
				} else {
					arr[0] = textField_for_name.getText();
					arr[1] = String.valueOf(passwordField.getPassword());
				}
				File file = new File("Sample.csv");
				try {
					CSVWriter writer = null;
					FileWriter writeFile = null;
					writeFile = new FileWriter(file);
					writer = new CSVWriter(writeFile);

					if (!file.exists() && flag != -1) {
						writeFile = new FileWriter("Sample.csv");
						writer = new CSVWriter(writeFile);
						writer.writeNext(arr);
						writer.flush();
					}
					if (file.exists() && flag != -1) {
						writer.writeNext(arr);
						writer.flush();
					}
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				//Read is alright
				String username = textField_for_name.getText();
				password = String.valueOf(passwordField.getPassword());
				CSVReader reader = null;
				String[] readArray = new String[5];
				try {
					reader = new CSVReaderBuilder(new FileReader("Sample.csv")).withSkipLines(0).build();

					while ((readArray = reader.readNext()) != null) {
						if (flag == 0) {
							JOptionPane.showMessageDialog(null, "Log in Failed", "Log In ", JOptionPane.WARNING_MESSAGE);
						}
						if (flag == -1) {
							JOptionPane.showMessageDialog(null, "Field is Empty,Try again Please", "Log In ", JOptionPane.ERROR_MESSAGE);
						}
						if (username.equals(readArray[0]) && password.equals(readArray[1])) {
							flag = 1;
							JOptionPane.showMessageDialog(null, "Log in successFull", "Log In ", JOptionPane.INFORMATION_MESSAGE);
							break;
						}
					}
				} catch (FileNotFoundException notFoundException) {
					notFoundException.printStackTrace();
				} catch (CsvValidationException validationException) {
					validationException.printStackTrace();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Log_In_page logInObj = new Log_In_page();
				JFrame LogInFrame = new JFrame();
				LogInFrame.add(logInObj.Loginpanel);
				LogInFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				LogInFrame.setSize(360, 410);
				LogInFrame.setLocationRelativeTo(null);
				LogInFrame.setVisible(true);
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logInButton) {
			mainFrame mainFrameObj = new mainFrame();
		}
		if (e.getSource() == logOutButton) {
			System.exit(0);
		}
	}
}
