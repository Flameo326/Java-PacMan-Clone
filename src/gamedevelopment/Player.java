
package gamedevelopment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Player extends DrawGraphics implements KeyListener, Runnable{
    //Dimensions
    int DimensionHeight;
    int DimensionWidth;
    
    //Movement 
    Tile currTile;
    int xPos, yPos;
    int xDir, yDir;
    int speed = 3;
    boolean xMoving, yMoving;
    Random random;
    
    //Sprites
    int activePos;
    Image[] sprites = new Image[4];
    Image currImage;
    Thread playerMovement; 
    
    Point mousePoint;
    
    public Player(Tile startingTile){
        DimensionWidth = GameDevelopment.DimensionWidth;
        DimensionHeight = GameDevelopment.DimensionHeight;
        mousePoint = new Point(0, 0);
        
        //Movement Setup
        currTile = startingTile;
        currTile.occupy(this);
        xPos = startingTile.xPos;
        yPos = startingTile.yPos;
        xDir = 1;
        yDir = 0;
        xMoving = true;
        random = new Random();        
        
        //Sprites
        sprites = SpriteSheet.createCharacterIMG();
        activePos = 0;
        currImage = sprites[activePos];
        
        tag = "player";
        
        //Movement Initialize
        playerMovement = new Thread(this);
        playerMovement.start();
    } 
    
    @Override
    public void run() {
        while(true){
            move();
            activePos+=1;
            if(activePos >= 4){
                activePos = 0;
            }
            currImage = sprites[activePos];
            System.out.println("Xpos: " + xPos + " Ypos: " + yPos + " CurrTile: " + currTile.x + ", " + currTile.y);            
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                
            }
        }
    }
    
    //probably tile based ...Configure for collision as well
    public void move(){
        if(xMoving){
            xPos += xDir * speed;
            if(GameDevelopment.tiles[currTile.x+xDir][currTile.y].check().equals("wall")){
                changeXDir();
            }else if(Math.abs(xPos - currTile.xPos) >= 16){
                currTile.notOccupy();
                currTile = GameDevelopment.tiles[currTile.x+xDir][currTile.y];
                currTile.occupy(this);
            } 
        } else if(yMoving){
            yPos += yDir * speed;
            if(GameDevelopment.tiles[currTile.x][currTile.y+yDir].check().equals("wall")){
                changeYDir();
            } else if(Math.abs(yPos - currTile.yPos) >= 16){
                currTile.notOccupy();
                currTile = GameDevelopment.tiles[currTile.x][currTile.y+yDir];
                currTile.occupy(this);
            }
        }
    } 
    
    private void changeYDir(){
        yPos = currTile.y*16;
        yDir = 0;
        yMoving = false;
        xDir = (random.nextInt(2) == 0) ? -1 : 1;
        xMoving = true;
    }
    
    private void changeXDir(){
        xPos = currTile.x*16;
        xDir = 0;
        xMoving = false;
        yDir = (random.nextInt(2) == 0) ? -1 : 1;
        yMoving = true;
    }
    
    @Override
    public void draw(Graphics g){               
        g.drawImage(currImage, xPos, yPos, null);
        g.setColor(Color.yellow);
        g.fillRect(mousePoint.x, mousePoint.y, 1, 1);
        g.drawString(mousePoint.toString(), 100, 100);
    }
    
    @Override // Remeber Tile based check...
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            //Resets sprites 
            default:
                System.out.println("Not a supported key " + xPos + " " + yPos);
                break;
            case KeyEvent.VK_W:               
                yMoving = true;
                xMoving = false;
                yDir = -1;
                xDir = 0;                
                break;
            case KeyEvent.VK_S:                
                    yMoving = true;
                    xMoving = false;
                    yDir = 1;
                    xDir = 0;               
                break;
            case KeyEvent.VK_A:                
                    xMoving = true;
                    yMoving = false;
                    xDir = -1;
                    yDir = 0;
                break;
            case KeyEvent.VK_D:
                xMoving = true;
                yMoving = false;
                xDir = 1;
                yDir = 0;
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent ke) {                
    }
    @Override
    public void keyReleased(KeyEvent ke) {                
    }     
}
