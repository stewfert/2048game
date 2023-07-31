import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    Game game = new Game();
   private  final int PANEL_X_SIZE=400, PANEL_Y_SIZE=400;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                    new Main();
            });
    }

    public Main() {
        setTitle("2048");
       
        this.getContentPane().setPreferredSize(new Dimension(PANEL_X_SIZE, PANEL_Y_SIZE));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(game, BorderLayout.CENTER);
        this.setSize(PANEL_X_SIZE, PANEL_Y_SIZE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.requestFocus();
        
        
        

        // Call the gameMethod() in the Game class to set up the game.
        game.gameMethod();

        // Add the key listener to the Game class, not the JPanel class.
        this.addKeyListener(game);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    
    
}