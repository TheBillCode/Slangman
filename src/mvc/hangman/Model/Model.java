package mvc.hangman.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Model {

    private final static File FILE_NAME = new File("HangManWords.txt"); //dictionary file
    private static Map<String, String> dictionary = new HashMap<String, String>(); //vocab words

    static private String word;
    static private String usedLetters = "abcd";
    static private LinkedHashMap<Character, Boolean> wordMap = new LinkedHashMap<>();
    static Boolean[] manParts = {false, false, false, false, false, false}; //body parts
    static private String gameOver = "";

    public Model() {
        //load all words from dictionary file into map dictionary
        buildDicitonary();
        //Choose a random word from map keys
        if (word == null) {
            getNewWord();
            mapWord();
        }        
    }

    private void mapWord() {
        for (int i = 0; i < word.length(); i++) {
            wordMap.put(word.charAt(i), false);
        }

    }

    /**
     * Load the dictionary file and build the dictionary map.
     */
    private static void buildDicitonary() {
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(FILE_NAME); //check if file exists
            while (fileIn.hasNext()) {
                String tk = fileIn.nextLine();
                if (tk.contains("HEADER:")) { //do not inlcude in dictionary
                    continue;
                } else { //add term and definition to dictionary
                    String[] term = tk.split(":");
                    dictionary.put(term[0].trim().toLowerCase(), term[1].trim());
                }
            }
        } catch (FileNotFoundException e) { //file not found in root directory
            System.err.println("File not found in root directory.");
            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException f) { //skip term
            System.err.println("There was an error in the dictionary file, "
                    + "all terms may not have been loaded");
        }

    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     *
     * @return the definition of word
     */
    public String getDefinition() {
        return dictionary.get(word);
    }

    /**
     * @return the usedLetters
     */
    public String getUsedLetters() {
        return usedLetters;
    }

    public void getNewWord() {
        ArrayList<String> allTerms = new ArrayList<String>(dictionary.keySet());
        Random r = new Random();
        this.word = allTerms.get(r.nextInt(allTerms.size()));
        mapWord();
        getWord();
        initManParts(); 
    }
    
    private void initManParts() {
        for (int i = 0; i < manParts.length; i++) {
            manParts[i] = false;
        }
    }

    public LinkedHashMap<Character, Boolean> getWordMap() {
        return wordMap;
    }

    public boolean isLetterFound(char c) {
        if (wordMap.get(c) == null) {
            return false; //is the letter in the map?
        } else if (!wordMap.get(c)) {
            return false;
        } else {
            return true;
        }
    }

    public void guessLetter(char c) {
        if (getGameStatus().equals("lost")) {
            System.out.println("You already lost!");
            return;
        } else if (getGameStatus().equals("won")) {
            System.out.println("You already won");
            return;
        }
        if (wordMap.get(c) == null) { //is the letter in the map?
            wordMap.put(c, true); //only take off points for 1 letter
            newManPart();
            return;
        } else if (!wordMap.get(c)) { //if letter not found yet, letter found!
            wordMap.put(c, true);
            return;
        } else {
            return; //letter alread found       
        }
    }

    public void newManPart() {
        for (int i = 0; i < manParts.length-1; i++) {
            if (!manParts[i]) {
                manParts[i] = !manParts[i];
                return;
            }
            

        }
        manParts[manParts.length-1] = true;
        gameOver = "lost";

    }

    public Boolean[] getManParts() {
        return manParts;
    }
    
    public String getGameStatus() {
        return gameOver;
    }
    
    public void setGameStatus() {
        gameOver = "won";        
    }

}
