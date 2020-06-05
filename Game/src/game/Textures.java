/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
//Storing images from sprite sheet
public class Textures {
    
    public BufferedImage player, asteroid, bullet;
    private SpriteSheet ss;
    
    public Textures(Game game){
        ss = new SpriteSheet(game.getSpriteSheet());
        getTextures();
    }

    private void getTextures() {
       player = ss.grabImage(1, 1, 32, 32);
       asteroid = ss.grabImage(2, 1, 32, 32);
       bullet = ss.grabImage(3, 1, 32, 32);
    }
}
