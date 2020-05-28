package com.nino;


/**
 * @author Nino
 * @date 2020-05-20 9:38
 */
public class Main {
    public static void main(String[] args) {
        TankFrame tf = new TankFrame();


        while (true){
            try {
                Thread.sleep( 25 );
                tf.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
