package files.chatbot;

import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Code Interpreter Chatbot!");
        System.out.println("You can enter Java code snippets, and I'll compile and execute them for you.");
        System.out.println("Type 'exit' to quit.");

        String userInput;
        do {
            System.out.print("Enter Java code: ");
            userInput = scanner.nextLine();

            if (!userInput.equals("exit")) {
                DynamicJavaCompiler.compileAndRun(userInput);
            }

        } while (!userInput.equals("exit"));

        System.out.println("Goodbye! Have a great day.");
        scanner.close();
    }
}

