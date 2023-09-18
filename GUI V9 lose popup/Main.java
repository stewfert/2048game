import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main extends JFrame{
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

    public static void gameEnd(){
    
        int n = JOptionPane.showConfirmDialog(
                null,
                "Your score was: \n" + score.getScore(),
                "Game Over! ",
                JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION)
        { 
            //way to run the newgame method. Static non static issues with this.
            
       
        }
        else
        {
            JOptionPane.showMessageDialog(null, "GOODBYE");
        }

    }

    public Main(){
        setTitle("2048");
        //all the setup data of the JFrame, centering it on screen and setting parameters for it to work for 2048.
        this.getContentPane().setPreferredSize(new Dimension(PANEL_X_SIZE, PANEL_Y_SIZE));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(game, BorderLayout.CENTER);
        this.setSize(PANEL_X_SIZE, PANEL_Y_SIZE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.requestFocus();

        //set the BG of 2048. 
        Color BGColor= new Color( 211, 188, 175 );
        game.setBackground(BGColor);

        // Call the gameMethod() in the Game class to set up the game.
        game.gameMethod();

        // Add the key listener to the Game class, not the JPanel class.
        this.addKeyListener(game);
        this.setFocusable(true);
        this.requestFocusInWindow();

        // Create the menu bar and menu items for quitting and making a new game
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Options");
        JMenuItem newGameItem = new JMenuItem("New Game?");
        JMenuItem exitItem = new JMenuItem("Exit");
        //lambda expression to run a non static method from static context (reset game)
        newGameItem.addActionListener(e -> {
                    game.gameMethod(); 
            });
        //Action listener for exiting the game
        exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   
                    System.exit(0);
                }
            });

        //Menu items to menu 
        gameMenu.add(newGameItem);
        gameMenu.add(exitItem);

        //Menu to menu bar
        menuBar.add(gameMenu);

        //menu bar to the frame
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

}
