/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet implements EntityA {
    
    private double x;
    private double y;
    
    private Game game;
    private Textures tex;
    private Controller c;
    
    
    public Bullet(double x, double y, Textures tex, Game game){
    this.x =x; 
    this.y =y;
    this.tex = tex;   
    this.game=game;
}
    public void tick(){
       y-=10;
       if(y>(Game.HEIGHT*Game.SCALE)){  //if asteriod exceed window 
           c.removeEntity(this);
       }
     
       
       //what happen if collision happens
       if(Physics.Collision(this, game.eb)){
           System.out.println("Collision detected! Asteriod has been shot down!"); //Check console output
       }
        
    }
    public void render (Graphics g){
        g.drawImage(tex.bullet, (int)x, (int)y, null);      
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public void setX(double x){
        this.x = x;
    }
    
    public void setY(double y){
        this.y = y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 10, 10);
    }

}
