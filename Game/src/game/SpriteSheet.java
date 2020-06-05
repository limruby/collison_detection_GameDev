
package game;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * store images
 */
public class SpriteSheet {
    
    private BufferedImage image;
    
    public SpriteSheet (BufferedImage image){
        this.image = image;
    }
    
    public BufferedImage grabImage (int col, int row, int width, int height){
        
        BufferedImage img = image.getSubimage((col*32)-32, (row*32)-32, width, height);
        ////// the sprite is 32 pixels * 32 pixels, need to set location
        return img;
    }
}
