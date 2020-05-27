package com.nino.abstractfactory;

import com.nino.Dir;
import com.nino.Group;
import com.nino.TankFrame;

/**
 * @author Nino
 * @date 2020-05-27 14:24
 */
public class ReactFactory extends GameFactory {

    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new ReactTank( x,y,dir,group,tf );
    }

    @Override
    public BaseExPlode createExplode(int x, int y, TankFrame tf) {
        return new ReactExplode( x,y,tf );
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new ReactBullect( x,y,dir,group,tf );
    }
}
