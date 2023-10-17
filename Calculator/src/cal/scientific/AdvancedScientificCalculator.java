package cal.scientific;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvancedScientificCalculator {

    private JTextField textField;
    private double num1 = 0;
    private char operator;

    public static void main(String[] args) {
        new AdvancedScientificCalculator();
    }

    public AdvancedScientificCalculator() {
    	JFrame frame = new JFrame("Advanced Scientific Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 40));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 5;
        panel.add(textField, c);

        String[] buttons = {
                "sin", "cos", "tan", "sqrt", "log",
                "7", "8", "9", "/", "exp",
                "4", "5", "6", "*", "x^2",
                "1", "2", "3", "-", "x^y",
                "0", ".", "=", "+", "x!",
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
            if (col > 4) {
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
                    String result = evaluateExpression(currentText);
                    textField.setText(result);
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
            case "log":
                double logValue = Math.log(Double.parseDouble(currentText));
                textField.setText(String.valueOf(logValue));
                break;
            case "exp":
                double expValue = Math.exp(Double.parseDouble(currentText));
                textField.setText(String.valueOf(expValue));
                break;
            case "x^2":
                double squareValue = Math.pow(Double.parseDouble(currentText), 2);
                textField.setText(String.valueOf(squareValue));
                break;
            case "x^y":
                num1 = Double.parseDouble(currentText);
                operator = '^';
                textField.setText("");
                break;
            case "x!":
                int factorialValue = factorial(Integer.parseInt(currentText));
                textField.setText(String.valueOf(factorialValue));
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

    private String evaluateExpression(String expression) {
        try {
            double num2 = Double.parseDouble(expression);

            switch (operator) {
                case '+':
                    return String.valueOf(num1 + num2);
                case '-':
                    return String.valueOf(num1 - num2);
                case '*':
                    return String.valueOf(num1 * num2);
                case '/':
                    if (num2 != 0) {
                        return String.valueOf(num1 / num2);
                    } else {
                        return "Error: Division by zero!";
                    }
                case '^':
                    return String.valueOf(Math.pow(num1, num2));
                default:
                    return "Error: Invalid operator!";
            }
        } catch (Exception e) {
            return "Error";
        }
    }

    private int factorial(int n) {
        if (n < 0) {
            return -1;
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
