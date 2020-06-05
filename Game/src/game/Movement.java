/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Controlling player movement
public class Movement extends KeyAdapter{
    
    Game game;
    
    public Movement (Game game){
        this.game =game;
    }
    
    
    @Override
    public void keyPressed(KeyEvent e){
        game.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e){
        game.keyReleased(e);
    }
}
