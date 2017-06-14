package sk.stubafiit.xzurek.FirmMenu;

import sk.stubafiit.xzurek.Defaults.DefaultControllerParent;
import sk.stubafiit.xzurek.RegLogMenu.RegLogControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Adam Zurek on 3/15/2016.
 *
 * <u>Control class from FirmMenu</u>
 * Extends class DefaultControllerParent from Defaults package.
 */


public class FirmMenuControl extends DefaultControllerParent{

    private FirmMenuView view;
    private FirmMenuModel model;

    /**
     * Constructor FirmMenu Control.
     * Create objects model, view and calls method init.
     */
    public FirmMenuControl(){
        model=new FirmMenuModel();
        view=new FirmMenuView();
        init();
    }
    /**
     * Method init initializes listeners to buttons.
     */
    private void init(){
        view.getButNext().addActionListener(nextkListener);
        view.getButEnd().addActionListener(endListener);
    }

    //FRAME LISTENERS

    /**
     * Listener to Next button.
     */
    private ActionListener nextkListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            //FULLING VALUES

            for (int i = 0; i < model.getValues().length-5; ++i) {
                model.getValues()[i] = view.getTexField()[i].getText();
            }
            model.getValues()[8]= String.valueOf(view.getPassField()[0].getPassword());
            model.getValues()[9]= String.valueOf(view.getPassField()[1].getPassword());
            model.getValues()[10]=view.getTexField()[8].getText();
            model.getValues()[11]=view.getTexField()[9].getText();
            model.getValues()[12]=view.getTexField()[10].getText();

            //PASSWORD CONTROL

            if(emptyCheck(view,model,0) | !passCheck(view,model.getValues()[8],model.getValues()[9],9) ||
                    !isNumeric(view,model.getValues()[3],3) || !isLength(view,model.getValues()[3],3) ||
                    !isLength(view,model.getValues()[3],3) || !isNumeric(view,model.getValues()[3],3) ||
                    !isLength(view,model.getValues()[5],5,8) || !isNumeric(view,model.getValues()[5],5) ||
                    !isLength(view,model.getValues()[6],6,10) || !isNumeric(view,model.getValues()[6],6) ||
                    !isLength(view,model.getValues()[7],7,12) || !dayCheck(view,model.getValues()[10],10) ||
                    !monthCheck(view,model.getValues()[11],10) || !yearCheck(view,model.getValues()[12],10)){
                if(!passCheck(view,model.getValues()[8],model.getValues()[9],9));
                if(model.getValues()[3].isEmpty() || !isLength(view,model.getValues()[3],3) || !isNumeric(view,model.getValues()[3],3));
                if(model.getValues()[5].isEmpty() || !isLength(view,model.getValues()[5],5,8) || !isNumeric(view,model.getValues()[5],5));
                if(model.getValues()[6].isEmpty() || !isLength(view,model.getValues()[6],6,10) || !isNumeric(view,model.getValues()[6],6));
                if(model.getValues()[7].isEmpty() || !isLength(view,model.getValues()[7],7,12));
                if(model.getValues()[10].isEmpty() || !dayCheck(view,model.getValues()[10],10));
                if(model.getValues()[11].isEmpty() || !monthCheck(view,model.getValues()[11],10));
                if(model.getValues()[12].isEmpty() || !yearCheck(view,model.getValues()[12],10));
                view.revalidate();
            }
            else{
                model.getValues()[10] += "." + model.getValues()[11] + "." + model.getValues()[12];
                model.setDatabase(model.getValues());
                view.setVisible(false);
                view.dispose();
                new RegLogControl();
            }
        }
    };
    /**
     * Listener to Back button.
     */
    private ActionListener endListener = e -> System.exit(1);
}