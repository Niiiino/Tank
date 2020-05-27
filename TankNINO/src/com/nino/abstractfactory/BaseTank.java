package com.nino.abstractfactory;

import com.nino.Group;

import java.awt.*;

/**
 * @author Nino
 * @date 2020-05-27 10:26
 */
public abstract class BaseTank {
    public Group group = Group.BAD;
    public Rectangle rect = new Rectangle();

    public abstract void paint(Graphics g);

    public  Group getGroup(){
        return this.group;
    };

    public abstract void die();

    public abstract int getX();

    public abstract int getY();
}
