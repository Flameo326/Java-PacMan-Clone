
package gamedevelopment;

public class Tile {
        
    int x, xPos;
    int y, yPos;
    
    boolean occupied = false;
    String entity;
    
    public Tile(int _x, int _y){ 
        x = _x;
        y = _y;
        xPos = _x*16;
        yPos = _y*16;
        entity = " ";
    }
    
    public void occupy(DrawGraphics _entity){
        occupied = true;
        entity = _entity.tag;
    }
    
    public void notOccupy(){
        occupied = false;
        entity = " ";
    }
    
    public String check(){
        if(occupied){
            return entity;
        } else
            return " ";
    }
    
}
