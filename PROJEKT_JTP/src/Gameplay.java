/**
 * Importing classes that are necessary for some functions to work
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Font;
import java.util.Random;

/**
 * class in which all of the gameplay aspects are taking place in
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener {

    /**
     * "play" tells us whether we started the game yet or not
     */
    private boolean play = false;


    /**
     * this one is pretty straightforwad "score" is a variable in which current score number is stored
     */
    private int score = 0;

    /**
     * Creating Random class object which I used later
     */
    private Random rand = new Random();
    /**
     * Creating Timer class object which I use to control the pace of the gameplay
     */
    private Timer timer;
    /**
     *This is where delay for the timer is set
     */
    private int delay=1; //5

    /**
     * Starting positions for the paddles
     */
    private int player1 =483;  //green
    private int player2 =116; //red

    /**
     *co-ordinates of top, left and right screen borders
     */

    private int bor1posX=0;
    private int bor1posY=0;

    private int bor2posX=0;
    private int bor2posY=0;

    private int bor3posX=691;
    private int bor3posY=0;


    /**
     * co-ordinates and direction of the ball
     * randomizing co-ordinates
     */
    private int ballposX=rand.nextInt(660)+5;  //230
    private int ballposY=rand.nextInt(480)+20; //20-500
    private int ballXdir=-2; //-2
    private int ballYdir=-2; //-2

    /**
     * co-ordinates and direction of obstacles
     */
    private int obst1posX=270;
    private int obst1posY=270;
    private int obst1Xdir=-2;

    private int obst2posX=400;
    private int obst2posY=170;
    private int obst2Xdir=2;

    private int obst3posX=150;
    private int obst3posY=70;
    private int obst3Xdir=-2;

    /**
     * co-ordinates of powerups
     * randomizing co-ordinates
     */
    private int pow1posX=rand.nextInt(650)+5;  //130
    private int pow1posY=rand.nextInt(480)+10;   //50
    private int pow2posX=rand.nextInt(650)+5; //660 +5
    private int pow2posY=rand.nextInt(480)+10;   //490 +10

    /**
     * "menuTrigger" determines whether we should display menu or game at the moment
     */
    private boolean menuTrigger=true;


    /**
     * "b" tells us which paddle can the ball be bounced off with
     */
    private int b=1;
    /**
     * "powtimer" is a variable which I use to determine how long should a powerup last
     */
    private int powtimer=0;
    /**
     * "start" tells me did the first game start
     */
    private int start=0;
    /**
     * "whichpow" stores the information on which powerup was triggered
     */
    private int whichpow=0;


    /**
     * I add keyboard handling service and set a timer
      */

    public Gameplay(){
        addKeyListener(this);
        //addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
       timer = new Timer(delay,this);
       timer.start();
    }

    /**
     *"paint" function is responsible for displaying both gameplay and menu elements
     */

    public void paint(Graphics g) {

        /**
         * instance of "Graphics2D" which I later use for displaying text with different fonts
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Font fnt3 = new Font ("axure handwriting",Font.BOLD,14);


        /**
         * Menut triggerd by the "menuTrigger" variable
         */
        //*****************MENU****************
        if(menuTrigger){
            /**
             * background color and size
             */
            g.setColor(Color.red);
            g.fillRect(1, 1, 692, 592);

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Font fnt0 = new Font ("algerian",Font.PLAIN,98);
                Font fnt1 = new Font ("axure handwriting",Font.BOLD,40);

            /**
             * Title of the game
             */
            g.setFont(fnt0);
                g2d.setColor(Color.black);
                g2d.drawString("FLOOR IS LAVA",3,150);

            /**
             * Instructions on how to enter the game and how to quit it
             */
            g.setFont(fnt1);
                g2d.drawString("Press ENTER to play",150,450);
                g2d.drawString("Press ESC to quit",150,500);

            /**
             * if "start" equals 1 and we lose then it displays a screen with our score
             */
            if(start==1){
                    Font fnt2 = new Font ("chiller",Font.PLAIN,50);
                    g.setFont(fnt2);
                    g2d.drawString("Game Over...",235,300);
                    g2d.drawString("Your score: "+score,235,350);
                }


            }
        //**************************************

        /**
         * All the drawing necessary for gameplay
         */
        else {
            /**
             * Background
             */
            g.setColor(Color.black);
            g.fillRect(1, 1, 692, 592);

            /**
             * Borders
             */
            g.setColor(Color.white);
            g.fillRect(bor1posX, bor1posY, 3, 592);
            g.fillRect(bor2posX, bor2posY, 692, 3);
            g.fillRect(bor3posX, bor3posY, 3, 592);

            /**
             * Bottom border
             */
            g.setColor(Color.RED);
            g.fillRect(3, 560, 688, 10);  //(0, 570, 692, 10)


            /**
             * Drawing paddle1 and paddle2 but they change their colour to gray when they are not active
             */
            if (b == 1) {
                g.setColor(Color.green);
            } else {
                g.setColor(Color.gray);
            }
            g.fillRect(player1, 550, 70, 8); //h:8

            //paddle 2
            if (b == 2) {
                g.setColor(Color.blue);
            } else {
                g.setColor(Color.gray);
            }
            g.fillRect(player2, 550, 70, 8);

            /**
             * drawing theball
             */
            g.setColor(Color.orange);
            g.fillOval(ballposX, ballposY, 15, 15);

            /**
             * drawing obstacles
             */

            g.setColor(Color.white);
            g.fillRect(obst1posX, obst1posY, 100, 1);
            g.fillRect(obst1posX,obst1posY-12,100,1);

            g.fillRect(obst2posX, obst2posY, 100, 1);
            g.fillRect(obst2posX,obst2posY-12,100,1);

            g.fillRect(obst3posX, obst3posY, 100, 1);
            g.fillRect(obst3posX,obst3posY-12,100,1);


            /**
             * drawing powerups
             */

            g.setColor(Color.magenta);
            g.fillOval(pow1posX, pow1posY, 30, 30);

            g.setColor(Color.cyan);
            g.fillOval(pow2posX, pow2posY, 30, 30);

            /**
             * displaying which powerup is currently triggered
             */
            g.setFont(fnt3);
           if(powtimer<600&&whichpow==1){
                g2d.setColor(Color.red);
                g2d.drawString("SPEED BOOST",585,50);
            }

           if(powtimer<600&&whichpow==2){
                g2d.setColor(Color.red);
                g2d.drawString("SLOWMOTION",585,50);
            }

            /**
             * displaying the score
             */
            Font fnt4 = new Font ("arial",Font.PLAIN,14);
            g.setFont(fnt4);
            g2d.setColor(Color.white);
            g2d.drawString("Score: "+score,630,30);

            g.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * Starting the timer
         */
        timer.start();

        if(play){



            /**
             * Setting "start" variable value to 1 so we now know that the first instance of the game has just started
             */
            start=1;

            /**
             * "a" is a variable that doesn't allow the ball to bounce off from two paddles at once
             */
            int a=0;

            /**
             * Setting hitboxes for all of the in game objects obstacles, powerups, borders etc.
             */

            //paddles and ball
            Rectangle ball = new Rectangle(ballposX, ballposY,15,15);
            Rectangle P1 = new Rectangle(player1,550,70,8);
            Rectangle P2 = new Rectangle(player2,550,70,8);


            //borders
            Rectangle bor1 = new Rectangle(bor1posX, bor1posY, 3, 592);
            Rectangle bor2 = new Rectangle(bor2posX, bor2posY, 692, 3);
            Rectangle bor3 = new Rectangle(bor3posX, bor3posY, 3, 592);
            Rectangle bor4 = new Rectangle(0, 570, 692, 10);

            //obstacles

            Rectangle obst1 = new Rectangle(obst1posX,obst1posY,100,1); //height:6
            Rectangle obst2 = new Rectangle(obst2posX,obst2posY,100,1); //height:6
            Rectangle obst3 = new Rectangle(obst3posX,obst3posY,100,1); //height:6

            Rectangle obst4 = new Rectangle(obst1posX,obst1posY-12,100,1); //height:6
            Rectangle obst5 = new Rectangle(obst2posX,obst2posY-12,100,1); //height:6
            Rectangle obst6 = new Rectangle(obst3posX,obst3posY-12,100,1); //height:6

            //powerups

            Rectangle pow1 = new Rectangle(pow1posX,pow1posY,30, 30);
            Rectangle pow2 = new Rectangle(pow2posX,pow2posY,30, 30);


            /**
             * Setting intersection with paddles keeping in mind that only on is active at a time("b" variable) and ball can only bounce off once("a" variable)
             */
            //Intersection with paddle1
           if(ball.intersects(P1)){
               if((b==1)&&(a==0)) {
                   ballYdir = -ballYdir;
                   b++;
                   a++;
                   score+=5;
               }
            }

            //Intersection with paddle2
            if(ball.intersects(P2)){
                if((b==2)&&(a==0)) {
                    ballYdir = -ballYdir;
                    b--;
                    a++;
                    score+=5;
                }
            }

            /**
             * Setting intersection with the ball and borderds so the ball can bounce away from them
             */
            if((ball.intersects(bor1))||(ball.intersects(bor3))){
                ballXdir=-ballXdir;
            }

            if(ball.intersects(bor2)){
                ballYdir=-ballYdir;
            }

            /**
             * Setting intersections with the ball and 3 obstacles so the ball can bounce away from them
             * There are free obst objects because insinde every obstacles is a tiny tunnel in which ball can also move and bounce
             */
            if((ball.intersects(obst1))||(ball.intersects(obst2))||(ball.intersects(obst3))||(ball.intersects(obst4))||(ball.intersects(obst5))||(ball.intersects(obst6))){
                ballYdir=-ballYdir;
            }


            /**
             * Setting intersection with the ball and powerups
             * Setting all the objects to move at a faster rate
             */

            if(ball.intersects(pow1)){
                if(ballXdir<0) {ballXdir=-4;} //3
                if(ballYdir<0) {ballYdir=-4;}
                if(ballXdir>0) {ballXdir=4;}
                if(ballYdir>0) {ballYdir=4;}
                if(obst1Xdir>0){obst1Xdir=4;} else{obst1Xdir=-4;}
                if(obst2Xdir>0){obst2Xdir=4;} else{obst2Xdir=-4;}
                if(obst3Xdir>0){obst3Xdir=4;} else{obst3Xdir=-4;}
                powtimer=1;
                whichpow=1;
            }

            /**
             * Setting intersection with the ball and powerups
             * Setting all the objects to move at a slower rate
             */
            if(ball.intersects(pow2)){
                if(ballXdir<0) {ballXdir=-1;}
                if(ballYdir<0) {ballYdir=-1;}
                if(ballXdir>0) {ballXdir=1;}
                if(ballYdir>0) {ballYdir=1;}
                if(obst1Xdir>0){obst1Xdir=1;} else{obst1Xdir=-1;}
                if(obst2Xdir>0){obst2Xdir=1;} else{obst2Xdir=-1;}
                if(obst3Xdir>0){obst3Xdir=1;} else{obst3Xdir=-1;}
                powtimer=1;
                whichpow=2;
            }
            /**
             * timer for the powerups
             * when "powtimer" value exceeds 600 then everything comes back to normal
             */
            if(powtimer>0){
                powtimer++;
            }
            if(powtimer>600){
                if(ballXdir<0) {ballXdir=-2;}
                if(ballYdir<0) {ballYdir=-2;}
                if(ballXdir>0) {ballXdir=2;}
                if(ballYdir>0) {ballYdir=2;}
                if(obst1Xdir>0){obst1Xdir=2;} else{obst1Xdir=-2;}
                if(obst2Xdir>0){obst2Xdir=2;} else{obst2Xdir=-2;}
                if(obst3Xdir>0){obst3Xdir=2;} else{obst3Xdir=-2;}
                powtimer=0;
                whichpow=0;
            }

            /**
             * Setting intersection with the ball and bottom border
             * it means the game is over so we set "play" to false and "menuTrigger" to true so the menu is displayed
             * im also resetting values of some variables and randomizing a new starting position for the ball
             */
            if(ball.intersects(bor4)){
                play=false;
                menuTrigger=true;
                whichpow=0;
                powtimer=0;
                ballposX=rand.nextInt(660)+5;
                ballposY=rand.nextInt(480)+20;
                ballXdir=-2;
                ballYdir=-2;
                obst1Xdir=-2;
                obst2Xdir=2;
                obst3Xdir=-2;
            }
            /**
             * adding "ballXdir" and "ballYdir" values to variables "ballposX" and "ballposY" which means changing the co-ordinates of the ball which makes it move
             */
            ballposX+=ballXdir;
            ballposY+=ballYdir;


            /**
             * intersection of obstacles and borders so they keep moving and changing direction
             */
            //**********************OBSTACLES MOVING AND CHANGING DIRECTIONS**************************

            if((obst1.intersects(bor1))||(obst1.intersects(bor3))){
                obst1Xdir=-obst1Xdir;
            }

            if((obst2.intersects(bor1))||(obst2.intersects(bor3))){
                obst2Xdir=-obst2Xdir;
            }

            if((obst3.intersects(bor1))||(obst3.intersects(bor3))){
                obst3Xdir=-obst3Xdir;
            }


            obst1posX+=obst1Xdir;
            obst2posX+=obst2Xdir;
            obst3posX+=obst3Xdir;
        }
        System.gc();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        /**
         * handling the keys required for the movement of the paddles
         * "b" tells which paddle is currently active
         * we also have conditions which don't let paddles move out of the screen
         */
        if(!menuTrigger) {
            //PADDLE 1
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (b == 1) {
                    if (player1 >= 620) {
                        player1 = 620;
                    } else {
                        RightP1();
                    }
                }
                else if(b==2){
                    if (player2 >= 620) {
                        player2 = 620;
                    } else {
                        RightP2();
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (b == 1) {
                    if (player1 <= 4) {
                        player1 = 4;
                    } else {
                        LeftP1();
                    }
                }
                else if(b==2){
                    if (player2 <= 4) {
                        player2 = 4;
                    } else {
                        LeftP2();
                    }
                }
            }

        }

        /**
         * ENTER disables the menu and let us start the game
         * it also resets the score
         */
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if (menuTrigger) {
                menuTrigger=false;
                score=0;
            }
        }

        /**
         * ESCAPE quits the game
         */
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }

    }

    /**
     * functions that allow the paddle1 and paddle2 movement
     * values of their co-ordinates are being changed so that they move
     */
    private void RightP1(){
        play = true;
        player1 +=15;
    }

    private void LeftP1(){
        play = true;
        player1 -=15;
    }

    private void RightP2(){
        play = true;
        player2 +=15;
    }

    private void LeftP2(){
        play = true;
        player2 -=15;
    }

}
