package com.nino;

import java.awt.*;

/**
 * @author Nino
 * @date 2020-05-20 17:03
 */
public class Explode extends GameObject {

    public static final int WIDTH  = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT  = ResourceMgr.explodes[0].getHeight();

    private int x,y;

    //private boolean live = true;


    private int step = 0;

        public Explode(int x, int y) {
        this.x = x;
        this.y = y;

        //¿ªÆô±¬Õ¨Éù
        //new Audio( "audio/explode.wav" ).play();

        GameModel.getInstance().add( this );
    }


    public void paint(Graphics g){
        g.drawImage( ResourceMgr.explodes[step++],x,y,null );
        if(step>=ResourceMgr.explodes.length){
            GameModel.getInstance().remove( this );
        }
    }


}
