import java.awt.*;

public class newBlock{
    private int blockValue; 
    private String  color;
    private String type; 
    private Color blockColor;
    private boolean isBlock=false;

    /* 
    The constructor for my blocks 

    
     */

    public newBlock(String type,int value){
        this.type=type; 
        switch (type){
            case "block":
                isBlock=true;
                this.blockValue=value;
                //need to think of a good algorithm to calculate the block's color using integer math. Perhaps a trig function? 
                //Math to give the blocks their apropriate color changes.
                this.blockColor= new Color ((int)Math.abs(256*Math.sin(value)),(int)Math.abs(256*Math.cos(value)),(int)Math.abs(256*-Math.cos(value)));

                break;
            case "space": 
                isBlock=false;

                this.blockValue=value;
                break;

        }

    
    
    }

    public int getValue(){

        return this.blockValue;

    }

    public int getRed(){
        return this.blockColor.getRed();

    }

    public int getGreen(){
        return this.blockColor.getGreen();

    }

    public int getBlue(){
        return this.blockColor.getBlue();

    }

    public boolean isBlock(){
        return this.isBlock;
    }

}