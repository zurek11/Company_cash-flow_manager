package sk.stubafiit.xzurek.CCFM;

import sk.stubafiit.xzurek.FirmMenu.FirmMenuControl;
import sk.stubafiit.xzurek.RegLogMenu.RegLogControl;

import java.io.File;

/**
 * Created by Adam Žúrek on 3/8/2016.
 *
 * <u>Main class - run one from two menus which depends if firm is registered or not.</u>
 */

public class CCFM {
    /**
     * Main class with method main
     * @param argc Basic arguments
     */
    public static void main(String[] argc){
        File file=new File("firm.db");
        if(file.exists()){
           new RegLogControl(); //If firm is registered (if database firm exists)
        }
        else{
            new FirmMenuControl();  //If firm is not registered
        }
    }
}