
import java.awt.geom.*;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame{
    public newBlock Block;
    private final int BLOCK_WIDTH =100, BLOCK_HEIGHT=100;
    private newBlock[][] blockArray=new newBlock [4][4];
    public void gameMethod(){
//set a time delay to a method
        
        // try{

    // Thread.sleep(500); 
// }
// catch(InterruptedException ex){

 // ex.printStackTrace();
// }
        
        //fills the array with empty spaces.
        for (int j=0; j<4; j++){
            for (int i=0; i<4; i++){

                blockArray[i][j]=new newBlock("space", 0);
   
            }
        }
        //spawns 2 new blocks at the start of the game.
      //  spawnBlock();
       //spawnBlock();
       //testing merge cases, need to change merge method to to read from back to front and not merge blocks twice.
blockArray[1][1]=new newBlock("block", 2);
blockArray[2][1]=new newBlock("block", 2);
blockArray[3][1]=new newBlock("block", 4);


    }

    public void mergeBlocks(int dX, int dY){

        for (int j=0; j<4; j++){
            for (int i=0; i<4; i++){
                try{
                    if(blockArray[i][j].getValue()==blockArray[i+dX][j+dY].getValue()){
                        blockArray[i+dX][j+dY].setValue(2*blockArray[i][j].getValue());
                        blockArray[i][j]=new newBlock("space", 0);
                    }
                } catch(ArrayIndexOutOfBoundsException e){
                System.out.println("can't merge with edge!");
           
                    //if the block value in the corresponding direction is the same as the current block, add their values together and replace the blocks value in the corresponding direction. 
                    //check for boundary/block at edge. 

                }

            }

        }
    }

    public void printTesting(){
        for (int j=0; j<4; j++){
            for (int i=0; i<4; i++){

                if( blockArray[i][j].isBlock()){
                    System.out.print(blockArray[i][j].getValue());
                }
                else System.out.print("X");

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