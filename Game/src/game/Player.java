/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject implements EntityA  {
        
    private Textures tex;
    private Game game;
    private Controller c;
    
    //Ensure smooth movement
    private double velX=0; 
    private double velY =0;
      
    //initialize events in player class
    public Player (double x, double y, Textures tex){
        super(x,y);
        this.tex =tex;
    }
    
    public void tick(){
        x+=velX;
        y+=velY;
        
        // collision bound to window frame
        if(x<=0)
            x=0;
        if(x>=900)
            x=900;
        if(y<=0)
            y=0;
        if(y>=720)
            y=720;
        
       
        
    }
    public void render (Graphics g){
        g.drawImage(tex.player, (int)x, (int)y, null); 
    }
    // Encapsulate the player with rectangle to detect collision
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
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
    public void setVelX(double velX){
        this.velX = velX;
    }
    public void setVelY(double velY){
        this.velY = velY;
    }
    
    
}
