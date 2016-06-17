
package gamedevelopment;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet{
   
    private static BufferedImage mainImage;

    public static void getSprites(){
        try{
        mainImage = ImageIO.read(new File("src/gamedevelopment/16x16_Sprite_Sheet.png"));       
        } catch(IOException e){ 
            System.out.println(e);
        }
    }
    
    public static Image[] createCharacterIMG(){
        Image[] temp = new Image[4];
        for(int i = 0; i < 4; i++){
            temp[i] = mainImage.getSubimage(i*16, 0, 16, 16);
        }  
        return temp;
    }
    
    public static Image createGunSprite(){
        return mainImage.getSubimage(64, 0, 16, 16);
    }
    
    public static BufferedImage createWallSprite(){
        return mainImage.getSubimage(80, 0, 16, 16);
    }
    
    public static Image createPelletSprite(){
        return mainImage.getSubimage(96, 0, 16, 16);
    }
    
    public static Image[] createEnemyIMG(){
        Image[] temp = new Image[4];
        for(int i = 7; i < 11; i++){
            temp[i] = mainImage.getSubimage(i*16, 0, 16, 16);
        }  
        return temp;
    }
}
