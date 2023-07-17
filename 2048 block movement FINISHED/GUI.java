
import java.awt.geom.*;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
/*
 * 2048 recreated in Java. 
 * program by Miguel Ferrer.
 * 
 */ 

public class GUI extends JFrame{
    public newBlock Block;
    private final int BLOCK_WIDTH =100, BLOCK_HEIGHT=100;

    private newBlock[][] blockArray=new newBlock [4][4];

    public void gameMethod(){

        /*
        Reminders! 
         *Need to think of a way to stop blocks from spawning if no moves can be made

         */

        //fills the array with empty spaces.
        for (int j=0; j<blockArray.length; j++){
            for (int i=0; i<blockArray.length; i++){

                blockArray[i][j]=new newBlock("space", 0);

            }
        }
        //spawns 2 new blocks at the start of the game.
        spawnBlock();
        spawnBlock();

    }
    //this method takes the input of a direction (X and Y) where only 1 direction at a time can be not equal to zero. 
    //This integer controls what direction the method parses through the 2d block array and compares blocks if they can be merged (same value over 0). 
    public void mergeBlocks(boolean Xpressed, boolean posDirection){
        int parseFromX=0, parseFromY=0;
        int dX=0, dY=0;
        int xCheck=0, yCheck=0;
        //
        /*we want to start with checking what direction has been inputted and setting the nested for loop from that direction in that array 
        Need to check from the edge in the inputted direction to the other edge if any other blocks in that direction have mergeable values. 

         */

        //Unoptimized conditionals that set the direction the array loops from depending on what direction has been set.
        //the Check variables refer to the block object being compared to the block object in another direction.
        if(Xpressed==true){
            if (posDirection==true){
                parseFromX=blockArray.length-1; 
                dX=-1;
                xCheck=-1;
            }

            else {
                parseFromX=0; 
                dX=1;
                xCheck=1;
            }
        }else{
            parseFromX=0; 
            dX=1;
            xCheck=0;
        }

        if(Xpressed==false){
            if (posDirection==true){
                parseFromY=blockArray.length-1; 
                dY=-1;
                yCheck=-1;
            }
            else{
                parseFromY=0; 
                dY=1;
                yCheck=1;
            }
        }else{
            parseFromY=0; 
            dY=1;
            yCheck=0;
        }
        System.out.println(parseFromX+" is starting pos and changes by"+dX+" and "+parseFromY+" is the y pos and changes by"+dY);

        for (int j=parseFromY; j>=0 && j<blockArray.length; j+=dY){
            for (int i=parseFromX; i>=0 && i<blockArray.length; i+=dX){
                System.out.println(i+" is the X and the Y is "+j);
                try{
                    //when an inputted direction is made the program calculated which blocks can be merged STARTING with the blocks in that direction.
                    //Checks if the block next to the current block has the same value and has not already been flagged for merging, if not then flags both for merging.

                    if(blockArray[i][j].getValue()==blockArray[i+xCheck][j+yCheck].getValue() 
                    && blockArray[i][j].isBlock()
                    && blockArray[i+xCheck][j+yCheck].canMerge()==false){
                        blockArray[i][j].setForMerge(); 
                        blockArray[i+xCheck][j+yCheck].setForMerge();
                        System.out.println("MARGED!!!");
                        score.setScore((2*blockArray[i][j].getValue())+score.getScore());
                    }
                } catch(ArrayIndexOutOfBoundsException e){

                }
            }
        }
    }
    //method utilized in moveBlocks to see if a a particular spot in the block Array is empty or not.
    public boolean checkSpaceEmpty(int x, int y){

        try{if(blockArray[x][y].isBlock()){
                return false;
            }
            else return true;
        }
        catch(ArrayIndexOutOfBoundsException e){
            return false;
        }

    }

    public boolean checkAdjacentBlocks() {
        int dX = 1, dY = 1;

       
        //parses through the block array and check every element.
        for (int i = 0; i < blockArray.length; i++) {
            for (int j = 0; j < blockArray.length; j++) {
                int currentElement = blockArray[i][j].getValue();

                // Each of these conditionals checks to see if objects in corresponding directions are blocks. 
                //It will run each conditional and if there are 2 same blocks allows the user to keep playing, otherwise returns false.
                
                //first we check if the current element is not a block in which case if its true we can immediately resume the game.
                if (!blockArray[i][j].isBlock()){
                return true;           
                }
                //each conditional checks the block next to it in a corresponding direction to see if the block has the same value. May simplify because directionality does not matter in this case.
                if (j + dX >= 0 && j + dX < blockArray.length && blockArray[i][j + dX].getValue() == currentElement) {
                    return true;
                }

               
                if (j - dX >= 0 && j - dX < blockArray.length && blockArray[i][j - dX].getValue() == currentElement) {
                    return true;
                }

               
                if (i + dY >= 0 && i + dY < blockArray.length && blockArray[i + dY][j].getValue() == currentElement) {
                    return true;
                }

                
                if (i - dY >= 0 && i - dY < blockArray.length && blockArray[i - dY][j].getValue() == currentElement) {
                    return true;
                }
            }
        }

        return false;
    }

    public void moveBlocks(boolean Xpressed,boolean posDirection){
        int parseFromX=0, parseFromY=0;

        int dX=0, dY=0;
        //We want to use a 2d array to parse through each element inside the array and move every block in an inputted direction to the max value of the array bounds or next to a block. 
        if(Xpressed==true){
            if (posDirection==true){
                parseFromX=blockArray.length-1; 
                dX=-1;
            }
            else {
                parseFromX=0; 
                dX=1;
            }
        }else{
            parseFromX=0; 
            dX=1;
        }
        if(Xpressed==false){
            if (posDirection==true){
                parseFromY=blockArray.length-1; 
                dY=-1;

            }
            else{
                parseFromY=0; 
                dY=1;

            }
        }else{
            parseFromY=0; 
            dY=1;

        }
        //nested for loop that parses through the block array from the direction the user has inputted from.
        for (int j=parseFromY; j>=0 && j<blockArray.length; j+=dY){
            for (int i=parseFromX; i>=0 && i<blockArray.length; i+=dX){

                int moveX=i, moveY=j;
                //if the current block in the array is a block or a space.
                if(Xpressed==true && blockArray[i][j].isBlock()){
                    //if the current block has space in the direction the user has inputted in, keep checking to see how far the block can move.
                    while (moveX - dX >= 0 && moveX - dX < blockArray.length && checkSpaceEmpty(moveX - dX, moveY)) {
                        //if there's space check the space next to that space.
                        moveX-=dX;
                    }
                    //once the block has hit the boundary or another block place the new block next to it or in the same spot as before.
                    if(moveX !=i){
                        blockArray[moveX][j]=blockArray[i][j];
                        newSpace(i,j);
                    }

                }
                else if(blockArray[i][j].isBlock()){
                    while (moveY - dY >= 0 && moveY - dY < blockArray.length && checkSpaceEmpty(moveX, moveY - dY)) {
                        moveY-=dY;

                    }
                    if(moveY !=j){
                        blockArray[i][moveY]=blockArray[i][j];
                        newSpace(i,j);

                    }

                }
            }

        }
    }

    public void newSpace(int x, int y){
        blockArray[x][y]=new newBlock("space", 0);
    }

    public void printTesting(){
        for (int j=0; j<blockArray.length; j++){
            for (int i=0; i<blockArray.length; i++){

                if( blockArray[i][j].isBlock()){
                    System.out.print(blockArray[i][j].getValue());
                }
                else System.out.print("X");

            }
            System.out.println();
        }

    }
    //method that tests if the merge calculations have functioned correctly, parses the array and highlights blocks that have been marked for merges.
    public void mergePrintTesting(){
        for (int j=0; j<blockArray.length; j++){
            for (int i=0; i<blockArray.length; i++){

                if( blockArray[i][j].canMerge()){
                    System.out.print("O");
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
            spawnX=GameEngine.randomInt(0,blockArray.length-1);
            spawnY = GameEngine.randomInt(0,blockArray.length-1);

            if(blockArray[spawnX][spawnY].isBlock()){
                spawnX=GameEngine.randomInt(0,blockArray.length-1);
                spawnY=GameEngine.randomInt(0,blockArray.length-1);

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
        // // int xEnd= blockArray.length00, yEnd= blockArray.length00 ;
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