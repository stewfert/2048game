
import java.awt.geom.*;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame{
    public newBlock Block;
    private final int BLOCK_WIDTH =100, BLOCK_HEIGHT=100;
    private newBlock[][] blockArray=new newBlock [4][4];
    public void gameMethod(){

        int prevX=5, prevY=5;
        //Method for spawning in 2 new blocks at the start of the game, and makes sure that the objects do not generate in the same square.
        for (int j=0; j<3; j++){
            for (int i=0; i<3; i++){

                blockArray[i][j]=new newBlock("space", 0);

            }
        }
        
        for (int i=0; i<2; i++){
            int blockX= GameEngine.randomInt(0,3), blockY = GameEngine.randomInt(0,3);
            while(prevX==blockX && prevY==blockY){
                blockX= GameEngine.randomInt(0,3);
                blockY = GameEngine.randomInt(0,3);
                System.out.println("Epic fail");
            }
            System.out.println ("X is "+blockX+" Y is "+blockY);
            blockArray[blockX][blockY]=new newBlock("block", GameEngine.blockChance());
            prevX=blockX;  prevY=blockY;
            System.out.println("Block at"+blockX+ " "+blockY);
        }
        
        for (int j=0; j<3; j++){
            for (int i=0; i<3; i++){

                if( blockArray[i][j].isBlock()){
                    System.out.print(blockArray[i][j].getValue());
                }
                else System.out.println("X");

            }
            System.out.println();
        }

    }

    public void spawnBlock(){

        int spawnX; 
        int spawnY;
        boolean blockSpawned=false;

        do{
            spawnX=GameEngine.randomInt(0,3);
            spawnY = GameEngine.randomInt(0,3);

            if(blockArray[spawnX][spawnY].isBlock()){
                spawnX=GameEngine.randomInt(0,3);
                spawnY=GameEngine.randomInt(0,3);

            }
            else{
                System.out.println("Yo do this work?");
                blockArray[spawnX][spawnY]=new newBlock("block", GameEngine.blockChance());
                blockSpawned=true;
                System.out.println(spawnX+" is the X coord"+"And "+spawnY+"Is the Y coord");
            }

        }while (blockSpawned=false);

    }

    public void canvas(){
        setTitle("2D graphics");

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