import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MoveFile {
	private JTextField sourceDirectoryTextField;
	private JTextField destinitionPathTextField;
	private JButton moveButton;
	private JScrollBar scrollBar1;
	private JScrollBar scrollBar2;
	private JPanel Movepanel;
	private JLabel infoLabel;

	public MoveFile() {
		JFrame moveFileFrame = new JFrame();
		moveFileFrame.add(Movepanel);
		moveFileFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		moveFileFrame.setSize(800, 235);
		moveFileFrame.setLocationRelativeTo(null);
		moveFileFrame.setVisible(true);

		moveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String des, src;
				src = sourceDirectoryTextField.getText();
				des = destinitionPathTextField.getText();
				File srcFile = new File(src);
				if(!srcFile.isDirectory()) {
					int lastSlashPosition = des.lastIndexOf('\\');
					String desFileLocation = des.substring(0, lastSlashPosition);
					des = desFileLocation + "\\MasumProduction";

					if (src.contains(".")) {
						int lastDotPosition = src.lastIndexOf('.');
						String fileExtention = src.substring(lastDotPosition+1, src.length());
						des = desFileLocation + "\\MasumProduction." + fileExtention;
					}
				}else{
					des = des + "\\MasumProduction";
				}
				String finalDes = des;
				String finalSrc = src;

				try {
					File file = new File(finalSrc);
					if (file.renameTo(new File(finalDes))) {
						file.delete();
						infoLabel.setForeground(Color.GREEN);
						infoLabel.setText("File moved Successfully");
					} else {
						infoLabel.setForeground(Color.RED);
						infoLabel.setText("File can not be moved.please check the source or destination file name");
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
	}

}
