package test;

import com.nino.PropertyMgr;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nino
 * @date 2020-05-21 15:59
 */
class ImageTest extends Object {

    @Test
    void test(){
        try {
            BufferedImage image2 = ImageIO.read( ImageTest.class.getClassLoader().getResourceAsStream( "images/bulletD.gif" ) );
            assertNotNull( image2 );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test1(){
        Object o = PropertyMgr.get( "initTankCount" );
        System.out.println(o);
    }

}