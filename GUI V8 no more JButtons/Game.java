//packages for 2048
import java.awt.geom.*;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage; 
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * 2048 recreated in Java. 
 * program by Miguel Ferrer.
 * 
 */ 

public class Game extends JPanel implements KeyListener {
    //definitions for my block objects, block array, graphics, and input
    public newBlock Block;
    private final int BLOCK_SIZE=50;
    private newBlock[][] blockArray=new newBlock [4][4];
    Canvas myGraphic;
    public boolean activityCheck=false;
    JMenuBar menuBar;
    JMenu menu; 
    JMenuItem menuItem;

    //takes input for all keys pressed.
    @Override
    public void keyPressed(KeyEvent e) {
        int input = e.getKeyCode();

        switch(input){
            case 87: 
                runTurn(false, false);
                //W key pressed (up movement)
                break;
            case 68: 
                runTurn(true, true);
                //D key pressed (right movement)
                break;
            case 65: 
                runTurn(true, false);
                //A key pressed (left movement)
                break;
            case 83: 
                runTurn(false, true);
                //S key pressed (down movement)
                break;
            default: 
                //other key pressed (nothing)

                break;

        }


    }
    //method for keypressed, don't need this function for my game.
    @Override
    public void keyReleased(KeyEvent e) {
    }
    //method for character keys typed, don't need this function for my game .
    @Override
    public void keyTyped(KeyEvent e) {
    }
    


    //method that's run at the start of the game to setup the board, and for making a new board.
    public void gameMethod(){
       
        
        //fills the array with empty spaces.
        for (int j=0; j<blockArray.length; j++){
            for (int i=0; i<blockArray.length; i++){

                blockArray[i][j]=new newBlock("space", 0);

            }
        }
        //spawns 2 new blocks at the start of the game.
        spawnBlock(); 
        spawnBlock ();
        printTesting();
        repaint();
        score.setScore(0);
    }
    
  
    //this method takes the input of a direction (X and Y) where only 1 direction at a time can be not equal to zero. 
    //This integer controls what direction the method parses through the 2d block array and compares blocks if they can be merged (same value over 0). 
    public void runTurn(boolean xPressed, boolean posDirection){
        activityCheck=false;
        moveBlocks(xPressed, posDirection);
        mergeBlocks(xPressed, posDirection); 
        moveBlocks(xPressed, posDirection);
        if( activityCheck==true) spawnBlock();
        if (loseCheck()==false){
            System.exit(1);
        }
        printTesting();

        repaint();
    } 

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

        for (int j=parseFromY; j>=0 && j<blockArray.length; j+=dY){
            for (int i=parseFromX; i>=0 && i<blockArray.length; i+=dX){
                try{
                    //when an inputted direction is made the program calculated which blocks can be merged STARTING with the blocks in that direction.
                    //Checks if the block next to the current block has the same value and has not already been flagged for merging, if not then flags both for merging.
                    //flagged blocks are now merged together and then the resultant block is flagged to not be merged again. Other block becomes an empty space.
                    if(blockArray[i][j].getValue()==blockArray[i+xCheck][j+yCheck].getValue() 
                    && blockArray[i][j].isBlock()
                    && blockArray[i+xCheck][j+yCheck].canMerge()==true
                    && blockArray[i][j].canMerge()==true) {
                        //adds the merged block's value to the curent score. 
                        score.setScore((2*blockArray[i][j].getValue())+score.getScore());
                        //Creates our resultant block and deletes the previous ones.
                        blockArray[i+xCheck][j+yCheck]= new newBlock("block",(2*blockArray[i][j].getValue()));

                        newSpace(i,j);
                        blockArray[i+xCheck][j+yCheck].blockMerged();
                        activityCheck=true;
                    }
                    //For boundary cases caused by our parsing outside the array.
                } catch(ArrayIndexOutOfBoundsException e){

                }
            }
        }

        //removes the blockMerged property from each block so that they can remerge after a move. 
        resetMerges();
    }
    // heehee snea
    public void resetMerges(){
        for (int j=0; j<4; j++){
            for (int i=0; i<4; i++){
                blockArray[i][j].setForMerge();
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
        //if a coordinate is checked outside the bounds of the array we report that as being no space for movement.
        catch(ArrayIndexOutOfBoundsException e){
            return false;
        }

    }

    public boolean loseCheck() {
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
                        activityCheck=true;
                    }

                }
                else if(blockArray[i][j].isBlock()){
                    while (moveY - dY >= 0 && moveY - dY < blockArray.length && checkSpaceEmpty(moveX, moveY - dY)) {
                        moveY-=dY;

                    }
                    if(moveY !=j){
                        blockArray[i][moveY]=blockArray[i][j];
                        newSpace(i,j);
                        activityCheck=true;
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
    //spawns a block in an empty random position within the array.
    public void spawnBlock(){
        int spawnX=0; 
        int spawnY=0;
        boolean blockSpawned=false;

        do{
            spawnX=GameEngine.randomInt(0,blockArray.length-1);
            spawnY = GameEngine.randomInt(0,blockArray.length-1);

            if(blockArray[spawnX][spawnY].isBlock()){

                //if a block has been selected to spawn on for some odd reason the do while loop breaks so we use recursion to place a new block.
                //Should never reach the above max amount as our loss detection will find if the player has lost before then.
                spawnBlock();

            }
            else{
                blockArray[spawnX][spawnY]=new newBlock("block", GameEngine.blockChance());
                blockSpawned=true;
            }

        }while (blockSpawned=false);

    }


    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g1 = (Graphics2D) g;
        int spacing=60;
        int blockXPos=0;
        int blockYPos=0;
        //font for the blocks
        Font blockFont= new Font( "SansSerif", Font.PLAIN, 10 );
        int PANEL_X_SIZE=400, PANEL_Y_SIZE=400;
        System.out.println("size is"+PANEL_X_SIZE);
        //colors for specific cases that are similar to 2048's.
        Color spaceColor= new Color( 226, 210, 202);
        Color scoreColor= new Color(169, 130, 108);
        for (int j=0; j<blockArray.length; j++){
            for (int i=0; i<blockArray.length; i++){
                blockXPos=(PANEL_X_SIZE/2)-(spacing*blockArray.length/2) +(i*spacing);
                blockYPos=(PANEL_Y_SIZE/2)-(spacing*blockArray.length/2) +(j*spacing);
                //calculates the current font width for centering.
                int blockTextWidth=g1.getFontMetrics().stringWidth(Integer.toString(blockArray[i][j].getValue()));     
                //avg text height
                int textHeight= 10;
                //if the current element is a block print out a tile with the block's color specified by its value. 
                if(blockArray[i][j].isBlock()){
                    //colors are generated inside of the block object constructor
                    Color blockColor=new Color(blockArray[i][j].getRed(),blockArray[i][j].getGreen(),blockArray[i][j].getBlue());
                    g1.setColor(blockColor);
                    g1.fillRect(blockXPos,blockYPos, BLOCK_SIZE,BLOCK_SIZE);
                    g1.setFont(blockFont);
                    g1.setColor(Color.BLACK);
                    //centers the value of the block
                    g1.drawString(Integer.toString(blockArray[i][j].getValue()),blockXPos-(blockTextWidth/2)+(BLOCK_SIZE/2),blockYPos+(BLOCK_SIZE/2)+(textHeight/2));
                   
                }
                else{
                    //draw an empty space represented by a box
                    g1.setColor(spaceColor);
                    g1.fillRect(blockXPos,blockYPos, BLOCK_SIZE,BLOCK_SIZE);

                }
            }

        }
        //these lines display the current score of the game.
        g1.setColor(scoreColor);
        Font scoreFont= new Font( "SansSerif", Font.PLAIN, 30 ); 
        g1.setFont(scoreFont);
        //gets the current score from score class, converts it into one string so length can be calculated
        String printScore=("Your score is "+Integer.toString(score.getScore()));
        //gets the strings current length
        int scoreWidth=g1.getFontMetrics().stringWidth(printScore); 
        System.out.println(scoreWidth);
        //display the score center aligned on the screen, looks a bit off but measured to be exact.
        g1.drawString(printScore,(PANEL_X_SIZE/2)-(scoreWidth/2),30);
 
    }

}