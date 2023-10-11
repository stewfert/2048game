import java.awt.*;

public class newBlock{
    //variables of each generated block
    private int blockValue; 
    private String  color;
    private String type; 
    private Color blockColor;
    private boolean isBlock=false;
private boolean mergeFlagged=true;
    /* 
    The constructor for my blocks 

    
     */
    
    public newBlock(String type,int value){
        this.type=type; 
        switch (type){
            
            case "block":
                isBlock=true;
                this.blockValue=value;
                //Math to give the blocks their apropriate color changes.
              this.blockColor= new Color ((int)Math.abs(128+128*Math.sin(0.05*(value+24))),
              ((int)Math.abs(128+128*Math.sin(0.09*(value+25)))),
             ((int)Math.abs(128+128*Math.sin(0.03*(value+10)))));

                break;
            case "space": 
                isBlock=false;

                this.blockValue=value;
                break;

        }

    
    
    }
    //returns value 
    public int getValue(){

        return this.blockValue;

    }
    //for setting the blocks value    
    public void  setValue(int value){
    this.blockValue=value;
    }
    //so blocks are not accidentaly merged multipe times
    public void setForMerge(){
    this.mergeFlagged=true;
    }
    public void blockMerged(){
    this.mergeFlagged=false;
    }
    
    public boolean canMerge(){
    return this.mergeFlagged;
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