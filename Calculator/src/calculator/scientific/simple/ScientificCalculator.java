package calculator.scientific.simple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator {
	private JTextField textField;
	private double num1 = 0, num2 = 0, result = 0;
	private char operator;

	public static void main(String[] args) {
		new ScientificCalculator();
	}

	public ScientificCalculator() {
		JFrame frame = new JFrame("Scientific Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 400);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);

		textField = new JTextField();
		textField.setPreferredSize(new Dimension(200, 30));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		panel.add(textField, c);

		String[] buttons = {
                "sin", "cos", "tan", "sqrt",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C"
        };

		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		int row = 1, col = 0;
		for (String label : buttons) {
			JButton button = new JButton(label);
			button.setFont(new Font("Arial", Font.PLAIN, 18));

			c.gridx = col;
			c.gridy = row;
			panel.add(button, c);

			col++;
			if (col > 3) {
				col = 0;
				row++;
			}

			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String buttonText = ((JButton) e.getSource()).getText();
					handleButtonClick(buttonText);
				}
			});
		}

		frame.add(panel);
		frame.setVisible(true);
	}

	private void handleButtonClick(String value) {
		String currentText = textField.getText();
		switch (value) {
		case "=":
			try {
				num2 = Double.parseDouble(currentText);
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
					if (num2 != 0) {
						result = num1 / num2;
					} else {
						System.out.println("Error: Division by zero!");
						return;
					}
					break;
				}
				textField.setText(String.valueOf(result));
			} catch (Exception ex) {
				textField.setText("Error");
			}
			break;
		case "C":
			textField.setText("");
			break;
		case "sin":
			double sinValue = Math.sin(Double.parseDouble(currentText));
			textField.setText(String.valueOf(sinValue));
			break;
		case "cos":
			double cosValue = Math.cos(Double.parseDouble(currentText));
			textField.setText(String.valueOf(cosValue));
			break;
		case "tan":
			double tanValue = Math.tan(Double.parseDouble(currentText));
			textField.setText(String.valueOf(tanValue));
			break;
		case "sqrt":
			double sqrtValue = Math.sqrt(Double.parseDouble(currentText));
			textField.setText(String.valueOf(sqrtValue));
			break;
		default:
			if (value.matches("[0-9.]")) {
				textField.setText(currentText + value);
			} else {
				num1 = Double.parseDouble(currentText);
				operator = value.charAt(0);
				textField.setText("");
			}
		}
	}
}
