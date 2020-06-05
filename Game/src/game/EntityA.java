/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Rectangle;
//Do not collide with objects in Entity A
public interface EntityA {
    
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();
    
    public double getX();
    public double getY();
    
}
