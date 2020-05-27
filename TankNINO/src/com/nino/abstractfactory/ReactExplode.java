package com.nino.abstractfactory;

import com.nino.ResourceMgr;
import com.nino.TankFrame;

import java.awt.*;

/**
 * @author Nino
 * @date 2020-05-27 10:59
 */
public class ReactExplode extends BaseExPlode {

    public static final int WIDTH  = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT  = ResourceMgr.explodes[0].getHeight();

    private int x,y;

    //private boolean live = true;

    TankFrame tf = null;

    private int step = 0;

    public ReactExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        //¿ªÆô±¬Õ¨Éù
        //new Audio( "audio/explode.wav" ).play();
    }

    @Override
    public void paint(Graphics g){

        //g.drawImage( ResourceMgr.explodes[step++],x,y,null );
        Color c =g.getColor();
        g.setColor( Color.red );
        g.fillRect( x,y,10*step,10*step );
        step++;

        if(step>=5){
            tf.explodes.remove( this );
        }

        g.setColor( c );
    }
}
