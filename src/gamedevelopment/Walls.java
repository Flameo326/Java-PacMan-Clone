
package gamedevelopment;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Walls extends DrawGraphics{
    
    BufferedImage horiWall;
    BufferedImage vertWall;
    
    int DimensionHeight;
    int DimensionWidth;
    
    //For Tile could have corner x and y pixels for drawing
    
    public Walls(){
        //Get wall Sprite
        horiWall = SpriteSheet.createWallSprite();
        //Rotate it
        AffineTransform aT = new AffineTransform();
        aT.rotate(Math.toRadians(90), horiWall.getWidth()/2, horiWall.getHeight()/2);
        AffineTransformOp transform = new AffineTransformOp(aT, AffineTransformOp.TYPE_BILINEAR);
        vertWall = transform.filter(horiWall, vertWall);
        
        DimensionHeight = GameDevelopment.DimensionHeight;
        DimensionWidth = GameDevelopment.DimensionWidth;
        
        //For level making...
        tag = "wall";
        setPosition();      
    }
    //X, Y;
    private void setPosition(){
        for(int i = 0; i < DimensionWidth/16;i++){
            GameDevelopment.tiles[i][0].occupy(this);
            GameDevelopment.tiles[0][i].occupy(this);
            GameDevelopment.tiles[i][DimensionWidth/16 - 1].occupy(this);
            GameDevelopment.tiles[DimensionWidth/16 - 1][i].occupy(this);            
        }
        
        for(int i = 2; i < 14; i++){
            GameDevelopment.tiles[i][2].occupy(this);
            GameDevelopment.tiles[i][13].occupy(this);
        }
        /*
        GameDevelopment.tiles[2][4].occupy(this);
        GameDevelopment.tiles[2][5].occupy(this);
        GameDevelopment.tiles[2][6].occupy(this);
        GameDevelopment.tiles[2][7].occupy(this);
        
        GameDevelopment.tiles[2][9].occupy(this);
        GameDevelopment.tiles[2][10].occupy(this);
        GameDevelopment.tiles[2][11].occupy(this);
        GameDevelopment.tiles[2][12].occupy(this);
        
        GameDevelopment.tiles[11][4].occupy(this);
        GameDevelopment.tiles[11][5].occupy(this);
        GameDevelopment.tiles[11][6].occupy(this);
        GameDevelopment.tiles[11][7].occupy(this);
        
        GameDevelopment.tiles[11][9].occupy(this);
        GameDevelopment.tiles[11][10].occupy(this);
        GameDevelopment.tiles[2][11].occupy(this);
        GameDevelopment.tiles[2][12].occupy(this);
        */
        
        GameDevelopment.tiles[2][4].occupy(this);
        GameDevelopment.tiles[3][4].occupy(this);
        GameDevelopment.tiles[4][4].occupy(this);
        GameDevelopment.tiles[5][4].occupy(this);
        GameDevelopment.tiles[6][4].occupy(this);
        
        GameDevelopment.tiles[8][4].occupy(this);
        GameDevelopment.tiles[9][4].occupy(this);
        GameDevelopment.tiles[10][4].occupy(this);
        GameDevelopment.tiles[11][4].occupy(this);
        GameDevelopment.tiles[12][4].occupy(this);
        GameDevelopment.tiles[12][4].occupy(this);
        GameDevelopment.tiles[13][4].occupy(this);
        
        
        GameDevelopment.tiles[2][11].occupy(this);
        GameDevelopment.tiles[3][11].occupy(this);
        GameDevelopment.tiles[4][11].occupy(this);
        GameDevelopment.tiles[5][11].occupy(this);
        GameDevelopment.tiles[6][11].occupy(this);
         
        GameDevelopment.tiles[8][11].occupy(this);
        GameDevelopment.tiles[9][11].occupy(this);
        GameDevelopment.tiles[10][11].occupy(this);
        GameDevelopment.tiles[11][11].occupy(this);
        GameDevelopment.tiles[12][11].occupy(this);
        GameDevelopment.tiles[12][11].occupy(this);
        GameDevelopment.tiles[13][11].occupy(this);
        
        
        GameDevelopment.tiles[2][5].occupy(this);
        GameDevelopment.tiles[2][6].occupy(this);
        GameDevelopment.tiles[2][7].occupy(this);
        
        GameDevelopment.tiles[2][9].occupy(this);
        GameDevelopment.tiles[2][10].occupy(this);
        
        
        GameDevelopment.tiles[13][5].occupy(this);
        GameDevelopment.tiles[13][6].occupy(this);
        GameDevelopment.tiles[13][7].occupy(this);
        
        GameDevelopment.tiles[13][9].occupy(this);
        GameDevelopment.tiles[13][10].occupy(this);
    }            
    
    @Override
    public void draw(Graphics g){        
        Graphics2D gfx = (Graphics2D) g;
        //could iterate through tiles for tags?
        
        
        for (int i = 0; i < GameDevelopment.tiles.length; i++) {
            for (int y = 0; y < GameDevelopment.tiles[0].length; y++) {
                if(GameDevelopment.tiles[i][y].check().equals("wall")){
                    //Draw wall...
                    if((y != 0 && GameDevelopment.tiles[i][y-1].check().equals("wall")) || (y != DimensionWidth/16 - 1 && GameDevelopment.tiles[i][y+1].check().equals("wall"))){
                        gfx.drawImage(vertWall, i*16, y*16, null);
                    }
                    if((i != 0 && GameDevelopment.tiles[i-1][y].check().equals("wall")) || (i != DimensionHeight/16 - 1 && GameDevelopment.tiles[i+1][y].check().equals("wall"))){
                        gfx.drawImage(horiWall, i*16, y*16, null);
                    }
                }
            }
        }
    }
      
}
