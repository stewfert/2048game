
public class newBlock{
   private int blockValue; 
   private String  blockColor;
  private String type; 
  private boolean isBlock=false;
  /* 
  The constructor for my blocks 
   
   
    
    */
   
   public newBlock(String type, String color, int value){
   this.type=type; 
   switch (type){
    case "block":
    isBlock=true;
    this.blockColor=color;
        this.blockValue=value;
    break;
    case "space": 
    isBlock=false;
    break;
    
    }
   
    
    
    
    
}
    public int blockValue(){
    
    return this.blockValue;
    
}
public String blockColor(){
    return this.blockColor;
    
}
public boolean isBlock(){
return this.isBlock;
}


}