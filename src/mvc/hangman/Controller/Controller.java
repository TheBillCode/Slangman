package mvc.hangman.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mvc.hangman.Model.Model;
import mvc.hangman.View.View;

public class Controller {

    private View view;
    private Model model;

    public Controller(Model model, View view) {
        this.view = view;
        this.view.setListener(new ListenForInput());
        this.view.setCurrentMessage("Hint: " + model.getDefinition());
       
        this.model = model;

    }
    
    class ListenForInput implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < view.getKeyboardButtons().length; i++) {
                if (e.getSource().equals(view.getKeyboardButtons()[i])) {
                    //check the if letter is in word
                    model.guessLetter(view.getKeyboardButtons()[i].getText().charAt(0));

                }
            }
            view.update();
        }
    }
    
    



}
