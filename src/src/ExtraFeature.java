package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.*;
import java.awt.event.ActionEvent;

public class ExtraFeature extends Component implements ActionListener {
	private JPanel gamePanel;
	private JPanel JpanelForReward;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;

	public ExtraFeature() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		button8.addActionListener(this);
		button9.addActionListener(this);

		JFrame gameFrame = new JFrame();
		gameFrame.add(gamePanel);
		gameFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		gameFrame.setSize(450, 450);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		if (arg.getSource() == button1) {
			String label = button1.getText();
			if (button2.getText().equals("")) {
				button2.setText(label);
				button2.setBackground(Color.CYAN);
				button1.setText("");
				button1.setBackground(Color.green);
			}
			if (button4.getText().equals("")) {
				button4.setText(label);
				button4.setBackground(Color.CYAN);
				button1.setText("");
				button1.setBackground(Color.green);
			}
		}
		if (arg.getSource() == button2) {
			String label = button2.getText();
			if (button1.getText().equals("")) {
				button1.setText(label);
				button1.setBackground(Color.CYAN);
				button2.setText("");
				button2.setBackground(Color.green);
			}
			if (button3.getText().equals("")) {
				button3.setText(label);
				button3.setBackground(Color.CYAN);
				button2.setText("");
				button2.setBackground(Color.green);
			}
			if (button5.getText().equals("")) {
				button5.setText(label);
				button5.setBackground(Color.CYAN);
				button2.setText("");
				button2.setBackground(Color.green);
			}
		}
		if (arg.getSource() == button3) {
			String label = button3.getText();
			if (button2.getText().equals("")) {
				button2.setText(label);
				button2.setBackground(Color.CYAN);
				button3.setText("");
				button3.setBackground(Color.green);
			}
			if (button6.getText().equals("")) {
				button6.setText(label);
				button6.setBackground(Color.CYAN);
				button3.setText("");
				button3.setBackground(Color.green);
			}
		}
		if (arg.getSource() == button4) {
			String label = button4.getText();
			if (button1.getText().equals("")) {
				button1.setText(label);
				button1.setBackground(Color.CYAN);
				button4.setText("");
				button4.setBackground(Color.green);
			}
			if (button5.getText().equals("")) {
				button5.setText(label);
				button5.setBackground(Color.CYAN);
				button4.setText("");
				button4.setBackground(Color.green);
			}
			if (button7.getText().equals("")) {
				button7.setText(label);
				button7.setBackground(Color.CYAN);
				button4.setText("");
				button4.setBackground(Color.green);
			}
		}
		if (arg.getSource() == button5) {
			String label = button5.getText();
			if (button2.getText().equals("")) {
				button2.setText(label);
				button2.setBackground(Color.CYAN);
				button5.setText("");
				button5.setBackground(Color.green);
			}
			if (button4.getText().equals("")) {
				button4.setText(label);
				button4.setBackground(Color.CYAN);
				button5.setText("");
				button5.setBackground(Color.green);
			}
			if (button6.getText().equals("")) {
				button6.setText(label);
				button6.setBackground(Color.CYAN);
				button5.setText("");
				button5.setBackground(Color.green);
			}
			if (button8.getText().equals("")) {
				button8.setText(label);
				button8.setBackground(Color.CYAN);
				button5.setText("");
				button5.setBackground(Color.green);
			}
		}
		if (arg.getSource() == button6) {
			String label = button6.getText();
			if (button3.getText().equals("")) {
				button3.setText(label);
				button3.setBackground(Color.CYAN);
				button6.setText("");
				button6.setBackground(Color.green);
			}
			if (button5.getText().equals("")) {
				button5.setText(label);
				button5.setBackground(Color.CYAN);
				button6.setText("");
				button6.setBackground(Color.green);
			}
			if (button9.getText().equals("")) {
				button9.setText(label);
				button9.setBackground(Color.CYAN);
				button6.setText("");
				button6.setBackground(Color.green);
			}
		}
		if (arg.getSource() == button7) {
			String label = button7.getText();
			if (button4.getText().equals("")) {
				button4.setText(label);
				button4.setBackground(Color.CYAN);
				button7.setText("");
				button7.setBackground(Color.green);
			}
			if (button8.getText().equals("")) {
				button8.setText(label);
				button8.setBackground(Color.CYAN);
				button7.setText("");
				button7.setBackground(Color.green);
			}
		}
		if (arg.getSource() == button8) {
			String label = button8.getText();
			if (button7.getText().equals("")) {
				button7.setText(label);
				button7.setBackground(Color.CYAN);
				button8.setText("");
				button8.setBackground(Color.green);
			}
			if (button5.getText().equals("")) {
				button5.setText(label);
				button5.setBackground(Color.CYAN);
				button8.setText("");
				button8.setBackground(Color.green);
			}
			if (button9.getText().equals("")) {
				button9.setText(label);
				button9.setBackground(Color.CYAN);
				button8.setText("");
				button8.setBackground(Color.green);
			}
		}
		if (arg.getSource() == button9) {
			String label = button9.getText();
			if (button8.getText().equals("")) {
				button8.setText(label);
				button8.setBackground(Color.CYAN);
				button9.setText("");
				button9.setBackground(Color.green);
			}
			if (button6.getText().equals("")) {
				button6.setText(label);
				button6.setBackground(Color.CYAN);
				button9.setText("");
				button9.setBackground(Color.green);
			}
		}
		///winning condition
		if (button1.getText().equals("1") && button2.getText().equals("2") && button3.getText().equals("3") &&
				button4.getText().equals("4") && button5.getText().equals("5") && button6.getText().equals("6") &&
				button7.getText().equals("7") && button8.getText().equals("8") && button9.getText().equals("")) {
			JOptionPane.showMessageDialog(JpanelForReward, "we have got Genius!!");
		}

	}

}
