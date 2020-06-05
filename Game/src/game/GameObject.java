/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Rectangle;

public class GameObject {
    public double x,y;
    
    public GameObject(double x, double y){
        this.x =x;
        this.y =y;
        
    }
    
    public Rectangle getBounds(int width, int height){
        return new Rectangle ((int)x, (int)y, width, height);
    }
}
