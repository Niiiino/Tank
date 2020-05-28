package com.nino.abstractfactory;

import com.nino.*;

/**
 * @author Nino
 * @date 2020-05-27 10:42
 */
public class DefaultFactory extends GameFactory {

    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, GameModel gm) {
       /* return new Tank( x,y,dir,group,tf );*/
        return null;
    }

    @Override
    public BaseExPlode createExplode(int x, int y, GameModel gm) {
       /* return new Explode(x,y,tf);*/
        return null;
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, GameModel gm) {
      /*  return new Bullet(x,y,dir,group,tf);*/
        return null;
    }
}
