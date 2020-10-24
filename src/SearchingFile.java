import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchingFile {
	private JPanel SearchPanel;
	private JTextField textFieldforDir;
	private JButton JFileChooserButton;
	private JTextField textFieldforChooseDir;
	private JCheckBox checkBoxforAll;
	private JTextArea textArea;
	private JCheckBox checkBoxforOnlyHidden;
	private JTextField textFieldbyName;
	private JTextField textFieldbyExtention;
	private JButton buttonforSearch;
	private JCheckBox checkBoxforSubFolder;
	private JButton startSearchButton;

	public SearchingFile() {
		JFrame SearchFrame = new JFrame();
		SearchFrame.add(SearchPanel);
		SearchFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		SearchFrame.setSize(730, 500);
		SearchFrame.setLocationRelativeTo(null);
		SearchFrame.setVisible(true);


		checkBoxforAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoxforAll.isSelected()) {
					checkBoxforOnlyHidden.setSelected(false);
					checkBoxforSubFolder.setSelected(false);

					String filePath = textFieldforDir.getText();
					File fileDir = new File(filePath);

					String allFile = showAllFile(filePath);
					allFile = allFile.replace('[', '\n');
					allFile = allFile.replace(']', '\n');
					if (allFile.contains(",")) {
						allFile = allFile.replaceAll(",", "\n");
					}
					textArea.setText(allFile);
				}
			}
		});
		checkBoxforOnlyHidden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoxforOnlyHidden.isSelected()) {
					checkBoxforAll.setSelected(false);
					checkBoxforSubFolder.setSelected(false);

					String filePath = textFieldforDir.getText();
					File fileDir = new File(filePath);

					String output = showOnlyHiddenFile(filePath);
					output = output.replace('[', '\n');
					output = output.replace(']', '\n');
					if (output.contains(",")) {
						output = output.replaceAll(",", "\n");
					}
					textArea.setText(output);
				}
			}
		});
		checkBoxforSubFolder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoxforSubFolder.isSelected()) {
					checkBoxforAll.setSelected(false);
					checkBoxforOnlyHidden.setSelected(false);

					String filePath = textFieldforDir.getText();
					File fileDir = new File(filePath);

					String output = showSubFolder(filePath);
					output = output.replace('[', '\n');
					output = output.replace(']', '\n');
					if (output.contains(",")) {
						output = output.replaceAll(",", "\n");
					}
					textArea.setText(output);
				}
			}
		});
		startSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileLocation = textFieldforDir.getText();
				String name = textFieldbyName.getText();
				String outputt = searchFileByName(fileLocation, name);
				outputt = outputt.replace('[', '\n');
				outputt = outputt.replace(']', '\n');
				if (outputt.contains(",")) {
					outputt = outputt.replaceAll(",", "\n");
				}
				textArea.setText(outputt);
			}
		});
		buttonforSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String filePath = textFieldforDir.getText();
				String ext = textFieldbyExtention.getText();
				String output = searchFileByExtention(filePath, ext);
				output = output.replace('[', '\n');
				output = output.replace(']', '\n');
				if (output.contains(",")) {
					output = output.replaceAll(",", "\n");
				}
				textArea.setText(output);
			}
		});
		textFieldbyName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startSearchButton.setVisible(true);
			}
		});
		textFieldbyExtention.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonforSearch.setVisible(true);
			}
		});
	}

	public static String showAllFile(String path) {
		File folder = new File(path);
		String[] files = folder.list();
		if (Arrays.stream(files).count() == 0) {
			return "This file is empty or this is not a directory";
		}
		ArrayList<String> fileList = new ArrayList<>();
		for (String file : files) {
			System.out.println(file);
			fileList.add(file);
		}
		return fileList.toString();
	}

	public static String showSubFolder(String path) {
		File folder = new File(path);
		File[] files = folder.listFiles();
		if (Arrays.stream(files).count() == 0) {
			return "This file is empty or this is not a directory";
		}
		ArrayList<String> fileList = new ArrayList<>();
		for (File file : files) {
			if (file.isDirectory()) {
				fileList.add(file.getName());
			}
		}
		return fileList.toString();
	}

	public static String showOnlyHiddenFile(String path) {
		File folder = new File(path);
		File[] files = folder.listFiles();
		if (Arrays.stream(files).count() == 0) {
			return "This file is empty or this is not a directory";
		}
		ArrayList<String> fileList = new ArrayList<>();
		for (File file : files) {
			if (file.isHidden()) {
				fileList.add(file.getName());
			}
		}
		if (fileList.size() == 0) {
			return "No Hidden File Found";
		} else {
			return fileList.toString();
		}
	}

	public static String searchFileByName(String path, String startname) {
		File folder = new File(path);
		FilenameFilter filterFilesByName = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.contains(startname);
			}
		};
		File[] files = folder.listFiles(filterFilesByName);
		if (Arrays.stream(files).count() == 0) {
			return "No Such File Exists";
		} else {
			ArrayList<String> fileList = new ArrayList<>();
			for (File file : files) {
				fileList.add(file.getName());
			}
			return fileList.toString();
		}
	}

	public static String searchFileByExtention(String path, String ext) {
		File folder = new File(path);
		FilenameFilter filterFiles = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(ext);
			}
		};
		File[] files = folder.listFiles(filterFiles);
		if (Arrays.stream(files).count() == 0) {
			return "No Such File Exists";
		} else {
			ArrayList<String> fileList = new ArrayList<>();
			for (File file : files) {
				fileList.add(file.getName());
			}
			return fileList.toString();
		}
	}

}
// TODO: 10/24/2020 jodi file directory te file select korar aagei check box select/search button-e click kore tobe shei Exception handle korte hobe