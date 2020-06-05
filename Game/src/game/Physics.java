/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.LinkedList;

/**
 *
 * @author user
 */
//Handle collision detection
public class Physics{
    
    // To detect if objects in Entity A intersects(collide) with objects in Entity B
    public static boolean collision (EntityA entA, LinkedList<EntityB> entB){
        for(int i=0; i<entB.size();i++){ //Loop through class B
            if(entA.getBounds().intersects(entB.get(i).getBounds()));
         //   entB.remove(i);
          //  System.out.println("Collision detected!");
            return true;  //Collision happens
        }        
        return false;// No collsion   
        }    
    
    
    // To detect if objects in Entity B intersects(collide) with objects in Entity A
     public static boolean collision (EntityB entB, LinkedList<EntityA> entA){
        for(int i=0; i<entA.size();i++){
            if(entB.getBounds().intersects(entA.get(i).getBounds()));
            return true;  //Collision happens
        }
        return false; // No collsion
    }
}
