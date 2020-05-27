package com.nino;

import com.nino.abstractfactory.BaseTank;

/**
 * @author Nino
 * @date 2020-05-26 10:13
 */
public class DefaultFireStrategy implements FireStrategy{

    @Override
    public void fire(Tank t) {
        int bx = t.x+Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = t.y+Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        new Bullet( bx,by,t.dir,t.group,t.tf ) ;
        if (t.group == Group.GOOD){
            new Thread(() ->{
                new Audio( "audio/tank_fire.wav" ).play();
            }).start();
        }
    }
}
