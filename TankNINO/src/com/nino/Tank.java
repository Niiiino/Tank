package com.nino;

import com.nino.decorator.RectDecorator;
import com.nino.decorator.TailDecorator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Nino
 * @date 2020-05-20 15:58
 */
public class Tank extends GameObject {
    int oldx,oldy;
    Dir dir = Dir.DOWN;
    private static final int speed = 5;
    private boolean moving = true;
    private boolean live = true;
    private Random random = new Random();
    FireStrategy fs;
    public Group group = Group.BAD;
    public Rectangle rect = new Rectangle();

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

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

    public Tank(int x, int y, Dir dir,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
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

            GameModel.getInstance().add( this );
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
            GameModel.getInstance().remove( this );
        }

        if(dir == Dir.LEFT){
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
        //��¼�ƶ�֮ǰ��λ��
        oldx =x;
        oldy =y;
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
        //�߽���
        boundCheck();

        //����rect��ֵ
        rect.x = x;
        rect.y = y;
    }

    private void boundCheck() {
        if(x<2){
            x=2;
        }
        if (x>TankFrame.GAME_WIDTH - Tank.WIDTH){
            x=TankFrame.GAME_WIDTH - Tank.WIDTH;
        }
        if(y<28){
            y=28;
        }
        if(y>TankFrame.GAME_HEIGHT - Tank.HEIGHT){
            y=TankFrame.GAME_HEIGHT - Tank.HEIGHT;
        }
    }

    //�з�̹������ƶ�
    private void randomDir() {
       this.dir = Dir.values(  )[random.nextInt( 4 )];
    }

    public void fire() {
        //����ģʽ
        // fs.fire( this );
        int bx = this.x+Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y+Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            GameModel.getInstance().add( new RectDecorator(new TailDecorator(new Bullet(bx,by,dir,this.group))) );
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
    public void turn(){
        if(dir == Dir.LEFT){

            dir = Dir.RIGHT;

        }
        if(dir == Dir.RIGHT){

            dir = Dir.LEFT;

        }
        if(dir == Dir.UP){

            dir = Dir.DOWN;

        }
        if(dir == Dir.DOWN){

            dir = Dir.UP;

        }
    }

    //������ת���ص���ȥ
    public void timereturn(){
        this.x = oldx;
        this.y =oldy;
    }

     List<TankFireObserver> fireObservers = Arrays.asList( new TankFireHandler() );

    public void handleFireKey(){
        TankFireEvent event =new TankFireEvent( this );
        for (TankFireObserver o : fireObservers) {
            o.actionOnFire( event );
        }
    }
}
