import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieGuessGame {
    // Creates file object to read the movie titles
    private File file;
    private Scanner fileScanner;
    private ArrayList<String> movies;
    private String movieToGuess;
    private StringBuilder stringToShow;
    private int moviesCount;
    private int movieToGuessIndex;
    private int wrongGuesses;
    private boolean win;

    MovieGuessGame(){
        this.file = new File("movies.txt");
        try {
            this.fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found exception");
        }
        this.movies = new ArrayList<>();
        this.stringToShow = new StringBuilder("");
        this.moviesCount = 0;
        this.movieToGuessIndex = 0;
        this.wrongGuesses = 0;
        this.win = false;
        this.createsMovieList();
        this.selectsMovieToGuess();
        this.fillStringToShow();
        this.showSpaces();
    }

    // Creates the list with the movie titles and count the number of movie titles
    private void createsMovieList() {
        while(this.fileScanner.hasNextLine()) {
            this.movies.add(this.fileScanner.nextLine());
            this.moviesCount++;
        }
    }

    // Gets a movie randomly from the list
    private void selectsMovieToGuess() {
        this.movieToGuessIndex = (int) (Math.random() * this.moviesCount);
        this.movieToGuess = this.movies.get(this.movieToGuessIndex);
    }

    // Fill string with "_"'s to show to the user
    private void fillStringToShow() {
        for (int i = 0; i < movieToGuess.length(); i++) {
            stringToShow.append("_");
        }
    }

    // Replace "_"'s with spaces
    private void showSpaces() {
        if(movieToGuess.indexOf(' ') != -1) {
            this.replaceLetterInString(' ');
        }
    }

    // Play the game trying to guess one letter of the movie
    public void guessLetter(String letter) {

        char firstLetter = letter.toLowerCase().charAt(0);

        if(movieToGuess.indexOf(firstLetter) != -1) {
            // Movie to guess contains the letter input by the user
            this.replaceLetterInString(firstLetter);
        } else {
            // Movie to guess does not contains the letter input by the user
            this.wrongGuesses++;
        }
        // Check is the string to show is completed and is equal to the movie to guess
        if(this.movieToGuess.equals(this.stringToShow.toString())) {
            this.win = true;
        }
    }

    // Replaces the "_"'s in the string with the letter guessed
    public void replaceLetterInString(char letter) {
        int index = 0;
        int indexOfLetterOccurrence = 0;
        // Replaces the occurrences of the input letter in the string shown to the user
        while (indexOfLetterOccurrence != -1 && index < this.movieToGuess.length()) {
            // Gets the index of the next occurrence of the chosen letter in the String
            indexOfLetterOccurrence = this.movieToGuess.indexOf(letter, index);
            if (indexOfLetterOccurrence != -1) {
                // Replaces the "_" with the letter chosen by the user
                this.stringToShow.setCharAt(indexOfLetterOccurrence, letter);
                index = indexOfLetterOccurrence + 1;
            }
        }
    }

    // Gets the number of wrong guesses
    public int getWrongGuesses() {
        return this.wrongGuesses;
    }

    // Return game status
    public boolean getWinStatus() {
        return this.win;
    }

    // Gets movie to guess
    public String getMovieToGuess() {
        return this.movieToGuess;
    }

    // Gets String to Show (String filled with "_"'s)
    public String getStringToShow() {
        return this.stringToShow.toString();
    }
}