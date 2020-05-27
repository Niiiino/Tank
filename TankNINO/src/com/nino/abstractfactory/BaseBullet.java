package com.nino.abstractfactory;

import com.nino.Tank;

import java.awt.*;

/**
 * @author Nino
 * @date 2020-05-27 10:40
 */
public abstract class BaseBullet {

    public abstract void paint(Graphics g);

    public abstract void collidewith(BaseTank baseTank);
}
