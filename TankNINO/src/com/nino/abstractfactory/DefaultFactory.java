package com.nino.abstractfactory;

import com.nino.*;

/**
 * @author Nino
 * @date 2020-05-27 10:42
 */
public class DefaultFactory extends GameFactory {

    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Tank( x,y,dir,group,tf );
    }

    @Override
    public BaseExPlode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x,y,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Bullet(x,y,dir,group,tf);
    }
}
