
package gamedevelopment;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class GameDevelopment extends JFrame{
    
    //Window Stuff ... hopefully
    public static final int DimensionHeight = 256;
    public static final int DimensionWidth = 256;
    
    // Level stuff possibly     
    Tile startingTile;
    
    Player player;
    Walls walls;
    public static Tile[][] tiles;
   
    public GameDevelopment(){
        //Initialize           
        startingTile = tiles[tiles.length/2][tiles[0].length/2];        
        walls = new Walls();
        player = new Player(startingTile);        
        
        //Sceen stuff       
        setTitle("Game");                        
        addKeyListener(player);
        add(new Draw(walls, player), BorderLayout.CENTER);
        setResizable(false);        
        pack();       
        setLocationRelativeTo(null);       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
 
    public static void main(String[] args){
        //Sprite crwation
        SpriteSheet.getSprites();
        //Tile Creation
        tiles = new Tile[DimensionWidth/16 ][DimensionHeight/16];
        for(int i = 0; i < DimensionWidth/16; i++){
            for(int y = 0; y < DimensionHeight/16; y++){
                tiles[i][y] = new Tile(i, y);
            }
        }
        // Game Cretion
        GameDevelopment game = new GameDevelopment();
    }
    
}
