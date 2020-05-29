package com.nino;

import java.awt.*;

/**
 * @author Nino
 * @date 2020-05-20 17:03
 */
public class Bullet extends GameObject {

    private static final int speed = 30;
    public static final int WIDTH  = ResourceMgr.bulletU.getWidth();
    public static final int HEIGHT  = ResourceMgr.bulletU.getHeight();

    Rectangle rect = new Rectangle();

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }


    private Dir dir;
    private boolean live = true;
        GameModel gm = GameModel.getInstance();

    public GameModel getGm() {
        return gm;
    }

    public void setGm(GameModel gm) {
        this.gm = gm;
    }

    private Group group =Group.BAD;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        GameModel.getInstance().add( this );
    }

    public void paint(Graphics g){

        if (!live){
            GameModel.getInstance().remove( this );
        }

        if(dir == Dir.LEFT){
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
        }

        move();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
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

   /* public boolean collidewith(Tank tank) {

        //检查是否是友军
        if (this.group == tank.getGroup()){
            return false;
        }


        //判断子弹和坦克是否碰撞
        if (rect.intersects( tank.rect )){
            tank.die();
            this.die();
            int ex = tank.getX()+Tank.WIDTH/2 - Explode.WIDTH/2;
            int ey = tank.getY()+Tank.HEIGHT/2 - Explode.HEIGHT/2;
            gm.add( new Explode( ex,ey,gm ) );
            return true;
        }
            return false;
    }*/

    private void die() {
        live = false;
    }
}
