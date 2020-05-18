import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieGuessGame game = new MovieGuessGame();
        Scanner letterScanner = new Scanner(System.in);

        System.out.println("Game: Guess the movie");
        // Shows the movie to guess
        // System.out.println(game.getMovieToGuess());

        while(game.getWrongGuesses() < 10 && !game.getWinStatus()){
            System.out.println("\n===================================================================");
            System.out.println("You are guessing: " + game.getStringToShow());
            System.out.println("You have guessed (" + game.getWrongGuesses() + ") wrong letter(s).");
            System.out.println("Guess a letter: ");
            // Gets user input (letter to guess)
            try {
                // Gets the user input (letter to guess the movie)
                game.guessLetter(letterScanner.nextLine().toLowerCase());
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("======================");
                System.out.println("You must type a letter");
                System.out.println("======================");
            }
            System.out.println("===================================================================");
        }

        // Checks if the user won or lost
        System.out.println("\n===================================================================");
        if(game.getWinStatus()) {
            System.out.println("You won!");
            System.out.println("You have guessed \"" + game.getMovieToGuess() + "\" correctly.");
        } else {
            System.out.println("You lost! The movie to guess was \"" + game.getMovieToGuess() + "\".");
        }
        System.out.println("===================================================================");
    }
}
