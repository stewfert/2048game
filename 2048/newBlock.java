import java.awt.*;
import java.awt.Color;
public class newBlock{
   private int blockValue; 
   private String  color;
  private String type; 
private Color blockColor;
  private boolean isBlock=false;
  
  /* 
  The constructor for my blocks 
   
   
    
    */
   
   public newBlock(String type,int value,int R,int G, int B){
   this.type=type; 
   switch (type){
    case "block":
    isBlock=true;
    
   this.blockColor= new Color(R,G,B);
   
        this.blockValue=value;
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