package com.nino;

import com.nino.abstractfactory.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nino
 * @date 2020-05-20 9:56
 */
public class TankFrame extends Frame {

    GameModel gm = GameModel.getInstance();

    public static final int GAME_WIDTH = 960,GAME_HEIGHT = 680;

    public TankFrame() {
        setSize( GAME_WIDTH, GAME_HEIGHT );
        setResizable( false );
        setTitle( "tank war" );
        setVisible( true );

        addKeyListener( new MyKeyListener() );

        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit( 0 );
            }
        } );
    }

    //∑¿÷π…¡À∏
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage( GAME_WIDTH,GAME_HEIGHT );
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor( Color.black );
        gOffScreen.fillRect( 0,0,GAME_WIDTH,GAME_HEIGHT );
        gOffScreen.setColor( c );
        paint( gOffScreen );
        g.drawImage( offScreenImage,0,0,null );
    }

    @Override
    public void paint(Graphics g) {

        gm.paint( g );

        }


    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            /*switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
            }
            // x += 200;
            //  repaint();
            setMyTankDir();*/
            if(key == KeyEvent.VK_LEFT){
                bL = true;
            }
            if(key == KeyEvent.VK_UP){
                bU = true;
            }
            if(key == KeyEvent.VK_RIGHT){
                bR = true;
            }
            if(key == KeyEvent.VK_DOWN){
                bD = true;
            }
            setMyTankDir();
        }


        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            /*switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;*/
            if(key == KeyEvent.VK_LEFT){
                bL = false;
            }
            if(key == KeyEvent.VK_UP){
                bU = false;
            }
            if(key == KeyEvent.VK_RIGHT){
                bR = false;
            }
            if(key == KeyEvent.VK_DOWN){
                bD = false;
            }
            if(key == KeyEvent.VK_SPACE){
                gm.GetMyTank().fire();
            }
            setMyTankDir();
            }


        private void setMyTankDir() {

            Tank myTank = GameModel.getInstance().GetMyTank();

            if(!bL && !bD && !bR && !bU){
                myTank.setMoving( false );
            }else {
                myTank.setMoving( true );

                if(bL) myTank.setDir( Dir.LEFT );
                if(bU) myTank.setDir( Dir.UP );
                if(bR) myTank.setDir( Dir.RIGHT );
                if(bD) myTank.setDir( Dir.DOWN );
            }

        }

    }

}

