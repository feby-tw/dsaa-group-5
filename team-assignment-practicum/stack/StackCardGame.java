package stack;

import java.util.Scanner;

public class StackCardGame {

    public static void main(String[] args) {
        GameLogic game = new GameLogic();
        Scanner scanner = new Scanner(System.in);
        String command = "";

        System.out.println("Deck with 52 cards has been shuffled and prepared.");

        while (!command.equalsIgnoreCase("5")) {
            System.out.println("\n=============================================");
            System.out.println(game.getGameStatus());
            System.out.print("Command (draw: 1, discard: 2, undo: 3, redo: 4, exit: 5): ");
            command = scanner.next();
            String resultMessage = switch (command.toLowerCase()) {
                case "1" -> game.drawAction();
                case "2" -> game.discardAction();
                case "3" -> game.undoAction();
                case "4" -> game.redoAction();
                case "5" -> "Thanks for playing!";
                default -> "Invalid command.";
            };

            System.out.println(resultMessage);
        }
        scanner.close();
    }
}