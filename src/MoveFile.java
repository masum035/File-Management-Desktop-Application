import javax.swing.*;

public class MoveFile {
	private JTextField sourceDirectoryTextField;
	private JTextField destinitionPathTextField;
	private JButton moveButton;
	private JScrollBar scrollBar1;
	private JScrollBar scrollBar2;
	private JPanel Movepanel;

	public MoveFile(){
		JFrame moveFileFrame = new JFrame();
		moveFileFrame.add(Movepanel);
		moveFileFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		moveFileFrame.setSize(800,210);
		moveFileFrame.setLocationRelativeTo(null);
		moveFileFrame.setVisible(true);

	}

}
