package sk.stubafiit.xzurek.RegLogMenu;

import sk.stubafiit.xzurek.Defaults.DefaultControllerParent;
import sk.stubafiit.xzurek.LogMenu.LogMenuControl;
import sk.stubafiit.xzurek.RegMenu.RegMenuControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zurek on 3/26/2016.
 *
 * <u>Control class from RegLogMenu</u>
 */

public class RegLogControl extends DefaultControllerParent{
    private RegLogView view;

    /**
     * Constructor RegLogControl.
     * Create objects model, view and calls method init.
     */
    public RegLogControl(){
        view=new RegLogView();
        init();
    }

    /**
     * Method init initializes listeners to buttons.
     */
    private void init(){
        view.getButEnd().addActionListener(endListener);
        view.getButLog().addActionListener(logMenuListener);
        view.getButReg().addActionListener(regMenukListener);
    }

    //FRAME LISTENERS

    /**
     * Listener to logMenu button
     */
    private ActionListener logMenuListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            new LogMenuControl();
        }
    };
    /**
     * Lisener to regMenu button
     */
    private ActionListener regMenukListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            new RegMenuControl();
        }
    };
    /**
     * Listener to end button
     */
    private ActionListener endListener = e -> System.exit(1);
}
