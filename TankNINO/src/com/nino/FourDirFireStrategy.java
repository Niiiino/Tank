package com.nino;

/**
 * @author Nino
 * @date 2020-05-26 10:31
 */
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bx = t.x+Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = t.y+Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            t.gm.bullets.add(new Bullet( bx,by,dir,t.group,t.gm  ));
        }

       /* if (t.group == Group.GOOD){
            new Thread(() ->{
                new Audio( "audio/tank_fire.wav" ).play();
            }).start();
        }*/
    }
}
