package com.nino;

import com.nino.abstractfactory.BaseExPlode;

import java.awt.*;

/**
 * @author Nino
 * @date 2020-05-20 17:03
 */
public class Explode {

    public static final int WIDTH  = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT  = ResourceMgr.explodes[0].getHeight();

    private int x,y;

    //private boolean live = true;

    GameModel gm = null;

    private int step = 0;

        public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;

        //¿ªÆô±¬Õ¨Éù
        //new Audio( "audio/explode.wav" ).play();
    }


    public void paint(Graphics g){
        g.drawImage( ResourceMgr.explodes[step++],x,y,null );
        if(step>=ResourceMgr.explodes.length){
            gm.explodes.remove( this );
        }
    }


}
