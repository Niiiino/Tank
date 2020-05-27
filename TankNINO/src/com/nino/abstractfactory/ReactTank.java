package com.nino.abstractfactory;

import com.nino.*;

import java.awt.*;
import java.util.Random;

/**
 * @author Nino
 * @date 2020-05-20 15:58
 */
public class ReactTank extends BaseTank {
    int x,y;
    Dir dir = Dir.DOWN;
    private static final int speed = 5;
    private boolean moving = true;
    TankFrame tf = null;
    private boolean live = true;
    private Random random = new Random();
    Group group = Group.BAD;
    FireStrategy fs;

    public Rectangle rect = new Rectangle();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public static final int WIDTH  = ResourceMgr.goodTankD.getWidth();
    public static final int HEIGHT  = ResourceMgr.goodTankD.getHeight();

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public static int getSpeed() {
        return speed;
    }

    public ReactTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        if (this.group == Group.GOOD){
            String goodFSName = PropertyMgr.get( "goodFS" );
            try {
                fs = (FireStrategy) Class.forName( goodFSName ).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            String badFSName = PropertyMgr.get( "badFS" );
            try {
                fs = (FireStrategy) Class.forName( badFSName ).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void paint(Graphics g) {

        if(!live){
            tf.tanks.remove( this );
        }

       /* if(dir == Dir.LEFT){
            g.drawImage( this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null );
        }
        if(dir == Dir.UP){
            g.drawImage( this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null );
        }
        if(dir == Dir.RIGHT){
            g.drawImage( this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null );
        }
        if(dir == Dir.DOWN){
            g.drawImage( this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null );
        }*/
       Color c =g.getColor();
       g.setColor( group == Group.GOOD?Color.CYAN:Color.MAGENTA );
       g.fillRect( x,y,40,40 );
       g.setColor( c );

        move();

    }

    private void move() {
        if(!moving) return;
        /*switch (dir){
            case LEFT:
                x -= speed;
                break;
            case UP:
                y -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
        }*/
        if(dir == Dir.LEFT){
            x -= speed;
        }
        if(dir == Dir.UP){
            y -= speed;
        }
        if(dir == Dir.RIGHT){
            x += speed;
        }
        if(dir == Dir.DOWN){
            y += speed;
        }
        if (this.group == Group.BAD && random.nextInt( 100) >95){
             randomDir();
             this.fire();
        }
        //边界检测
        boundCheck();

        //更新rect的值
        rect.x = x;
        rect.y = y;
    }

    private void boundCheck() {
        if(x<2){
            x=2;
        }
        if (x>TankFrame.GAME_WIDTH - ReactTank.WIDTH){
            x=TankFrame.GAME_WIDTH - ReactTank.WIDTH;
        }
        if(y<28){
            y=28;
        }
        if(y>TankFrame.GAME_HEIGHT - ReactTank.HEIGHT){
            y=TankFrame.GAME_HEIGHT - ReactTank.HEIGHT;
        }
    }

    //敌方坦克随机移动
    private void randomDir() {
       this.dir = Dir.values(  )[random.nextInt( 4 )];
    }

    public void fire() {
        //策略模式
        // fs.fire( this );
        int bx = this.x+ ReactTank.WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y+ ReactTank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            this.tf.gf.createBullet(bx,by,dir,this.group,this.tf );
        }
         if (this.group == Group.GOOD){
            new Thread(() ->{
                new Audio( "audio/tank_fire.wav" ).play();
            }).start();
        }
    }

    public void die() {
        live = false;
    }
}
