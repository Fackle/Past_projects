import javax.swing.*;

public class Main {


    public static void main(String[] args) {

        /**
         * new JFrame object on which we will work
         */
        JFrame obj = new JFrame();


        /**
         * creating new object of Gameplay class
         */
        Gameplay gameplay = new Gameplay();

        /**
         * setting bounds for our game's window
         */
        obj.setBounds(10,10,700,600);

        /**
         * setting the title for our game's window
         */
        obj.setTitle("Floor is Lava");

        /**
         * not allowing to resize the window
         */
        obj.setResizable(false);

        /**
         *makes our window visible
         */
        obj.setVisible(true);
        /**
         * setting what will happen after we close our game
         */
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * adding Gameplay object to JFrame object which puts our game insie a window
         */
        obj.add(gameplay);


    }
}
