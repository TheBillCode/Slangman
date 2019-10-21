package mvc.hangman.View;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import mvc.hangman.Model.Model;

public class DrawWord {    
    Model model;

    public DrawWord(Model model) {
        this.model = model;
    }

    //display the model or blanks in center of JPanel
    public void draw(Graphics g) {
        Font f = new Font("monospace", 0, 28); //set font
        String displayWord = makeWord();
        
        
        //add spacing between letters
        Map<TextAttribute, Object> spacing = new HashMap<TextAttribute, Object>();
        spacing.put(TextAttribute.TRACKING, 0.05);
        Font f2 = f.deriveFont(spacing);
        
        g.setFont(f2); //update font
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(displayWord); //word length
        int center = 200 - (width / 2); //find center of JPanel
        g.drawString(displayWord, center, 75);
                
    }
    
    //prepares model for display including blanks
    private String makeWord() {
        String newWord = "";
        for (int i =0; i < model.getWord().length(); i++) {
            char letter = model.getWord().charAt(i);
            if (model.isLetterFound(letter)) {
              newWord += letter;  
            } else if (letter == ' ' | letter == '-') {
                newWord += "-";
            } 
            else {
                newWord += "_";
            }           
        } 
        if (!newWord.contains("_")) {
            model.setGameStatus();
        }

        return newWord;
    }
    
}
