/*
 * File:           SpritesHandlerImpl.java
 * Date:           January 4, 2015  11:18 AM
 *
 * @author  cuong.nguyen
 * @version generated by NetBeans XML module
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.atom.corex.assets.sprite;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author cuong.nguyen
 */
public class SpritesHandlerImpl implements SpritesHandler {
    public static final boolean DEBUG = false;
    private SpriteSheet result;
    
    public void start_dir(final Attributes meta) throws SAXException {
        if (DEBUG) {
            System.err.println("start_dir: " + meta);
        }
    }

    public void end_dir() throws SAXException {
        if (DEBUG) {
            System.err.println("end_dir()");
        }
    }

    public void start_img(final Attributes meta) throws SAXException {
        if (DEBUG) {
            System.err.println("start_img: " + meta);
        }
        result = new SpriteSheet();
        result.setImagePath(meta.getValue("name"));
    }

    public void end_img() throws SAXException {
        if (DEBUG) {
            System.err.println("end_img()");
        }
    }

    public void start_definitions(final Attributes meta) throws SAXException {
        if (DEBUG) {
            System.err.println("start_definitions: " + meta);
        }
    }

    public void end_definitions() throws SAXException {
        if (DEBUG) {
            System.err.println("end_definitions()");
        }
    }

    public void handle_spr(final Attributes meta) throws SAXException {
        if (DEBUG) {
            System.err.println("handle_spr: " + meta);
        }
        
                
        SpriteTextureInfo sprite = new SpriteTextureInfo();
        sprite.name = meta.getValue("name");
        sprite.x = Integer.parseInt(meta.getValue("x"));
        sprite.y = Integer.parseInt(meta.getValue("y"));
        sprite.width = Integer.parseInt(meta.getValue("w"));
        sprite.height = Integer.parseInt(meta.getValue("h"));
        
        result.addSprite(sprite);
    }
    public SpriteSheet getResult() {
        return result;
    }
}
