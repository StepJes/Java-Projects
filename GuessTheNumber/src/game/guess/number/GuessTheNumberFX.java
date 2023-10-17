package game.guess.number;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GuessTheNumberFX extends Application {

    private int randomNumber;
    private Label label;
    private TextField textField;
    private Button submitButton;
    private ProgressBar progressBar;
    private Label percentageLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Guess the Number Game");

        label = new Label("I've picked a number between 1 and 100. Try to guess it!");
        textField = new TextField();
        submitButton = new Button("Submit");
        progressBar = new ProgressBar(0);
        percentageLabel = new Label("");

        submitButton.setOnAction(e -> checkGuess());

        // Add event handler for Enter key press
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                checkGuess();
            }
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(label, textField, submitButton, progressBar, percentageLabel);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        randomNumber = generateRandomNumber(1, 100);
    }

    private void checkGuess() {
        String input = textField.getText();
        int guess = Integer.parseInt(input);

        int range = Math.abs(randomNumber - guess);
        double progress = 1.0 - (double) range / 100;
        progressBar.setProgress(progress);

        int percentage = (int) (progress * 100);
        percentageLabel.setText("You are " + percentage + "% close.");

        if (guess < randomNumber) {
            label.setText("Higher! Try again.");
        } else if (guess > randomNumber) {
            label.setText("Lower! Try again.");
        } else {
            label.setText("Congratulations! You've guessed the number.");
            textField.setEditable(false);
            submitButton.setDisable(true);
            percentageLabel.setText("");
        }
    }

    private int generateRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
