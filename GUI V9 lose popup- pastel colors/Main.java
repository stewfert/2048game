import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
public class Main extends JFrame{
    //global variables
    private final int PANEL_X_SIZE = 400;
    private final int PANEL_Y_SIZE = 400;
    private Game game = new Game();
    private static JOptionPane optionPane;
    private static JDialog dialog;

    //the method that starts when running this class. Starts all other methods.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                    new Main();
            });
    }
    public static void gameWon(){
        //button text on the popup
     Object[] options = {"Ok"};
        //the panel that pops up when the user reaches a 2048 tile.
        int popup = JOptionPane.showOptionDialog(
                null, 
                "Congradulations on reaching the 2048 tile. You can continue your game, reset, or quit.\n Thanks for playing!",
                "You've reached 2048! ",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);
    }
    //message popup showing the player's score and asking if they want to quit. 
    //Attempted to add a buttton allowing the user to reset their game however can't do this easily.
    public static void gameEnd(){
        //the dialog boxes I want to be shown in my popup
        Object[] options = {"Ok", "Quit game?"};
        //the panel that pops up when the user loses.
        int popup = JOptionPane.showOptionDialog(
                null, 
                "Your score was: \n" + score.getScore(),
                "Game Over! ",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);
        //if the user hits the quite game button the program closes.
        if(popup == JOptionPane.NO_OPTION)
        { 
            System.exit(0);           

        }

    }

    //the class that sets up the panel, and runs the game constructor class.
    public Main(){
        setTitle("2048");
        //all the setup data of the JFrame, centering it on screen and setting parameters for it to work for 2048.
        this.getContentPane().setPreferredSize(new Dimension(PANEL_X_SIZE, PANEL_Y_SIZE));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(game, BorderLayout.CENTER);
        this.setSize(PANEL_X_SIZE, PANEL_Y_SIZE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.requestFocus();


        //set the BG of 2048. 
        Color BGColor= new Color( 211, 188, 175 );
        Color contrastColor= new Color(82,73,76);
        game.setBackground(BGColor);

        // Call the setupGame() in the Game class to set up the game.
        game.setupGame();

        // Add the key listener to the Game class, not the JPanel class.
        this.addKeyListener(game);
        this.setFocusable(true);
        this.requestFocusInWindow();

        UIManager.put("PopupMenu.border", new LineBorder(contrastColor));
        // Create the menu bar and menu items for quitting, making a new game, and help menu.
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(BGColor);
        //creates a border around the pop down menu for contrast against  the background
        menuBar.setBorder(BorderFactory.createLineBorder(BGColor));
        JMenu mainMenu = new JMenu("Options");
        //so that the JButton how to play doesn't take up all the allowed space on the JBar
        JMenuItem helpItem = new JMenuItem("How To Play"){
            @Override
            public Dimension getMaximumSize() {
                Dimension d1 = super.getPreferredSize();
                Dimension d2 = super.getMaximumSize();
                d2.width = d1.width;
                return d2;
            }
        };
        //the components inside of my JMenu for the drop down menu.
        JMenuItem newGameItem = new JMenuItem("New Game?");
        JMenuItem exitItem = new JMenuItem("Exit");
        newGameItem.setBackground(BGColor);
        exitItem.setBackground(BGColor);
        helpItem.setBackground(BGColor);
        //lambda expression to run a non static method from static context (reset game)
        newGameItem.addActionListener(e -> {
                    game.setupGame(); 
            });
        //Action listener for exiting the game
        exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    System.exit(0);
                }
            });
            
        helpItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                 Object[] options = {"Ok"};
                 //Can't seem to remove the boxes on the ok button, as it's inbuilt into the OptionPane.
                    JOptionPane.showOptionDialog(
                null, 
                "You can control the game using either arrow keys or WASD. \nThe aim of the game is to merge blocks to reach the highest score you can.\nTiles move and merge in the directions you press, you can hold down keys to play possible moves.\n The game will beep if you've entered a movement that doesn't change anything. \nThe score kept is the sum of all merges you've made, not sum of all the tiles.\nCode written by Miguel Ferrer using logic from original 2048. \nMerge as many tiles you can to get to the 2048 tile! ",
                "How To Play: ",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options, options[0]);
                }
            });
            

        //Menu items to menu 
        mainMenu.add(newGameItem);
        mainMenu.add(exitItem);
    

        //Menu to menu bar
        menuBar.add(mainMenu);
        menuBar.add(helpItem);
        
        //menu bar to the frame
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

}
