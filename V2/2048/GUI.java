
import java.awt.geom.*;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame{
   public newBlock Block;
   private final int BLOCK_WIDTH =100, BLOCK_HEIGHT=100;
   private int[][] tempArray=new int [4][4];
   public void GameMethod(){
Block= new newBlock("block",2);
        setTitle("2D graphics");
        System.out.println(Block.getRed());
         System.out.println(Block.getBlue());
          System.out.println(Block.isBlock());
        this.getContentPane().setPreferredSize(new Dimension(500,500));
        this.getContentPane().setLayout(null); 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
       
       
    }
    public void paint(Graphics g){
        // super.paint(g);
        // Graphics2D g2 = (Graphics2D) g;
      // //  int xStart = 0, yStart= 0 ;
       // // int xEnd= 400, yEnd= 400 ;
      // //  Line2D lin = new Line2D.Float(xStart, yStart,xEnd,yEnd);
        // //g2.draw(lin);
        // g2.setColor(Color.LIGHT_GRAY);
        // g2.fillRect(BLOCK_WIDTH,BLOCK_HEIGHT);
        // //g2.drawOval(200,500,200,200);
        // g2.setColor(Color.LIGHT_GRAY);
        // Font font= new Font( "SansSerif", Font.PLAIN, 50 ); 
        // g2.setFont(font);
        // g2.drawString();
    } 


}