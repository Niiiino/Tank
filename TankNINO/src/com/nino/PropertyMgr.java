package com.nino;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Nino
 * @date 2020-05-22 13:51
 */
public class PropertyMgr {

   static Properties prop = new Properties();


    static{
        try {
            prop.load( PropertyMgr.class.getClassLoader().getResourceAsStream( "config" ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        if(prop == null){
            return null;
        }
        return (String)prop.get( key );
    }

}
