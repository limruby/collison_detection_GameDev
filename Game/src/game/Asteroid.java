/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author user
 */
public class Asteroid extends GameObject implements EntityB{   
    Random r= new Random();
    private Game game;
    private Controller c;
    
    private int speed = r.nextInt(10)+1;
    
    private Textures tex;
    
    
    public Asteroid(double x, double y, Textures tex, Controller c, Game game){
    super(x,y);
    this.tex = tex;   
    this.c =c;
    this.game =game;
}
    @Override
    public void tick(){
       y+=speed;  //asteriod coming down with different speed
       
       if(y>Game.HEIGHT*Game.SCALE){  //if asteriod exceed window 
           speed = r.nextInt(3)+1;
           x =r.nextInt(640); //jumps to top with random x position
           y=-10;  
            
       }
       c.checkCollisions();     
      
    }
    @Override
    public void render (Graphics g){
        g.drawImage(tex.asteroid, (int)x, (int)y, null);      
    }
    // Encapsulate the asteriod with rectangle to detect collision
    @Override
     public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }
    
    @Override
    public double getX(){
        return x;
    }
    
    @Override
    public double getY(){
        return y;
    }
    
    public void setX(double x){
        this.x = x;
    }
    
    public void setY(double y){
        this.y = y;
    }
        
    
    
}


