package com.nino;


import java.io.Serializable;

/**
 * @author Nino
 * @date 2020-05-26 10:12
 */
public interface FireStrategy extends Serializable {
    public void fire(Tank t);
}
