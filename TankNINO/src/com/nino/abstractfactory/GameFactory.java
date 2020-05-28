package com.nino.abstractfactory;

import com.nino.Dir;
import com.nino.GameModel;
import com.nino.Group;
import com.nino.TankFrame;

/**
 * @author Nino
 * @date 2020-05-27 10:23
 */
public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, GameModel gm);
    public abstract BaseExPlode createExplode(int x, int y, GameModel gm);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, GameModel gm);
}
