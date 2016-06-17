
package gamedevelopment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JPanel;

public class Draw extends JPanel{
    
    //Size
    int DimensionHeight;
    int DimensionWidth;
    
    // Components
    ArrayList components;
    int amount;
    
    public Draw(DrawGraphics ... _components){
        //Size
        DimensionWidth = GameDevelopment.DimensionWidth;
        DimensionHeight = GameDevelopment.DimensionHeight;
        setPreferredSize(new Dimension(DimensionWidth, DimensionHeight));        
        setBackground(new Color(80, 0, 255));
        
        //Components
        components = new ArrayList();
        components.addAll(Arrays.asList(_components));
        amount = components.size();
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(DimensionWidth, DimensionHeight);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < 16; i++){
            for(int y = 0; y < 16; y++){
                g.drawRect(i*16, y*16, 16, 16);
            }
        }
        for(int i = 0; i < amount; i++){
            ((DrawGraphics)components.get(i)).draw(g);
        }
        
        repaint();
    }
    
    public void addComponent(DrawGraphics ... dG){
        components.addAll(Arrays.asList(dG));
        amount += dG.length;
    }
    
    public void reset(){
        components.clear();
    }
  
}
