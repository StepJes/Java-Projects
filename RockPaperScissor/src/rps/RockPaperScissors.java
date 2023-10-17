package rps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissors extends JFrame implements ActionListener {

    private JButton rockButton, paperButton, scissorsButton;
    private JLabel resultLabel, scoreLabel;
    private int playerScore = 0, computerScore = 0;
    private Random random;

    public RockPaperScissors() {
        setTitle("Rock, Paper, Scissors Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");
        resultLabel = new JLabel("Choose your move:");
        scoreLabel = new JLabel("Score: Player - 0, Computer - 0");
        random = new Random();

        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        setLayout(new BorderLayout());
        add(resultLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scoreLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        String playerMove = sourceButton.getText();
        int playerChoice = getPlayerChoice(playerMove);
        int computerChoice = random.nextInt(3) + 1;
        String computerMove = getMove(computerChoice);
        String result = determineWinner(playerChoice, computerChoice);

        resultLabel.setText("You chose: " + playerMove + ". Computer chose: " + computerMove + ". " + result);

        if (result.contains("win")) {
            playerScore++;
        } else if (result.contains("lose")) {
            computerScore++;
        }

        scoreLabel.setText("Score: Player - " + playerScore + ", Computer - " + computerScore);

        if (playerScore == 5 || computerScore == 5) {
            showGameOverDialog();
        }
    }

    private int getPlayerChoice(String move) {
        switch (move) {
            case "Rock":
                return 1;
            case "Paper":
                return 2;
            case "Scissors":
                return 3;
            default:
                return -1; // Invalid choice
        }
    }

    private String getMove(int choice) {
        switch (choice) {
            case 1:
                return "Rock";
            case 2:
                return "Paper";
            case 3:
                return "Scissors";
            default:
                return "Invalid choice";
        }
    }

    private String determineWinner(int playerChoice, int computerChoice) {
        if (playerChoice == computerChoice) {
            return "It's a tie!";
        } else if ((playerChoice == 1 && computerChoice == 3) ||
                (playerChoice == 2 && computerChoice == 1) ||
                (playerChoice == 3 && computerChoice == 2)) {
            return "You win!";
        } else {
            return "You lose!";
        }
    }

    private void showGameOverDialog() {
        String message;
        if (playerScore == 5) {
            message = "Congratulations! You've won the game!";
        } else {
            message = "You've lost the game. Better luck next time!";
        }

        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        new RockPaperScissors();
    }
}
