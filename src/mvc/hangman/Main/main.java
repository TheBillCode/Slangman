package mvc.hangman.Main;

import javax.swing.*;
import java.awt.*;
import mvc.hangman.Controller.Controller;
import mvc.hangman.Model.Model;
import mvc.hangman.View.View;

public class main {
    
    static {
        try {//if using mac OS
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        Model model = new Model();
        View panel = new View();
        Controller controller = new Controller(model, panel);
        JFrame frame = new JFrame("SlangMan CS");

        frame.setContentPane(panel);
        frame.setJMenuBar(panel.getMenuBar());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - frame.getWidth()) / 2,
                (screenSize.height - frame.getHeight()) / 2);
        frame.setVisible(true);
    }
}
