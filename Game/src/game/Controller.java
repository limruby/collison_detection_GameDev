package game;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
//Storing game controls
public class Controller {
    private LinkedList <EntityA> ea = new LinkedList <>();
    private LinkedList <EntityB> eb = new LinkedList <>();
   
    EntityA entA;
    EntityB entB;
    private Textures tex;
    Random r = new Random();
    private Game game;
    
  
    public Controller(Textures tex, Game game){
        this.tex=tex;
        this.game =game;
    }
    
    public void addAsteriod(int asteriod_count){
        for(int i=0; i<asteriod_count;i++){
           addEntity(new Asteroid (r.nextInt(640),-10, tex, this, game));
    }
    }
    
    public void tick(){
        //Class A
        for(int i=0; i<ea.size();i++){
            entA =ea.get(i);            
            entA.tick();  
            //remove bullet after offscreen
            if(entA.getY()<0){
                this.removeEntity(entA);
            }
        }
        //Class B
        for(int i=0; i<eb.size();i++){
            entB =eb.get(i);
            entB.tick();
        }
        
    }
    
   public void render(Graphics g){
       //class A
       for(int i=0; i<ea.size();i++){
            entA =ea.get(i);
            entA.render(g);
   }
        //class B
       for(int i=0; i<eb.size();i++){
            entB =eb.get(i);
            entB.render(g);
   }
   }
   public void addEntity(EntityA block){
           ea.add(block);
           
       }
   public void removeEntity(EntityA block){
           ea.remove(block);
       }
   public void addEntity(EntityB block){
           eb.add(block);          
       }
   public void removeEntity(EntityB block){
           eb.remove(block);
       }
   public void checkCollisions(){
        for(int i = 0; i< ea.size(); i++){
            entA = ea.get(i);
            for(int j = 0; j < eb.size(); j++){
                entB = eb.get(j);
                if(entA.getBounds().intersects(entB.getBounds())){
                    System.out.println("collision detected! Asteroid destroyed!");
                    removeEntity(entA);
                    removeEntity(entB);
                }
            }
        }
    }
   //Show the list of objects in Entity A & Entity B
   public LinkedList<EntityA> getEntityA(){
       return ea;
   }
   public LinkedList<EntityB> getEntityB(){
       return eb;
   } 
}
   
  
