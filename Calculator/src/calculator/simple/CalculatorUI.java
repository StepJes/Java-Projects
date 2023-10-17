package calculator.simple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUI extends JFrame implements ActionListener {
	private JTextField textField;
	private JButton[] numberButtons = new JButton[10];
	private JButton[] functionButtons = new JButton[4];
	private JButton addButton, subButton, mulButton, divButton, equButton, clrButton;
	private JPanel panel;

	private double num1 = 0, num2 = 0, result = 0;
	private char operator;

	public CalculatorUI() {
		// Set dark theme
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Create the frame
		setTitle("Simple Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 400);
		setResizable(false);

		// Create the text field
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(280, 50));
		textField.setFont(new Font("Arial", Font.PLAIN, 24));
		textField.setHorizontalAlignment(JTextField.RIGHT);

		// Create buttons
		for (int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
			numberButtons[i].addActionListener(this);
		}

		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		equButton = new JButton("=");
		clrButton = new JButton("C");

		JButton[] functionButtons = { addButton, subButton, mulButton, divButton, equButton, clrButton };
		for (JButton button : functionButtons) {
			button.setFont(new Font("Arial", Font.PLAIN, 18));
			button.addActionListener(this);
		}

		// Create the panel and add components
		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4, 10, 10));

		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);

		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);

		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);

		panel.add(clrButton);
		panel.add(numberButtons[0]);
		panel.add(equButton);
		panel.add(divButton);

		// Add components to the frame
		add(textField, BorderLayout.NORTH);
		add(panel);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 10; i++) {
			if (e.getSource() == numberButtons[i]) {
				textField.setText(textField.getText().concat(String.valueOf(i)));
			}
		}

		if (e.getSource() == addButton) {
			num1 = Double.parseDouble(textField.getText());
			operator = '+';
			textField.setText("");
		}

		if (e.getSource() == subButton) {
			num1 = Double.parseDouble(textField.getText());
			operator = '-';
			textField.setText("");
		}

		if (e.getSource() == mulButton) {
			num1 = Double.parseDouble(textField.getText());
			operator = '*';
			textField.setText("");
		}

		if (e.getSource() == divButton) {
			num1 = Double.parseDouble(textField.getText());
			operator = '/';
			textField.setText("");
		}

		if (e.getSource() == equButton) {
			num2 = Double.parseDouble(textField.getText());

			switch (operator) {
			case '+':
				result = num1 + num2;
				break;
			case '-':
				result = num1 - num2;
				break;
			case '*':
				result = num1 * num2;
				break;
			case '/':
				result = num1 / num2;
				break;
			}

			textField.setText(String.valueOf(result));
			num1 = result;
		}

		if (e.getSource() == clrButton) {
			textField.setText("");
		}
	}

	public static void main(String[] args) {
		new CalculatorUI();
	}
}
