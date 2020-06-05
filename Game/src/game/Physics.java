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
public class Physics {
    // To detect if objects in Entity A intersects(collide) with objects in Entity B
    public static boolean Collision (EntityA entA, LinkedList<EntityB> entB){
        for(int i=0; i<entB.size();i++){
            if(entA.getBounds().intersects(entB.get(i).getBounds()));
            return true;  //Collision happens
        }
        return false; // No collsion
    }
    
    // To detect if objects in Entity B intersects(collide) with objects in Entity A
     public static boolean Collision (EntityB entB, LinkedList<EntityA> entA){
        for(int j=0; j<entA.size();j++){
            if(entB.getBounds().intersects(entA.get(j).getBounds()));
            return true;  //Collision happens
        }
        return false; // No collsion
    }
}
