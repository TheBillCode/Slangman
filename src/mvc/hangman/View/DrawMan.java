package mvc.hangman.View;

import java.awt.Graphics;
import mvc.hangman.Model.Model;

public class DrawMan {
    Model model;

    public DrawMan(Model model) {
        this.model = model;
    }
    
    

    public void draw(Graphics g) { //need to make more generic
        Boolean[] manParts = model.getManParts();
        g.drawLine(100, 0, 100, 20);

        if (manParts[0]) {//if the head is hung
            g.drawOval(86, 20, 28, 28); //head
            g.drawOval(93, 28, 3, 3); //L eye
            g.drawOval(104, 28, 3, 3); //R eye
            g.drawLine(95, 40, 105, 40); //mouth
        }
        if (manParts[1]) //if body hung
        {
            g.drawLine(100, 48, 100, 84); //body
        }
        if (manParts[2]) //if R arm hung
        {
            g.drawLine(100, 69, 115, 60); //R arm
        }
        if (manParts[3]) //if left arm hung
        {
            g.drawLine(100, 69, 85, 60); //L arm
        }
        if (manParts[4]) //if right leg hung
        {
            g.drawLine(100, 84, 115, 100); //R leg
        }
        if (manParts[5]) //if left leg hung
        {
            g.drawLine(100, 84, 85, 100); //L leg
        }

    }

//    public static boolean setManPart() {
//        for (Boolean b : manParts) {
//            if (!b) {
//                b = true;
//                System.out.println(b);
//                return false;
//            }
//
//        }
//        return true;
//    }
    
    
}
