package mvc.hangman.View;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;
import mvc.hangman.Model.Model;

public class View extends JPanel {

    DrawMan man = null;
    DrawWord word = null;
    Model model = new Model();

    //Window Size
    final static int WIDTH = 600;
    final static int HEIGHT = 300;

    //theme
    Color colorBackground = Color.DARK_GRAY;
    Color colorButtonText = Color.DARK_GRAY;
    Color colorForeground = Color.LIGHT_GRAY;

    public Canvas canvas;
    private JMenuBar menuBar;
    public Canvas nooseArea;
    private Canvas puzzleArea;
    JPanel messageArea;
    JTextArea textArea;
    private String currentMessage = null;

    //Buttons
    static JButton[] keyBoardButton = new JButton[36];

    //Menu
    static JMenuItem newGame = new JMenuItem();

    private class Canvas extends JPanel {

        boolean noose = false;

        Canvas() {
            //setPreferredSize(new Dimension(400, 300));
            setBackground(colorBackground);
            setForeground(colorForeground);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            if (noose) {
                man.draw(g);
            } else {
                word.draw(g);
                setCurrentMessage(currentMessage);
                
            }

        }

        public void setNoose(boolean noose) {
            this.noose = noose;
        }

    }

    public View() {

        //Set full Jpanel
        setLayout(new BorderLayout(3, 3));
        setBackground(colorBackground);
        setBorder(BorderFactory.createLineBorder(colorBackground, 2));
        JPanel box = new JPanel();
        add(box, BorderLayout.CENTER);

        //top panel contains text area and noose area
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(colorBackground);

        //puzzle area
        word = new DrawWord(model);
        puzzleArea = new Canvas();
        puzzleArea.setBackground(colorBackground);
        puzzleArea.setBorder(BorderFactory.createLineBorder(colorForeground));
        puzzleArea.setPreferredSize(new Dimension(400, HEIGHT / 2));
        topPanel.add(puzzleArea);

        //Noose area
        man = new DrawMan(model);
        nooseArea = new Canvas();
        nooseArea.setBackground(colorBackground);
        nooseArea.setBorder(BorderFactory.createLineBorder(colorForeground));
        nooseArea.setPreferredSize(new Dimension(200, HEIGHT / 2));
        nooseArea.setNoose(true);
        topPanel.add(nooseArea);

        add(topPanel, BorderLayout.NORTH);

        //User Message
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(WIDTH - 10, 40));
        textArea.setBackground(colorBackground);
        textArea.setForeground(colorForeground);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText(currentMessage);
        textArea.setEditable(false);

        //Message area
        messageArea = new JPanel();
        messageArea.setBackground(colorBackground);
        messageArea.setBorder(BorderFactory.createLineBorder(colorForeground));
        messageArea.setPreferredSize(new Dimension(WIDTH, 60));
        messageArea.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(5, 5, 5, 5),
                new LineBorder(colorForeground)
        ));
        messageArea.add(textArea);
        add(messageArea, BorderLayout.CENTER);

        //Set Keyboard
        JPanel keyboard = new JPanel();
        keyboard.setBackground(colorBackground);
        keyboard.add(getKeyboard());
        add(keyboard, BorderLayout.SOUTH);

    }

    private JPanel getKeyboard() {
        String keys = "1234567890qwertyuiopasdfghjklzxcvbnm";
        //setup grid for keyboard display
        JPanel keyBoardPanel = new JPanel(new GridLayout(4, 1)); //4 rows
        JPanel keyBoardPanelRow1 = new JPanel(new GridBagLayout());
        keyBoardPanelRow1.setBackground(colorBackground);
        JPanel keyBoardPanelRow2 = new JPanel(new GridBagLayout());
        keyBoardPanelRow2.setBackground(colorBackground);
        JPanel keyBoardPanelRow3 = new JPanel(new GridBagLayout());
        keyBoardPanelRow3.setBackground(colorBackground);
        JPanel keyBoardPanelRow4 = new JPanel(new GridBagLayout());
        keyBoardPanelRow4.setBackground(colorBackground);

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
            keyBoardButton[i].setForeground(colorButtonText);

        }
        keyBoardPanel.add(keyBoardPanelRow1);
        keyBoardPanel.add(keyBoardPanelRow2);
        keyBoardPanel.add(keyBoardPanelRow3);
        keyBoardPanel.add(keyBoardPanelRow4);

        return keyBoardPanel;
    }

    public JMenuBar getMenuBar() {
        if (menuBar == null) {
            menuBar = new JMenuBar();
        }

        String commandKey; //for making keyboard accelerators for menu commands
        if (System.getProperty("mrj.version") == null) {
            commandKey = "control ";  // command key for non-Mac OS
        } else {
            commandKey = "meta ";  // command key for Mac OS 

        }
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        newGame = new JMenuItem("I'm Losing - Play Again");
        newGame.setAccelerator(KeyStroke.getKeyStroke(commandKey + "P"));
        fileMenu.add(newGame);
        JMenuItem hint = new JMenuItem("Hint - Mommy. Help");
        fileMenu.add(hint);
        JMenuItem quit = new JMenuItem("Quit - I'm Scared");
        fileMenu.add(quit);

        return menuBar;
    }

    public JButton[] getKeyboardButtons() {
        return keyBoardButton;
    }

    public JMenuItem getNewGame() {
        return newGame;
    }

    public void setListener(ActionListener listenForInput) {
        for (int i = 0; i < keyBoardButton.length; i++) {
            keyBoardButton[i].addActionListener(listenForInput);
        }

    }

    public void update() {
        nooseArea.repaint();
        puzzleArea.repaint();

    }

    public void setCurrentMessage(String msg) {
        if (model.getGameStatus().equals("lost")) msg = "YOU LOST";
        if (model.getGameStatus().equals("won")) msg = ("YOU WON");
        this.currentMessage = msg;
        textArea.setText(currentMessage);
    }

}
