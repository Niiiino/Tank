package com.nino;


/**
 * @author Nino
 * @date 2020-05-20 9:38
 */
public class Main {
    public static void main(String[] args) {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt( PropertyMgr.get( "initTankCount" ) );

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add( tf.gf.createTank( 50+i*150,200,Dir.DOWN,Group.BAD,tf ) );
        }

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
