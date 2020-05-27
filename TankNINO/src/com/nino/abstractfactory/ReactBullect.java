package com.nino.abstractfactory;

import com.nino.*;

import java.awt.*;

/**
 * @author Nino
 * @date 2020-05-20 17:03
 */
public class ReactBullect extends BaseBullet {

    private static final int speed = 30;
    public static final int WIDTH  = ResourceMgr.bulletU.getWidth();
    public static final int HEIGHT  = ResourceMgr.bulletU.getHeight();

    Rectangle rect = new Rectangle();

    private int x,y;
    private Dir dir;
    private boolean live = true;
    TankFrame tf = null;
    private Group group =Group.BAD;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public ReactBullect(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        tf.bullets.add( this );
    }

    public void paint(Graphics g){

        if (!live){
            tf.bullets.remove( this );
        }

        /*if(dir == Dir.LEFT){
            g.drawImage( ResourceMgr.bulletL,x,y,null );
        }
        if(dir == Dir.UP){
            g.drawImage( ResourceMgr.bulletU,x,y,null );
        }
        if(dir == Dir.RIGHT){
            g.drawImage( ResourceMgr.bulletR,x,y,null );
        }
        if(dir == Dir.DOWN){
            g.drawImage( ResourceMgr.bulletD,x,y,null );
        }*/

        Color c = g.getColor();
        g.setColor( Color.yellow );
        g.fillRect( x,y,20,20 );
        g.setColor( c );

        move();
    }


    private void move() {

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

            if(x<0 || y <0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT ){
                live = false;
            }

            //更新rect的值
            rect.x = x;
            rect.y = y;

    }

    @Override
    public void collidewith(BaseTank tank) {

        //检查是否是友军
        if (this.group == tank.getGroup()){
            return;
        }


        //判断子弹和坦克是否碰撞
        if (rect.intersects( tank.rect )){
            tank.die();
            this.die();
            int ex = tank.getX()+Tank.WIDTH/2 - Explode.WIDTH/2;
            int ey = tank.getY()+Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add( tf.gf.createExplode( ex,ey,tf ) );
        }

    }

    private void die() {
        live = false;
    }
}
