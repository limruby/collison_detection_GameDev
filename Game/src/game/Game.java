package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable { //execute code in the thread

    // Creating JFrame as the game window
    public static final int WIDTH =500;  //Constant width for the frame
    public static final int HEIGHT =WIDTH /12*9; //Constant height for the frame
    public static final int SCALE =2;  // scale for resizing
    public final String TITLE = "Collision Detection";  // Title of the window
    
    private boolean running =false;
    private Thread thread; //Initialize thread
    
    //Buffer whole window, describe image with an accessible buffer of image data with 8-bit RGB 
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    
    private boolean is_shooting =false;
    
    private int asteriod_count =5;  //set number of asteriod coming down
    private int asteriod_destroy =0;

    public int getAsteriod_count() {
        return asteriod_count;
    }

    public void setAsteriod_count(int asteriod_count) {
        this.asteriod_count = asteriod_count;
    }

    public int getAsteriod_destroy() {
        return asteriod_destroy;
    }

    public void setAsteriod_destroy(int asteriod_destroy) {
        this.asteriod_destroy = asteriod_destroy;
    }
    
   

    
    //Initialization
    public void init (){
        requestFocus(); // no need to click on window before moving the player
        BufferedImageLoader load = new BufferedImageLoader();
        
        try{
            spriteSheet = load.loadImage("/sprite_sheet_nobg.png"); //throw exception if error occurs
        }catch (IOException e){
        }
        addKeyListener(new Movement(this)); //Add keylistener for arrow keys
        tex = new Textures(this);
        p = new Player (200,200, tex);  //parameters as the window location
        c= new Controller(tex, this);
        
        c.addAsteriod(asteriod_count);
        ea = c.getEntityA();
        eb =c.getEntityB();
        
        
    }
    
    private Player p; //initiate player
    private Controller c; //initiate game controls
    private Textures tex; //store asset images
    
    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;
    
    //Synchronization to prevent thread error//
    private synchronized void start(){ 
        if(running)
            return; //exit method
        
        running =true; //start game loop
        thread = new Thread(this);
        thread.start(); //invoke thread
    }
    
    private synchronized void stop(){
        try {
            if(!running)
                return;
            
            running =false; //stop game loop
            thread.join(); //join threads
        } catch (InterruptedException ex) {//exiting if interruption occurs
            
                    }
        System.exit(1);
    }
    
    public void run() { 
        // controlling fps 
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0; //60 frame per second
        double ns = 1000000000 /amountOfTicks;
        double delta =0; //constant to keep fps checked
        int updates =0;
        int frame =0;
        long timer = System.currentTimeMillis();
        
        while(running){
            //running game loop
           long now = System.nanoTime();
           delta += (now - lastTime)/ns;  
           lastTime =now;
           if (delta >=1){
               tick();
               updates++;
               delta--;  
           }
           render();
           frame++;
           if(System.currentTimeMillis()-timer>1000){ //avoid multiple loop
               timer +=1000;
               System.out.println(updates + "Ticks, Fps "+ frame);
               updates =0; //reset update to keep 60 ticks
               frame =0; // reset frame to keep 60 fps
           }
        }
        stop(); //stop game loop 
    }
    
    private void tick(){ //Constant updates of the game
        p.tick();
   //    a.tick();
        c.tick();
    }
    
    private void render (){ //Rendering of the game
        //Handle buffers
        BufferStrategy bs = this.getBufferStrategy(); // "this" inherits Canvas 
        if(bs ==null){ //initialize buffer once only
            createBufferStrategy(3); //tripple buffering to load images faster
            return;
        }
        Graphics g =bs.getDrawGraphics(); //create graphics to draw buffer
        ////////////////////////DRAW YOUR IMAGE//////////////////////////////
        g.drawImage(image, 0,0, getWidth(), getHeight(), this);
        p.render(g); //draw player in the screen 
        c.render(g);
        ////////////////////////////////////////////////////////////////////
        g.dispose(); //avoid buffer becoming null when looping again
        bs.show(); //present drawing to screen
    }
    public void keyPressed(KeyEvent e){
        // check for arrow key pressed
        // use getKeyCode to get key pressed    
          int keyCode = e.getKeyCode();
         
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                //Move player to right
                p.setVelX(5);
                break;
            case KeyEvent.VK_LEFT:
                //Move player to left
                p.setVelX(-5);
                break;
            case KeyEvent.VK_DOWN:
                ////Move player to down
                p.setVelY(5);
                break;
            case KeyEvent.VK_UP:
                //Move player to up
                p.setVelY(-5);
                break;
            case KeyEvent.VK_SPACE:
          //Fire bullet by pressing spacebar
                c.addEntity(new Bullet(p.getX(),p.getY(),tex, this));
                break;
            default:
               break;
        }
        
           
    }
    public void keyReleased(KeyEvent e){
         int keyCode = e.getKeyCode();
         
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                //Move player to right
                p.setVelX(0);
                break;
            case KeyEvent.VK_LEFT:
                //Move player to left
                p.setVelX(0);
                break;
            case KeyEvent.VK_DOWN:
                ////Move player to down
                p.setVelY(0);
                break;
            case KeyEvent.VK_UP:
                //Move player to up
                p.setVelY(0);
                break;
                 case KeyEvent.VK_SPACE:
          //Fire bullet by pressing spacebar
                c.removeEntity(p);
                break;
            default:
               break;
        }
    }
    
    public static void main (String args[]){
        Game game = new Game ();
        game.setPreferredSize(new Dimension (WIDTH* SCALE, HEIGHT*SCALE));
        game.setMaximumSize(new Dimension (WIDTH* SCALE, HEIGHT*SCALE));
        game.setMinimumSize(new Dimension (WIDTH* SCALE, HEIGHT*SCALE));
        
        
        JFrame frame = new JFrame(game.TITLE);  //Creating frame
        frame.add(game);   //Showing the game into the frame
        frame.pack();  //Contents are at or above preferred size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closing frame when exit
        frame.setResizable(false); //To forbid frame to resize 
        frame.setLocationRelativeTo(null); //set location of window to null (centered on screen)
        frame.setVisible(true); //To show the frame
        
        game.start();
    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
}
    

}
