
package mvc.hangman.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import mvc.hangman.Model.Model;

public class ViewOld extends JPanel {
    Model model;
    
    //theme
    Color backGroundColor = Color.DARK_GRAY;
    Color buttonTextColor = Color.DARK_GRAY;
    Color forgroundColor = Color.GRAY;

    //private Model model;
    final static int WIDTH = 600;
    final static int HEIGHT = 500;

    //GUI Panels
    private JFrame jf;
    private JPanel mainPanel;
    private JPanel keyBoardPanel;
    private JPanel phrasePanel;
    private JPanel noosePanel;
    
    JLabel label;

    //Buttons
    JButton[] keyBoardButton = new JButton[36];
    JButton giveUpButton = new JButton("I'm a Quitter");
    JButton playAgainButton = new JButton("Play Again");
    JButton hintButton = new JButton("Help Mommy");
    

    private JPanel topPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,2));
        //topPanel.setBackground(backGroundColor);
        topPanel.setPreferredSize(new Dimension(WIDTH,HEIGHT/2));     
        topPanel.add(displayLeftJPanel());
        topPanel.add(displayRightJPanel());
        return topPanel;

    }
    
    private JPanel displayRightJPanel() {
        JPanel displayRight = new JPanel();
        //displayRight.setLayout(new GridLayout());
        displayRight.setPreferredSize(new Dimension(WIDTH/2, HEIGHT));
        displayRight.setBackground(forgroundColor);
        
        return displayRight;
    }
    
    private JPanel displayLeftJPanel() {
        //System.out.println(model.getWord());
        String[] words = model.getWord().split(" ");
        JPanel displayLeft = new JPanel(new GridBagLayout()); 
        displayLeft.setPreferredSize(new Dimension(WIDTH/2, HEIGHT));
        displayLeft.setBackground(backGroundColor);

        JPanel textJPanel = new JPanel(new FlowLayout());
        textJPanel.setBorder(new EmptyBorder(4,4,4,4));
        
        for (String str: words) {
            //System.out.println(str);
            label = new JLabel(str);
            label.setForeground(forgroundColor);
            label.setFont(label.getFont().deriveFont(32.0f));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            textJPanel.add(label);
        }        
        displayLeft.add(textJPanel);
        displayLeft.repaint();
        
        return displayLeft;
    }
    
    private JPanel textDisplay() {
        //System.out.println(model.getWord());
        String[] words = model.getWord().split(" ");
        JPanel textDisplayPanel = new JPanel(new GridLayout(words.length, 1));
        
        for (String str: words) {
            //System.out.println(str);
            JLabel label = new JLabel(str);
            label.setHorizontalAlignment(JLabel.CENTER);
            textDisplayPanel.add(label);
        }
        
        return textDisplayPanel;
        
    }
            
    private JPanel usedLetterRow() {
        JPanel letterDisplay = new JPanel(new FlowLayout());
        letterDisplay.add(new JLabel("Used Letters: " + model.getUsedLetters()));
        return letterDisplay;
    }
    

    private JPanel getKeyboard() {
        //setup grid for keyboard display
        keyBoardPanel = new JPanel(new GridLayout(4, 1)); //4 rows
        JPanel keyBoardPanelRow1 = new JPanel(new GridBagLayout());
        keyBoardPanelRow1.setBackground(backGroundColor);
        JPanel keyBoardPanelRow2 = new JPanel(new GridBagLayout());
        keyBoardPanelRow2.setBackground(backGroundColor);
        JPanel keyBoardPanelRow3 = new JPanel(new GridBagLayout());
        keyBoardPanelRow3.setBackground(backGroundColor);
        JPanel keyBoardPanelRow4 = new JPanel(new GridBagLayout());
        keyBoardPanelRow4.setBackground(backGroundColor);


        String keys = "1234567890qwertyuiopasdfghjklzxcvbnm";

        for (int i = 0; i < keyBoardButton.length; i++) {
            if (0 <= i & i <= 9) {
                keyBoardPanelRow1.add(keyBoardButton[i]
                        = new JButton(String.valueOf(keys.charAt(i))));
            } else if (10 <= i & i <= 19) {
                keyBoardPanelRow2.add(keyBoardButton[i]
                        = new JButton(String.valueOf(keys.charAt(i))));
            } else if (20 <= i & i <= 28) {
                keyBoardPanelRow3.add(keyBoardButton[i]
                        = new JButton(String.valueOf(keys.charAt(i))));
            } else if (29 <= i & i <= 35) {
                keyBoardPanelRow4.add(keyBoardButton[i]
                        = new JButton(String.valueOf(keys.charAt(i))));
            }
            keyBoardButton[i].setPreferredSize(new Dimension(55, 30));
            keyBoardButton[i].setForeground(buttonTextColor);

        }
        keyBoardPanel.add(keyBoardPanelRow1);
        keyBoardPanel.add(keyBoardPanelRow2);
        keyBoardPanel.add(keyBoardPanelRow3);
        keyBoardPanel.add(keyBoardPanelRow4);

        return keyBoardPanel;
    }
    
    private JPanel controls() {
        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
        controls.add(hintButton);
        controls.add(giveUpButton);
        controls.add(playAgainButton);
        
        return controls;
    }

   

    public ViewOld(Model model) throws HeadlessException {
        this.model = model;

        canvas();
                setListener();
        jf.setVisible(true);
    }
        
    private void canvas() {
                //start JFrame
        jf = new JFrame("Slangman CS");
        jf.setSize(WIDTH, HEIGHT);
        jf.setLocationRelativeTo(null); //center jf on screen
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        
        c.fill = GridBagConstraints.HORIZONTAL;
        jf.add(topPanel(),c);
        
        c.gridy++;
        jf.add(controls(),c);

        c.gridy++; 
        jf.add(usedLetterRow(),c);
        
        c.gridy++;
        jf.add(getKeyboard(),c);
        

       
        
        

    }

        private void setListener() {
            ActionListener listner = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(playAgainButton)) {
                        model.getNewWord();
                        //label.setText(model.getNewWord());

                    }
                }
            };
            
            playAgainButton.addActionListener(listner);
        }


}